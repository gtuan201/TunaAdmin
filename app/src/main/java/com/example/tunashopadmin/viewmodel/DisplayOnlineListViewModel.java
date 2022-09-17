package com.example.tunashopadmin.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.User;
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
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String imgUrl = ""+dataSnapshot.child("imgUser").getValue();
                    String name = ""+dataSnapshot.child("user_name").getValue();
                    String online = ""+dataSnapshot.child("online").getValue();
                    String type = ""+dataSnapshot.child("userType").getValue();
                    if (online.equals("online") && ( type.equals("admin") || type.equals("staff") || type.equals("shipper") || type.equals("manager"))){
                        User user = new User();
                        user.setImgUrl(imgUrl);
                        user.setName(name);
                        list.add(user);
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
