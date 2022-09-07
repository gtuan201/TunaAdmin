package com.example.tunashopadmin.repository;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;
import com.example.tunashopadmin.view.add_voucher_screen.AddVoucherActivity;
import com.example.tunashopadmin.view.main_screen.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddVoucherRepository {
    private final Application application;

    public AddVoucherRepository(Application application) {
        this.application = application;
    }

    public void addVoucherToDatabase(long id,String nameVoucher, String subject,String timeStart,String timeCancel,String amount, String percent,String minTotalPrice,String maxOfPercent,String type){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Voucher");
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",""+id);
        hashMap.put("name",nameVoucher);
        hashMap.put("subject",subject);
        hashMap.put("type",type);
        hashMap.put("amount",""+amount);
        hashMap.put("percent",""+percent);
        hashMap.put("maxPercent",""+maxOfPercent);
        hashMap.put("minTotalPrice",minTotalPrice);
        hashMap.put("timeStart",timeStart);
        hashMap.put("timeCancel",timeCancel);
        reference.child(""+id).setValue(hashMap)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(application,"Đã thêm voucher thành công",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(application, AddVoucherActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        application.startActivity(intent);
                    },1200);
                })
                .addOnFailureListener(e -> Toast.makeText(application,"Lỗi! Vui lòng thử lại",Toast.LENGTH_SHORT).show());
    }
}
