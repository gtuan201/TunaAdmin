package com.example.tunashopadmin.view.login_signup_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityLoginWithEmailBinding;
import com.example.tunashopadmin.viewmodel.LoginViewModel;

public class LoginWithEmailActivity extends AppCompatActivity {
    ActivityLoginWithEmailBinding binding;
    private LoginViewModel loginViewModel;
    private String email = "",password ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProgressDialog progressDialog = new ProgressDialog(LoginWithEmailActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Vui lòng đợi");
        loginViewModel = new LoginViewModel(getApplication());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_with_email);
        binding.btBackEmail.setOnClickListener(v -> onBackPressed());
        binding.etEmail.setOnFocusChangeListener((v, hasFocus) -> binding.btLoginEmail.setBackgroundResource(R.drawable.button_custom4));
        binding.etPassword.setOnFocusChangeListener((v, hasFocus) -> binding.btLoginEmail.setBackgroundResource(R.drawable.button_custom4));
        binding.btLoginEmail.setOnClickListener(v -> {
            email = binding.etEmail.getText().toString().trim();
            password = binding.etPassword.getText().toString().trim();
            if (TextUtils.isEmpty(email)){
                Toast.makeText(LoginWithEmailActivity.this,"Vui lòng nhập email",Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(password)){
                Toast.makeText(LoginWithEmailActivity.this,"Vui lòng nhập mật khẩu",Toast.LENGTH_SHORT).show();
            }
            else if (password.length() < 6){
                Toast.makeText(LoginWithEmailActivity.this,"Mật khẩu phải có ít nhất 6 ký tự",Toast.LENGTH_SHORT).show();
            }
            else {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binding.btLoginEmail.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },2000);
                loginViewModel.login(email,password);
            }
        });
    }
}