package com.example.tunashopadmin.repository;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.tunashopadmin.view.main_screen.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AuthenticationRepository {
    private final Application application;
    private final MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userLoggedMutableLiveData;
    private final FirebaseAuth auth;

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public MutableLiveData<Boolean> getUserLoggedMutableLiveData() {
        return userLoggedMutableLiveData;
    }

    public AuthenticationRepository(Application application){
        this.application = application;
        firebaseUserMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
        }
    }
    public void creatUser(String email, String password, String name, String type, String address, String phone){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        reference.child(auth.getUid()).child("online").setValue("offline");
        register(email, password, name, type, address, phone);

    }
    public void register(String email, String password, String name, String type, String address, String phone){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                        putDataToRealtime(name,type,address,phone);
                    }
                    else {
                        Toast.makeText(application,"Đăng ký thất bại! Vui lòng thử lại",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void putDataToRealtime(String name, String type, String address, String phone) {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("imgUser","");
        hashMap.put("uid",auth.getCurrentUser().getUid());
        hashMap.put("user_name",name);
        hashMap.put("userType",type);
        hashMap.put("address",address);
        hashMap.put("phone",phone);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(auth.getCurrentUser().getUid())
                .setValue(hashMap)
                .addOnSuccessListener(unused -> {
                    auth.signOut();
                    Toast.makeText(application, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                });
    }

    public void login(String email, String password){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                        checkUser();
                    }
                    else {
                        auth.signOut();
                        Toast.makeText(application,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        reference.child(user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String userType = ""+snapshot.child("userType").getValue();
                        if (userType.equals("admin")){
                            HashMap<String,Object> hashMap = new HashMap<>();
                            hashMap.put("online","online");
                            reference.child(user.getUid()).updateChildren(hashMap);
                            Intent intent = new Intent(application, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            application.startActivity(intent);
                        }
                        else {
                            Toast.makeText(application,"Bạn không phải quản lý",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void signOut(){
        auth.signOut();
    }
}
