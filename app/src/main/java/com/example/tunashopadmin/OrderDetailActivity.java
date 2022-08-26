package com.example.tunashopadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.tunashopadmin.adapter.CurrentCoffeeOrderAdapter;
import com.example.tunashopadmin.databinding.ActivityOrderDetailBinding;
import com.example.tunashopadmin.model.Coffee;
import com.example.tunashopadmin.model.Order;
import com.example.tunashopadmin.model.User;
import com.example.tunashopadmin.viewmodel.OrderDetailViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private ActivityOrderDetailBinding binding;
    private CurrentCoffeeOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_order_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String uid = intent.getStringExtra("uid");
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.revOrderDetail.setLayoutManager(manager);
        OrderDetailViewModel viewModel = new ViewModelProvider(this).get(OrderDetailViewModel.class);
        viewModel.getOrderMutableLiveData(id).observe(this, order -> {
            binding.nameCustomer.setText(order.getFullname());
            binding.phoneCustomer.setText(order.getPhoneNumber());
            binding.addressCustomer.setText(order.getAddress());
        });
        viewModel.getUserMutableLiveData(uid).observe(this, user -> Picasso.get().load(user.getImgUrl()).into(binding.imgUserOrderDetail));
        viewModel.getListCoffeeMutableLiveData(id).observe(this, new Observer<List<Coffee>>() {
            @Override
            public void onChanged(List<Coffee> coffees) {
                adapter = new CurrentCoffeeOrderAdapter(coffees);
                binding.revOrderDetail.setAdapter(adapter);
            }
        });
    }
}