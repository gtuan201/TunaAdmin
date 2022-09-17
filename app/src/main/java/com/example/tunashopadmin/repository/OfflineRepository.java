package com.example.tunashopadmin.repository;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OfflineRepository {
    private final Application application;

    public OfflineRepository(Application application) {
        this.application = application;
    }
    public void offlineUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        reference.child(auth.getUid()).child("online").onDisconnect().setValue("offline");
    }
}
