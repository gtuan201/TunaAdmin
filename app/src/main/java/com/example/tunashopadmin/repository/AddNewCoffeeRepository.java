package com.example.tunashopadmin.repository;

import android.app.Application;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddNewCoffeeRepository {
    private final Application application;

    public AddNewCoffeeRepository(Application application) {
        this.application = application;
    }
    public void addNewCoffee(String img, String back, String name, String category, String price, String des){
        long timestamp = System.currentTimeMillis();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("ImgUrl",img);
        hashMap.put("background",back);
        hashMap.put("category",category);
        hashMap.put("description",des);
        hashMap.put("id",""+timestamp);
        hashMap.put("name",name);
        hashMap.put("price",price);
        hashMap.put("rate",0);
        hashMap.put("status","Đang bán");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Coffee");
        reference.child(name).setValue(hashMap)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(application, "Thêm thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(application, "Thất bại vui lòng thử lại", Toast.LENGTH_SHORT).show();
                });
    }
}
