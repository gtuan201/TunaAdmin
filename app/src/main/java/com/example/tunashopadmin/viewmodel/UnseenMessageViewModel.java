package com.example.tunashopadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tunashopadmin.repository.SeenLastMessage;

public class UnseenMessageViewModel extends AndroidViewModel {
    private SeenLastMessage repository;
    public UnseenMessageViewModel(@NonNull Application application) {
        super(application);
        repository = new SeenLastMessage(application);
    }
    public void seenMessage(String id, String uidLast){
        repository.seen(id,uidLast);
    }
}
