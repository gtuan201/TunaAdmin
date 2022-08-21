package com.example.tunashopadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;

import com.example.tunashopadmin.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.loginWithPhoneNumber.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),LoginWithPhoneNumberActivity.class)));
        binding.loginWithEmail.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),LoginWithEmailActivity.class)));
    }
}