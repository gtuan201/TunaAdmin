package com.example.tunashopadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tunashopadmin.repository.ManageStaffRepository;

public class ManageStaffViewModel extends AndroidViewModel {
    private final ManageStaffRepository manageStaffRepository;
    public ManageStaffViewModel(@NonNull Application application) {
        super(application);
        manageStaffRepository = new ManageStaffRepository(application);
    }
    public void removeStaff(String uid){
        manageStaffRepository.removeStaff(uid);
    }
    public void updateStaff(String uid, String name, String address, String level){
        manageStaffRepository.updateStaff(uid, name, address, level);
    }
}
