package com.example.tunashopadmin.view.chat_screen.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.RowRevChatListBinding;
import com.example.tunashopadmin.model.Chat;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>{

    private final List<Chat> chatList;
    private final Context context;

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
        if (!chat.getImgReceiver().isEmpty()){
            Picasso.get().load(chat.getImgReceiver()).into(holder.binding.img);
        }
        else {
            holder.binding.img.setImageResource(R.drawable.user);
        }
        holder.binding.tvName.setText(chat.getNameReceiver());
        holder.binding.tvLastMessage.setText(chat.getLastMessage());
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
