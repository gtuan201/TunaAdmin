package com.example.tunashopadmin.view.add_voucher_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityAddVoucherBinding;
import com.example.tunashopadmin.view.main_screen.MainActivity;

public class AddVoucherActivity extends AppCompatActivity {

    private ActivityAddVoucherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_voucher);
        binding.btBackAddVoucher.setOnClickListener(v -> onBackPressed());
        binding.voucherTypeMoney.setOnClickListener(v -> {
            Intent intent = new Intent(AddVoucherActivity.this, StepVoucherActivity.class);
            intent.putExtra("type","money");
            startActivity(intent);
            finish();
        });
        binding.voucherTypePercent.setOnClickListener(v -> {
            Intent intent = new Intent(AddVoucherActivity.this, StepVoucherActivity.class);
            intent.putExtra("type","percent");
            startActivity(intent);
            finish();
        });
    }
}