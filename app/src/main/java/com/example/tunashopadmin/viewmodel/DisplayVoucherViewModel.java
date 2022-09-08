package com.example.tunashopadmin.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.Voucher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DisplayVoucherViewModel extends ViewModel {
    private final MutableLiveData<List<Voucher>> voucherMemberLiveData;
    private final List<Voucher> voucherListMember  = new ArrayList<>();

    public DisplayVoucherViewModel() {
        voucherMemberLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Voucher>> getVoucherMemberLiveData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Voucher");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                voucherListMember.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String id = ""+dataSnapshot.child("id").getValue();
                    String name = ""+dataSnapshot.child("name").getValue();
                    String subject = ""+dataSnapshot.child("subject").getValue();
                    String type = ""+dataSnapshot.child("type").getValue();
                    String imgVoucher = ""+dataSnapshot.child("imgVoucher").getValue();
                    String amount = ""+dataSnapshot.child("amount").getValue();
                    String percent = ""+dataSnapshot.child("percent").getValue();
                    String min = ""+dataSnapshot.child("minTotalPrice").getValue();
                    String max = ""+dataSnapshot.child("maxPercent").getValue();
                    String timeStart = ""+dataSnapshot.child("timeStart").getValue();
                    String timeCancel = ""+dataSnapshot.child("timeCancel").getValue();
//                    SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//                    Date date1 = new Date();
//                    try {
//                        date1 = date.parse(timeCancel);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                    if (subject.equals("member")){
                        Voucher voucher = new Voucher();
                        voucher.setId(id);
                        voucher.setNameVoucher(name);
                        voucher.setSubject(subject);
                        voucher.setType(type);
                        voucher.setImgVoucher(imgVoucher);
                        voucher.setMinTotalPrice(min);
                        voucher.setTimeStart(timeStart);
                        voucher.setTimeCancel(timeCancel);
                        if (type.equals("percent")){
                            voucher.setPercent(percent);
                            voucher.setMaxOfPercent(max);
                        }
                        else {
                            voucher.setAmount(amount);
                        }
                        voucherListMember.add(voucher);
                    }
                }
                voucherMemberLiveData.postValue(voucherListMember);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return voucherMemberLiveData;
    }
}
