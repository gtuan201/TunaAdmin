package com.example.tunashopadmin.view.menu_manage_screen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityCoffeeDetailBinding;
import com.example.tunashopadmin.viewmodel.UpdateCoffeeViewModel;
import com.squareup.picasso.Picasso;

public class CoffeeDetailActivity extends AppCompatActivity {
    private ActivityCoffeeDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_coffee_detail);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String category = intent.getStringExtra("category");
        String price = intent.getStringExtra("price");
        String img = intent.getStringExtra("img");
        String background = intent.getStringExtra("background");
        String des = intent.getStringExtra("description");
        String status = intent.getStringExtra("status");
        UpdateCoffeeViewModel viewModel = new ViewModelProvider(this).get(UpdateCoffeeViewModel.class);
        Picasso.get().load(background).into(binding.imgThumbnail);
        binding.name.setText(name);
        binding.category.setText(category);
        binding.price.setText(price);
        binding.description.setText(des);
        binding.swOnOffCoffee.setChecked(status.equals("Đang bán"));
        binding.status.setText(status);
        if (status.equals("Đang bán")){
            binding.status.setTextColor(Color.GREEN);
        }
        else {
            binding.status.setTextColor(Color.RED);
        }
        binding.swOnOffCoffee.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                binding.status.setTextColor(Color.GREEN);
                binding.status.setText("Đang bán");
            }
            else {
                binding.status.setTextColor(Color.RED);
                binding.status.setText("Ngừng bán");
            }
        });
        binding.btUpdateCoffee.setOnClickListener(v -> {
            String upName = binding.name.getText().toString().trim();
            String upCate = binding.category.getText().toString().trim();
            String upPrice = binding.price.getText().toString().trim();
            String upDes = binding.description.getText().toString().trim();
            String upStatus = binding.status.getText().toString().trim();
            if (TextUtils.isEmpty(upName) || TextUtils.isEmpty(upCate) || TextUtils.isEmpty(upPrice) || TextUtils.isEmpty(upDes)){
                Toast.makeText(CoffeeDetailActivity.this,"Không được để trống thông tin",Toast.LENGTH_SHORT).show();
            }
            else viewModel.updateCoffee(upName,upCate,upPrice,upDes,upStatus);
        });
    }
}