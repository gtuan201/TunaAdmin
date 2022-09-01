package com.example.tunashopadmin.view.order_detail_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityOrderDetailBinding;
import com.example.tunashopadmin.viewmodel.OrderDetailViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class OrderDetailActivity extends AppCompatActivity {
    private ActivityOrderDetailBinding binding;
    private CurrentCoffeeOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);
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
        viewModel.getListCoffeeMutableLiveData(id).observe(this, coffees -> {
            adapter = new CurrentCoffeeOrderAdapter(coffees);
            binding.revOrderDetail.setAdapter(adapter);
        });
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Bill");
        binding.btConfirm.setOnClickListener(v -> {
            if (!binding.shipper.getText().toString().equals("Chưa chỉ định tài xế")){
                HashMap<String,Object> map = new HashMap<>();
                map.put("status","Đang chuẩn bị thức uống");
                reference.child("customer").child(id)
                        .updateChildren(map)
                        .addOnCompleteListener(task -> Toast.makeText(OrderDetailActivity.this,"Đã xác nhận vui lòng đợi shipper tới lấy hàng",Toast.LENGTH_SHORT).show());
            }
            else {
                Toast.makeText(OrderDetailActivity.this,"Vui lòng chọn tài xế giao hàng",Toast.LENGTH_SHORT).show();
            }
        });
        binding.btBackDetail.setOnClickListener(v -> onBackPressed());
    }
}