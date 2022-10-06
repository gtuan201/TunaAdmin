package com.example.tunashopadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tunashopadmin.repository.AddMessageRepository;

public class MessageViewModel extends AndroidViewModel {
    private AddMessageRepository repository;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        repository = new AddMessageRepository(application);
    }

    public void addMessage(long id, String message, String uidReceiver, String imgReceiver, String nameReceiver) {
        repository.addMessage(id, message, uidReceiver,imgReceiver,nameReceiver);
    }
    public void updateMessage(long id, String message,long idMessage){
        repository.updateMessage(id, message,idMessage);
    }
}
