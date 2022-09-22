package com.example.tunashopadmin.viewmodel;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.tunashopadmin.model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayMessageViewModel extends ViewModel {
    private final MutableLiveData<List<Message>> messageMutableLiveData;
    List<Message> messageList = new ArrayList<>();

    public DisplayMessageViewModel() {
        messageMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Message>> getMessageMutableLiveData(String id) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.child(id).child("Message")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            String message = ""+dataSnapshot.child("message").getValue();
                            String uidSender = ""+dataSnapshot.child("uid").getValue();
                            Message messageModel = new Message();
                            messageModel.setMessage(message);
                            messageModel.setSenderID(uidSender);
                            messageList.add(messageModel);
                        }
                        messageMutableLiveData.postValue(messageList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return messageMutableLiveData;
    }
}
