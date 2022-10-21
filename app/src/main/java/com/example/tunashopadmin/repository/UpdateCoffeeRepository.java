package com.example.tunashopadmin.repository;

import android.app.Application;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateCoffeeRepository {
    private Application application;

    public UpdateCoffeeRepository(Application application) {
        this.application = application;
    }
    public void updateCoffee(String name, String category, String price, String description, String status){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("category",category);
        hashMap.put("price",price);
        hashMap.put("description",description);
        hashMap.put("status",status);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Coffee");
        reference.child(name).updateChildren(hashMap)
                .addOnSuccessListener(unused -> Toast.makeText(application,"Cập nhật thành công",Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(application,"Lỗi vui lòng thử lại",Toast.LENGTH_SHORT).show());
    }
}
