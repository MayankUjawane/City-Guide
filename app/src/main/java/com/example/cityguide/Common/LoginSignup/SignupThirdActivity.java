package com.example.cityguide.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguide.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignupThirdActivity extends AppCompatActivity {

    TextInputLayout phoneNumberText;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_third_activity);

        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumberText = findViewById(R.id.signup_phone_number);
    }

    public void callVerifyOTPScreen(View view) {

        /* Validate fields
        if(!validatePhoneNumber()){
            return;
        } */

        // Get all values passed from previous screens using intent
        /*
        String fullName = getIntent().getStringExtra("fullName");
        String email = getIntent().getStringExtra("eamil");
        String userName = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        String date = getIntent().getStringExtra("date");
        String gender = getIntent().getStringExtra("gender");

         */

        String userEnteredPhoneNumber = phoneNumberText.getEditText().getText().toString().trim(); //Get Phone Number
        String phoneNumber = "+" + countryCodePicker.getFullNumber() + userEnteredPhoneNumber;
        Log.d("phone", "callVerifyOTPScreen: "+ phoneNumber);

        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);

        //Passing all the fields to the next Activity
        /*
        intent.putExtra("fullName", fullName);
        intent.putExtra("email", email);
        intent.putExtra("userName", userName);
        intent.putExtra("password", password);
        intent.putExtra("date", date);
        intent.putExtra("gender", gender);
         */

        intent.putExtra("phoneNumber", phoneNumber);
        startActivity(intent);

    }
}