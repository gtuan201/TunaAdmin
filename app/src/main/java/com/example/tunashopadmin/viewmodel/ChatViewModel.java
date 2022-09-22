package com.example.tunashopadmin.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {
    private final MutableLiveData<List<Chat>> chatMutableLiveData;

    public ChatViewModel() {
        chatMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Chat>> getChatMutableLiveData() {
        final List<Chat> chatList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference.child(user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        chatList.clear();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            String imgReceiver = ""+dataSnapshot.child("imgReceiver").getValue();
                            String nameReceiver = ""+dataSnapshot.child("nameReceiver").getValue();
                            String uidReceiver = ""+dataSnapshot.child("uidReceiver").getValue();
                            String lastMessage = ""+dataSnapshot.child("lastMessage").getValue();
                            Chat chat = new Chat();
                            chat.setUidReceiver(uidReceiver);
                            chat.setLastMessage(lastMessage);
                            chat.setImgReceiver(imgReceiver);
                            chat.setNameReceiver(nameReceiver);
                            chatList.add(chat);
                        }
                        chatMutableLiveData.postValue(chatList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return chatMutableLiveData;
    }
}
