package com.example.cityguide.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.cityguide.Databases.UserData;
import com.example.cityguide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    PinView pin;
    String codeBySystem;
    String email, userName, password, date, gender, phoneNumber;
    UserData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        pin = findViewById(R.id.pin_view);

        email = getIntent().getStringExtra("email");
        userName = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        date = getIntent().getStringExtra("date");
        gender = getIntent().getStringExtra("gender");
        phoneNumber = getIntent().getStringExtra("phoneNumber");

        data = new UserData(email, userName, password, date, gender, phoneNumber);
        Log.d("data", "onCreate: "+ data.toString());

        sendOTP(phoneNumber);
    }

    private void sendOTP(String phoneNumber) {
        // All of the work involved in generating the code and sending the SMS message is handled by a call to the verifyPhoneNumber() method
        // of the PhoneAuthProvider instance. This will trigger a call to one of the following callback methods:

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,              //Phone Number to verify
                60,                    //Timeout duration
                TimeUnit.SECONDS,        //Unit of timeout
                TaskExecutors.MAIN_THREAD,            //Activity (for callback binding)
                mCallbacks);            //OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            // onCodeSent() – Called after the code has been sent. This method is passed a verification ID (String s)
            // and a resend token that should be referenced when making a code resend request.

            super.onCodeSent(s, forceResendingToken);
            codeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

           // onVerificationCompleted() – Called only on certain device configurations (typically devices containing a SIM card) where
           // the verification code can be verified automatically on the device without the user having to manually enter it.

            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                pin.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            // onVerificationFailed() – Indicates that an error occurred when sending the activation code.
            // This is usually the result of the user entering an invalid or incorrectly formatted phone number.

            Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        // Returns a new instance of AuthCredential that is associated with a phone number. Used when calling FirebaseAuth.signInWithCredential(AuthCredential)
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            storeNewUsersData();

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerifyOTP.this, "Verification is not completed! Try Again", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void storeNewUsersData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User Data");

        Log.d("userData", "storeNewUsersData: "+ data.toString());
        myRef.setValue(data);

    }

    public void callNextScreenFromOTP(View view) {
        String code = pin.getText().toString();
        if(!code.isEmpty()){
            verifyCode(code);
        }
    }
}