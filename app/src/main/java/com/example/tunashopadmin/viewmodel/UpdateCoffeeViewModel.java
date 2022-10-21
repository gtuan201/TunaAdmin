package com.example.tunashopadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tunashopadmin.repository.UpdateCoffeeRepository;

public class UpdateCoffeeViewModel extends AndroidViewModel {
    private final UpdateCoffeeRepository repository;
    public UpdateCoffeeViewModel(@NonNull Application application) {
        super(application);
        repository = new UpdateCoffeeRepository(application);
    }
    public void updateCoffee(String name, String category, String price, String description, String status){
        repository.updateCoffee(name, category, price, description, status);
    }
}
