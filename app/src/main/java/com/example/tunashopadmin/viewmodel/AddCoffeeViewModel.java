package com.example.tunashopadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tunashopadmin.repository.AddNewCoffeeRepository;

public class AddCoffeeViewModel extends AndroidViewModel {
    private final AddNewCoffeeRepository repository;
    public AddCoffeeViewModel(@NonNull Application application) {
        super(application);
        repository = new AddNewCoffeeRepository(application);
    }
    public void addCoffee(String img, String back, String name, String category, String price, String des){
        repository.addNewCoffee(img, back, name, category, price, des);
    }
}
