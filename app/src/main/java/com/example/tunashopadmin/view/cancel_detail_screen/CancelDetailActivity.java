package com.example.tunashopadmin.view.cancel_detail_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityCancelDetailBinding;
import com.example.tunashopadmin.model.Coffee;
import com.example.tunashopadmin.view.order_detail_screen.CurrentCoffeeOrderAdapter;
import com.example.tunashopadmin.viewmodel.OrderDetailViewModel;

import java.util.List;

public class CancelDetailActivity extends AppCompatActivity {
    // dùng chung adapter với OrderDetailActivity
    private CurrentCoffeeOrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        com.example.tunashopadmin.databinding.ActivityCancelDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_cancel_detail);
        OrderDetailViewModel viewModel = new ViewModelProvider(this).get(OrderDetailViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.revCancelDetail.setLayoutManager(manager);
        viewModel.getListCoffeeMutableLiveData(id).observe(this, coffees -> {
            adapter = new CurrentCoffeeOrderAdapter(coffees);
            binding.revCancelDetail.setAdapter(adapter);
        });
        binding.btBackCancelDetail.setOnClickListener(v -> onBackPressed());
    }
}