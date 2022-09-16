package com.example.tunashopadmin.repository;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class ManageStaffRepository {
    private final Application application;

    public ManageStaffRepository(Application application) {
        this.application = application;
    }
    public void removeStaff(String uid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(uid).removeValue()
                .addOnSuccessListener(unused -> Toast.makeText(application,"Đã xóa",Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(application,"Lỗi! Vui lòng thử lại",Toast.LENGTH_SHORT).show());
    }
    public void updateStaff(String uid, String name, String address, String level){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("user_name",name);
        hashMap.put("address",address);
        hashMap.put("userType",level);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(uid)
                .updateChildren(hashMap)
                .addOnSuccessListener(unused -> Toast.makeText(application,"Cập nhật thành công!",Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(application,"Lỗi! Vui lòng thử lại",Toast.LENGTH_SHORT).show());
    }
}
