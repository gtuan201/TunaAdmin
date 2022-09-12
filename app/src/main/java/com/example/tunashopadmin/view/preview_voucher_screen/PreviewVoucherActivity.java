package com.example.tunashopadmin.view.preview_voucher_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityPreviewVoucherBinding;
import com.example.tunashopadmin.view.add_voucher_screen.AddVoucherActivity;
import com.example.tunashopadmin.view.add_voucher_screen.StepVoucherActivity;
import com.example.tunashopadmin.viewmodel.AddVoucherViewModel;

public class PreviewVoucherActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPreviewVoucherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_preview_voucher);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String name = intent.getStringExtra("nameVoucher");
        String subject = intent.getStringExtra("object");
        String percent = intent.getStringExtra("percent");
        String amount = intent.getStringExtra("amount");
        String min = intent.getStringExtra("minTotalPrice");
        String max = intent.getStringExtra("maxPrice");
        String timeStart = intent.getStringExtra("timeStart");
        String timeCancel = intent.getStringExtra("timeCancel");
        AddVoucherViewModel viewModel = new ViewModelProvider(this).get(AddVoucherViewModel.class);
        binding.nameVoucher.setText(name);
        binding.tvMinTotalPrice.setText(String.format("%sđ", min));
        binding.tvTimeStart.setText(timeStart);
        binding.tvTimeCancel.setText(timeCancel);
        if (type.equals("percent")){
            binding.typeVoucher.setText("Giảm giá theo phần trăm trên giá trị của đơn hàng");
        }
        else {
            binding.typeVoucher.setText("Giảm giá theo số tiền cụ thể");
        }
        if (subject.equals("all")){
            binding.tvObject.setText("Toàn bộ bộ cửa hàng");
            binding.tvObjectDetail.setText("Toàn bộ khách hàng sẽ sử dụng được mã giảm giá này");
        }
        else {
            binding.tvObject.setText("Khách hàng là member");
            binding.tvObjectDetail.setText("Chỉ những khách hàng là member mới thấy và sử dụng mà giảm giá này");
        }
        if (type.equals("money")){
            binding.tvMaxVoucher.setVisibility(View.GONE);
            binding.titleMax.setVisibility(View.GONE);
            binding.tvPriceVoucher.setText(String.format("%sđ", amount));
        }
        else {
            binding.tvMaxVoucher.setVisibility(View.VISIBLE);
            binding.titleMax.setVisibility(View.VISIBLE);
            binding.tvMaxVoucher.setText(String.format("%sđ", max));
            binding.tvPriceVoucher.setText(String.format("%s %%", percent));
        }
        binding.btBackPreviewVoucher.setOnClickListener(v -> onBackPressed());
        binding.btComplete.setOnClickListener(v -> {
            long timestamp = System.currentTimeMillis();
            viewModel.AddVoucher(timestamp,name,subject,timeStart,timeCancel,amount,percent,min,max,type);
            new Handler().postDelayed(() -> {
                onBackPressed();
                finish();
                StepVoucherActivity.fa.finish();
            },1000);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}