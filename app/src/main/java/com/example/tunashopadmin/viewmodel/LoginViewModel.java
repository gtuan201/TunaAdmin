package com.example.tunashopadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tunashopadmin.repository.AuthenticationRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private final AuthenticationRepository repository;
    private final MutableLiveData<FirebaseUser> userData;
    private final MutableLiveData<Boolean> loggedStatus;

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedStatus() {
        return loggedStatus;
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new AuthenticationRepository(application);
        userData = repository.getFirebaseUserMutableLiveData();
        loggedStatus = repository.getUserLoggedMutableLiveData();
    }
    public void register(String email, String password , String name, String type, String address, String phone){
        repository.register(email,password,name,type,address,phone);
    }
    public void login(String email, String password){
        repository.login(email,password);
    }
    public void signOut(){
        repository.signOut();
    }
}
