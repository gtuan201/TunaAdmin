package com.example.tunashopadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tunashopadmin.repository.OfflineRepository;

public class OfflineViewModel extends AndroidViewModel {
    private final OfflineRepository offlineRepository;
    public OfflineViewModel(@NonNull Application application) {
        super(application);
        offlineRepository = new OfflineRepository(application);
    }
    public void offlineUser(){
        offlineRepository.offlineUser();
    }
}
