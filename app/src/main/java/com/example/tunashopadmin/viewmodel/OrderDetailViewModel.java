package com.example.tunashopadmin.viewmodel;





import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.Coffee;
import com.example.tunashopadmin.model.Order;
import com.example.tunashopadmin.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailViewModel extends ViewModel {
    private final MutableLiveData<Order> orderMutableLiveData;
    private final MutableLiveData<User> userMutableLiveData;
    private final MutableLiveData<List<Coffee>> listCoffeeMutableLiveData;
    public OrderDetailViewModel() {
        orderMutableLiveData = new MutableLiveData<>();
        userMutableLiveData = new MutableLiveData<>();
        listCoffeeMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<User> getUserMutableLiveData(String uid) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String imgUrl = ""+snapshot.child("imgUser").getValue();
                        User user = new User();
                        user.setImgUrl(imgUrl);
                        userMutableLiveData.postValue(user);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return userMutableLiveData;
    }

    public MutableLiveData<Order> getOrderMutableLiveData(String id) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Bill");
        reference.child("customer").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = ""+snapshot.child("fullname").getValue();
                        String phone = ""+snapshot.child("phone").getValue();
                        String address = ""+snapshot.child("address").getValue();
                        String shopname = ""+snapshot.child("shopName").getValue();
                        String method = ""+snapshot.child("purchase_method").getValue();
                        Order order = new Order();
                        if (method.equals("ship")){
                            order.setAddress(address);
                        }
                        else if (method.equals("pick up")){
                            order.setAddress(shopname);
                        }
                        order.setFullname(name);
                        order.setPhoneNumber(phone);
                        orderMutableLiveData.postValue(order);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return orderMutableLiveData;
    }

    public MutableLiveData<List<Coffee>> getListCoffeeMutableLiveData(String id) {
        List<Coffee> coffeeList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Bill");
        reference.child("customer").child(id).child("coffee")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        coffeeList.clear();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            String imgUrl = ""+dataSnapshot.child("image").getValue();
                            String name = ""+dataSnapshot.child("name").getValue();
                            String size = ""+dataSnapshot.child("size").getValue();
                            String ice = ""+dataSnapshot.child("ice").getValue();
                            String quantity = ""+dataSnapshot.child("quantity").getValue();
                            String note = ""+dataSnapshot.child("note").getValue();
                            String price = ""+dataSnapshot.child("totalPrice").getValue();
                            Coffee coffee = new Coffee();
                            coffee.setUrlImg(imgUrl);
                            coffee.setCoffeeName(name);
                            coffee.setSize(size);
                            coffee.setIce(ice);
                            coffee.setQuantity(quantity);
                            coffee.setNote(note);
                            coffee.setTotalPrice(price);
                            coffeeList.add(coffee);
                        }
                        listCoffeeMutableLiveData.setValue(coffeeList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return listCoffeeMutableLiveData;
    }
}
