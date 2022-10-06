package com.example.tunashopadmin.repository;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SeenLastMessage {
   private final Application application;

    public SeenLastMessage(Application application) {
        this.application = application;
    }
    public void seen(String id, String uid){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
       HashMap<String,Object> hashMap = new HashMap<>();
       hashMap.put("Status","Seen");
        assert user != null;
        if (!uid.equals(user.getUid())){
           reference.child(id).child("lastMessage").updateChildren(hashMap);
       }
   }
}
