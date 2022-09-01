package com.example.tunashopadmin.view.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tunashopadmin.view.login_signup_screen.LoginActivity;
import com.example.tunashopadmin.view.main_screen.MainActivity;
import com.example.tunashopadmin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        new Handler().postDelayed(() -> {
            if (user!=null){
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
            else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        },1700);
    }
}