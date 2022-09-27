package com.example.tunashopadmin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UnseenMessageViewModel extends ViewModel {
    private final MutableLiveData<List<Message>> unSeenMutableLiveData;

    public UnseenMessageViewModel() {
        unSeenMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Message>> getUnSeenMutableLiveData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        return unSeenMutableLiveData;
    }
}
