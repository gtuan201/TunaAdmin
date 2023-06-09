package com.example.tunashopadmin.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddMessageRepository {
    private Application application;

    public AddMessageRepository(Application application) {
        this.application = application;
    }
    public void updateMessage(long id, String message,long idMessage) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        assert user != null;
        HashMap<String,Object> map = new HashMap<>();
        map.put("uid",user.getUid());
        map.put("message",message);
        map.put("Status","UnSeen");
        HashMap<String,Object> hashMap = new HashMap<>();
        HashMap<String,Object> hashMap1 = new HashMap<>();
        hashMap1.put("lastMessage",message);
        hashMap1.put("uid",user.getUid());
        hashMap1.put("Status","UnSeen");
        reference.child(""+id).child("lastMessage").updateChildren(hashMap1);
        reference.child(""+id).updateChildren(hashMap);
        reference.child(""+id).child("Message").child(""+idMessage)
                .updateChildren(map);
    }


    public void addMessage(long id,String message, String uidReceiver, String imgReceiver, String nameReceiver) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        HashMap<String,Object> map = new HashMap<>();
        assert user != null;
        map.put("idMessage",""+id+ 1);
        map.put("uid",user.getUid());
        map.put("message",message);
        map.put("Status","UnSeen");
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",""+id);
        hashMap.put("uid",user.getUid());
        hashMap.put("uidReceiver",uidReceiver);
        hashMap.put("imgReceiver",imgReceiver);
        hashMap.put("nameReceiver",nameReceiver);
        HashMap<String,Object> hashMap1 = new HashMap<>();
        hashMap1.put("lastMessage",message);
        hashMap1.put("uid",user.getUid());
        hashMap1.put("Status","UnSeen");
        reference.child(""+id).child("lastMessage").updateChildren(hashMap1);
        reference.child(""+id).updateChildren(hashMap);
        reference.child(""+id).child("Message").child(""+id+ 1 )
                .updateChildren(map);
    }
}
