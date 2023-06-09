package com.example.tunashopadmin.view.login_signup_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityLoginWithPhoneNumberBinding;

public class LoginWithPhoneNumberActivity extends AppCompatActivity {
    ActivityLoginWithPhoneNumberBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_with_phone_number);
        binding.btBackPhoneNumber.setOnClickListener(v -> onBackPressed());
    }
}