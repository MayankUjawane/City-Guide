package com.example.cityguide.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguide.Databases.UserData;
import com.example.cityguide.R;
import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity {

    ImageView backButton;
    Button next, login;
    TextView titleText;
    TextInputEditText userNameText, passwordText, emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_signup);

        backButton = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);

        userNameText = findViewById(R.id.signup_user_name);
        passwordText = findViewById(R.id.signup_password);
        emailText = findViewById(R.id.signup_email);

    }

    public void callNextSignupScreen(View view) {

        Intent intent = new Intent(this, SignupSecondActivity.class);
        intent.putExtra("userName", userNameText.getText().toString());
        intent.putExtra("password", passwordText.getText().toString());
        intent.putExtra("email", emailText.getText().toString());

        // Add Transition
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(backButton, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup.this, pairs);
            startActivity(intent, options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }
}