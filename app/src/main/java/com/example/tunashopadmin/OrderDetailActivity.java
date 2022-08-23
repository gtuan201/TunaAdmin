package com.example.tunashopadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.tunashopadmin.databinding.ActivityOrderDetailBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class OrderDetailActivity extends AppCompatActivity {
    private ActivityOrderDetailBinding binding;
    private String id,uid,name,address,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_order_detail);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        uid = intent.getStringExtra("uid");
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        phone = intent.getStringExtra("phoneNumber");
        binding.nameCustomer.setText(name);
        binding.addressCustomer.setText(address);
        binding.phoneCustomer.setText(phone);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String imgUrl = ""+snapshot.child("imgUser").getValue();
                        if (imgUrl.equals("")){
                            binding.imgUserOrderDetail.setImageResource(R.drawable.user);
                        }
                        else {
                            Picasso.get().load(imgUrl).into(binding.imgUserOrderDetail);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public String getId() {
        return id;
    }
}