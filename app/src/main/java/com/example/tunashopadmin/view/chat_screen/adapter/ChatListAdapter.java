package com.example.tunashopadmin.view.chat_screen.adapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.RowRevChatListBinding;
import com.example.tunashopadmin.model.Chat;
import com.example.tunashopadmin.view.chat_screen.ChatActivity;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>{

    private final List<Chat> chatList;
    private final Context context;
    private String name,imgUser;

    public ChatListAdapter(List<Chat> chatList, Context context) {
        this.chatList = chatList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevChatListBinding binding = RowRevChatListBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ChatListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        if (chat == null){
            return;
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
//        holder.binding.tvLastMessage.setText(chat.getLastMessage());
        if (user.getUid().equals(chat.getUid())){
            holder.binding.tvName.setText(chat.getNameReceiver());
            if (!chat.getImgReceiver().isEmpty()){
                Picasso.get().load(chat.getImgReceiver()).into(holder.binding.img);
            }
            else {
                holder.binding.img.setImageResource(R.drawable.user);
            }
        }
        else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
            reference.child(chat.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String img = ""+snapshot.child("imgUser").getValue();
                    String name = ""+snapshot.child("user_name").getValue();
                    if (img.isEmpty()){
                        holder.binding.img.setImageResource(R.drawable.user);
                    }
                    else {
                        Picasso.get().load(chat.getImgReceiver()).into(holder.binding.img);
                    }
                    holder.binding.tvName.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.child(chat.getId()).child("lastMessage")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String lastMessage = ""+snapshot.child("lastMessage").getValue();
                        String uidLast = ""+snapshot.child("uid").getValue();
                        String status = ""+snapshot.child("Status").getValue();
                        if (user.getUid().equals(uidLast)){
                            holder.binding.tvLastMessage.setText(String.format("Bạn: %s", lastMessage));
                        }
                        else {
                            holder.binding.tvLastMessage.setText(lastMessage);
                            if (status.equals("UnSeen")){
                                holder.binding.tvLastMessage.setTextColor(Color.BLACK);
                                holder.binding.tvLastMessage.setTypeface(null, Typeface.BOLD);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("User");
        reference1.child(chat.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = ""+snapshot.child("user_name").getValue();
                imgUser = ""+snapshot.child("imgUser").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatActivity.class);
            if (user.getUid().equals(chat.getUid())){
                intent.putExtra("name",chat.getNameReceiver());
                intent.putExtra("imgUser",""+chat.getImgReceiver());
                intent.putExtra("uid",chat.getUidReceiver());
            }
            else {
                intent.putExtra("name",name);
                intent.putExtra("imgUser",""+imgUser);
                intent.putExtra("uid",chat.getUid());
            }
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (chatList != null){
            return chatList.size();
        }
        return 0;
    }

    public static class ChatListViewHolder extends RecyclerView.ViewHolder {
        private final RowRevChatListBinding binding;
        public ChatListViewHolder(RowRevChatListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
