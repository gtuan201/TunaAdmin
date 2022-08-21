package com.example.tunashopadmin;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
    public void register(String email, String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                            putDataToRealtime();
                        }
                        else {
                            Toast.makeText(application,"Đăng ký thất bại! Vui lòng thử lại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void putDataToRealtime() {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("imgUser","");
        hashMap.put("uid",auth.getCurrentUser().getUid());
        hashMap.put("user_name","");
        hashMap.put("userType","");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(auth.getCurrentUser().getUid())
                .setValue(hashMap);
    }

    public void login(String email, String password){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                            checkUser();
                        }
                        else {
                            Toast.makeText(application,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void checkUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        FirebaseUser user = auth.getCurrentUser();
        reference.child(user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String userType = ""+snapshot.child("userType").getValue();
                        if (userType.equals("admin")){
                            Intent intent = new Intent(application,MainActivity.class);
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
        userLoggedMutableLiveData.postValue(true);
    }
}
