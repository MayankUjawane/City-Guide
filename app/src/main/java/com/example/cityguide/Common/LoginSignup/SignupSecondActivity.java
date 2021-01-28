package com.example.cityguide.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguide.R;

public class SignupSecondActivity extends AppCompatActivity {

    ImageView backButton;
    Button next, login;
    TextView titleText;
    String gender;
    DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_second_activity);

        backButton = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);
        picker = findViewById(R.id.date_picker);

    }

    public String getSelectedDate(){
        StringBuilder builder=new StringBuilder();;
        builder.append((picker.getMonth() + 1)+"/"); //month is 0 based i.e. 0 is January
        builder.append(picker.getDayOfMonth()+"/");
        builder.append(picker.getYear());
        return builder.toString();
    }

    public void callNextSignupScreen(View view) {

        String userName = getIntent().getStringExtra("userName");
        String password = getIntent().getStringExtra("password");
        String email = getIntent().getStringExtra("email");
        String age = getSelectedDate();

        Intent intent = new Intent(this, SignupThirdActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("password", password);
        intent.putExtra("email", email);
        intent.putExtra("gender", gender);
        intent.putExtra("age", age);

        // Add Transition
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(backButton, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
            startActivity(intent, options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }

    public void radioButtonClicked(View view) {
        switch (view.getId()){
            case R.id.rb_male:
                gender = "Male";
                break;

            case R.id.rb_female:
                gender = "Female";
                break;

            case R.id.rb_other:
                gender = "Other";
                break;
        }
    }
}