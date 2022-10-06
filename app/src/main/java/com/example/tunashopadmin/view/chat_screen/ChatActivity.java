package com.example.tunashopadmin.view.chat_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityChatBinding;
import com.example.tunashopadmin.model.Message;
import com.example.tunashopadmin.view.chat_screen.adapter.MessageAdapter;
import com.example.tunashopadmin.viewmodel.DisplayMessageViewModel;
import com.example.tunashopadmin.viewmodel.MessageViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private String id = "add";
    private MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chat);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.revMessage.setLayoutManager(manager);
        binding.revMessage.setHasFixedSize(true);
        binding.revMessage.getRecycledViewPool().setMaxRecycledViews(0, 0);
        MessageViewModel viewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        Intent intent = getIntent();
        String uidReceiver = intent.getStringExtra("uid");
        String imgReceiver = intent.getStringExtra("imgUser");
        String name = intent.getStringExtra("name");
        binding.nameReceiver.setText(name);
        if (!imgReceiver.isEmpty()){
            Picasso.get().load(imgReceiver).into(binding.imgReceiver);
        }
        else {
            binding.imgReceiver.setImageResource(R.drawable.user);
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String uidRec = ""+dataSnapshot.child("uidReceiver").getValue();
                    String uid = ""+dataSnapshot.child("uid").getValue();
                    assert user != null;
                    if ((uid.equals(user.getUid()) && uidRec.equals(uidReceiver)) || (uidRec.equals(user.getUid()) && uid.equals(uidReceiver))){
                        id = ""+dataSnapshot.child("id").getValue();
                        displayRev(id);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btSend.setOnClickListener(v -> {
            String message = binding.etMessage.getText().toString().trim();
            if (message.isEmpty()){
                return;
            }
            else {
                if (id.equals("add")){
                    long timestamp = System.currentTimeMillis();
                    viewModel.addMessage(timestamp,message,uidReceiver,imgReceiver,name);
                    Toast.makeText(ChatActivity.this,"add",Toast.LENGTH_SHORT).show();
                    binding.etMessage.setText("");
                }
                else {
                    long timestamp2 = System.currentTimeMillis();
                    viewModel.updateMessage(Long.parseLong(id),message,timestamp2);
                    Toast.makeText(ChatActivity.this,"up",Toast.LENGTH_SHORT).show();
                    binding.etMessage.setText("");
                }
            }
        });
        binding.btBack.setOnClickListener(v -> onBackPressed());
    }

    private void displayRev(String id) {
        DisplayMessageViewModel viewModel = new ViewModelProvider(this).get(DisplayMessageViewModel.class);
        viewModel.getMessageMutableLiveData(id).observe(this, messages -> {
            adapter = new MessageAdapter(messages);
            binding.revMessage.setAdapter(adapter);
            binding.revMessage.setHasFixedSize(true);
            binding.revMessage.scrollToPosition(adapter.getItemCount()-1);
        });
    }
}