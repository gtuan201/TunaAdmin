package com.example.tunashopadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tunashopadmin.repository.AddVoucherRepository;

public class AddVoucherViewModel extends AndroidViewModel {
    private final AddVoucherRepository repository;
    public AddVoucherViewModel(@NonNull Application application) {
        super(application);
        repository = new AddVoucherRepository(application);
    }
    public void AddVoucher(long id,String nameVoucher, String subject,String timeStart,String timeCancel,String amount, String percent,String minTotalPrice,String maxOfPercent,String type){
        repository.addVoucherToDatabase(id,nameVoucher,subject,timeStart,timeCancel,amount,percent,minTotalPrice,maxOfPercent,type);
    }
}
