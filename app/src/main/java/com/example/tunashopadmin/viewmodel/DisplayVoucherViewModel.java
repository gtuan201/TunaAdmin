package com.example.tunashopadmin.viewmodel;

import android.annotation.SuppressLint;

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
    private final MutableLiveData<List<Voucher>> voucherAllLiveData;
    public DisplayVoucherViewModel() {
        voucherMemberLiveData = new MutableLiveData<>();
        voucherAllLiveData  = new MutableLiveData<>();
    }
    @SuppressLint("SimpleDateFormat")
    public MutableLiveData<List<Voucher>> getVoucherMemberLiveData() {
        final List<Voucher> voucherListMember  = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Voucher");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                voucherListMember.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    Date currentDate = Calendar.getInstance().getTime();
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
                    Date dateCancel = new Date();
                    Date dateStart = new Date();
                    try {
                        dateCancel = dateFormat.parse(timeCancel);
                        dateStart = dateFormat.parse(timeStart);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (subject.equals("member") && currentDate.after(dateStart) && currentDate.before(dateCancel)){
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
    @SuppressLint("SimpleDateFormat")
    public MutableLiveData<List<Voucher>> getVoucherAllLiveData() {
        final List<Voucher> voucherListAll  = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Voucher");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                voucherListAll.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    Date currentDate = Calendar.getInstance().getTime();
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
                    Date dateCancel = new Date();
                    Date dateStart = new Date();
                    try {
                        dateCancel = dateFormat.parse(timeCancel);
                        dateStart = dateFormat.parse(timeStart);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (subject.equals("all") && currentDate.after(dateStart) && currentDate.before(dateCancel)){
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
                        voucherListAll.add(voucher);
                    }
                }
                voucherAllLiveData.postValue(voucherListAll);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return voucherAllLiveData;
    }
}
