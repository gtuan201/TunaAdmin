package com.example.tunashopadmin.view.splash_screen;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        new Handler().postDelayed(() -> {
            if (user != null){
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                reference.child(user.getUid()).child("online").setValue("online");
            }
            else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        },1700);
    }
}