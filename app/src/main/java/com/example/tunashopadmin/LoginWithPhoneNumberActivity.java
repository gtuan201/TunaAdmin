package com.example.tunashopadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.tunashopadmin.databinding.ActivityLoginWithPhoneNumberBinding;

public class LoginWithPhoneNumberActivity extends AppCompatActivity {
    ActivityLoginWithPhoneNumberBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login_with_phone_number);
        binding.btBackPhoneNumber.setOnClickListener(v -> onBackPressed());
    }
}