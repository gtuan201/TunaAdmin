package com.example.tunashopadmin.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends ViewModel {
    private final MutableLiveData<List<Order>> listMutableLiveData;
    private final List<Order> orderList = new ArrayList<>();

    public OrderViewModel() {
        listMutableLiveData = new MutableLiveData<>();
        initData();
    }

    private void initData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Bill");
        reference.child("customer")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        orderList.clear();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            String id = ""+dataSnapshot.child("id").getValue();
                            String uid = ""+dataSnapshot.child("uid").getValue();
                            String name = ""+dataSnapshot.child("fullname").getValue();
                            String address = ""+dataSnapshot.child("address").getValue();
                            String shopName = ""+dataSnapshot.child("shopName").getValue();
                            String time = ""+dataSnapshot.child("time").getValue();
                            String date = ""+dataSnapshot.child("date").getValue();
                            String totalPrice = ""+dataSnapshot.child("totalPrice").getValue();
                            String method = ""+dataSnapshot.child("purchase_method").getValue();
                            String phone = ""+dataSnapshot.child("phone").getValue();
                            String status = ""+dataSnapshot.child("status").getValue();
                            if (status.equals("Đang chuẩn bị thức uống")){
                                Order order = new Order();
                                order.setId(id);
                                order.setUid(uid);
                                order.setFullname(name);
                                order.setPhoneNumber(phone);
                                order.setTime(time);
                                order.setDate(date);
                                order.setTotalprice(totalPrice);
                                if (method.equals("ship")){
                                    order.setAddress(address);
                                }
                                else if (method.equals("pick up")){
                                    order.setAddress(shopName);
                                }
                                orderList.add(order);
                            }
                        }
                        listMutableLiveData.setValue(orderList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public MutableLiveData<List<Order>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}
