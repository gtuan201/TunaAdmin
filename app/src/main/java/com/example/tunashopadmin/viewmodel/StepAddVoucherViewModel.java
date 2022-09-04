package com.example.tunashopadmin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.Voucher;

public class StepAddVoucherViewModel extends ViewModel {

    private final MutableLiveData<Voucher> voucherMutableLiveData;
    public StepAddVoucherViewModel() {
        voucherMutableLiveData = new MutableLiveData<>();
    }

    public void setData(Voucher voucher){
        voucherMutableLiveData.setValue(voucher);

    }
    public MutableLiveData<Voucher> getVoucherMutableLiveData() {
        return voucherMutableLiveData;
    }
}
