package com.example.cityguide.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.example.cityguide.R;

public class RetailerStartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_start_screen);

    }

    public void callLoginScreen(View view) {
        Intent intent = new Intent(this, Login.class);

     //   Pair[] pairs = new Pair[1];
     //   pairs[0] = new Pair<View, String>(findViewById(R.id.login_btn) , "transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

     //   ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RetailerStartScreen.this, pairs);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RetailerStartScreen.this, findViewById(R.id.login_btn) , "transition_login");
            startActivity(intent, options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }

    public void callSignupScreen(View view) {

        Intent intent = new Intent(this, Signup.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RetailerStartScreen.this, findViewById(R.id.signup_btn) , "transition_signup");
            startActivity(intent, options.toBundle());
        }
        else{
            startActivity(intent);
        }
    }
}