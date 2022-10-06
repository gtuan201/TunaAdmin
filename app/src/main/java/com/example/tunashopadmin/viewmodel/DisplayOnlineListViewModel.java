package com.example.tunashopadmin.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayOnlineListViewModel extends ViewModel {
    private final MutableLiveData<List<User>> userOnlineMutableLiveData;

    public DisplayOnlineListViewModel() {
        userOnlineMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getUserOnlineMutableLiveData() {
        final List<User> list = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String imgUrl = ""+dataSnapshot.child("imgUser").getValue();
                    String name = ""+dataSnapshot.child("user_name").getValue();
                    String online = ""+dataSnapshot.child("online").getValue();
                    String type = ""+dataSnapshot.child("userType").getValue();
                    String uid = ""+dataSnapshot.child("uid").getValue();
                    if ((type.equals("admin") || type.equals("staff") || type.equals("shipper") || type.equals("manager"))) {
                        assert user != null;
                        if (!uid.equals(user.getUid())) {
                            User user = new User();
                            user.setUid(uid);
                            user.setImgUrl(imgUrl);
                            user.setName(name);
                            user.setOnline(online);
                            list.add(user);
                        }
                    }
                }
                userOnlineMutableLiveData.postValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return userOnlineMutableLiveData;
    }
}
