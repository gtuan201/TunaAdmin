package com.example.tunashopadmin.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.Coffee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayCoffeeViewModel extends ViewModel {
    private final MutableLiveData<List<Coffee>> coffeeLiveData;

    public DisplayCoffeeViewModel() {
        coffeeLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Coffee>> getCoffeeLiveData() {
        List<Coffee> list = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Coffee");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String img = ""+dataSnapshot.child("ImgUrl").getValue();
                    String back = ""+dataSnapshot.child("background").getValue();
                    String des = ""+dataSnapshot.child("description").getValue();
                    String name = ""+dataSnapshot.child("name").getValue();
                    String cate = ""+dataSnapshot.child("category").getValue();
                    String price = ""+dataSnapshot.child("price").getValue();
                    String status = ""+dataSnapshot.child("status").getValue();
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeName(name);
                    coffee.setUrlImg(img);
                    coffee.setPrice(price);
                    coffee.setCategory(cate);
                    coffee.setStatus(status);
                    coffee.setBackground(back);
                    coffee.setDescription(des);
                    list.add(coffee);
                }
                coffeeLiveData.postValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return coffeeLiveData;
    }
}
