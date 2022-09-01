package com.example.tunashopadmin.view.login_signup_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.loginWithPhoneNumber.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LoginWithPhoneNumberActivity.class)));
        binding.loginWithEmail.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LoginWithEmailActivity.class)));
    }
}