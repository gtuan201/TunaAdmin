package com.example.tunashopadmin.view.preview_voucher_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityPreviewVoucherBinding;
import com.example.tunashopadmin.model.Voucher;
import com.example.tunashopadmin.viewmodel.StepAddVoucherViewModel;

public class PreviewVoucherActivity extends AppCompatActivity {
    private ActivityPreviewVoucherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_preview_voucher);
        StepAddVoucherViewModel viewModel = new ViewModelProvider(this).get(StepAddVoucherViewModel.class);
        viewModel.getVoucherMutableLiveData().observe(this, new Observer<Voucher>() {
            @Override
            public void onChanged(Voucher voucher) {
                binding.tvTest.setText(voucher.getNameVoucher());
            }
        });
    }
}