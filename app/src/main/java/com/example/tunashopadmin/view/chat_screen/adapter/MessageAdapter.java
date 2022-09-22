package com.example.tunashopadmin.view.chat_screen.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tunashopadmin.databinding.RowRevMessageBinding;
import com.example.tunashopadmin.model.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private final List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevMessageBinding binding = RowRevMessageBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MessageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (message == null){
            return;
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        if (message.getSenderID().equals(user.getUid())){
            holder.binding.message.setVisibility(View.GONE);
            holder.binding.messageOfSender.setText(message.getMessage());
        }
        else {
            holder.binding.messageOfSender.setVisibility(View.GONE);
            holder.binding.message.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        if (messageList != null){
            return messageList.size();
        }
        return 0;
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        private RowRevMessageBinding binding;
        public MessageViewHolder(RowRevMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
