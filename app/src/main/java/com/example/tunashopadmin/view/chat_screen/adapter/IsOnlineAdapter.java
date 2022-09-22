package com.example.tunashopadmin.view.chat_screen.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.RowRevOnlineBinding;
import com.example.tunashopadmin.model.User;
import com.example.tunashopadmin.view.chat_screen.ChatActivity;
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

public class IsOnlineAdapter extends RecyclerView.Adapter<IsOnlineAdapter.IsOnlineViewHolder>{

    private final List<User> userList;
    private final Context context;

    public IsOnlineAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public IsOnlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevOnlineBinding binding = RowRevOnlineBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new IsOnlineViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IsOnlineViewHolder holder, int position) {
        User user = userList.get(position);
        if (user == null){
            return;
        }
        MessageViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MessageViewModel.class);
        if (!user.getImgUrl().isEmpty()){
            Picasso.get().load(user.getImgUrl()).into(holder.binding.imgUser);
        }
        else {
            holder.binding.imgUser.setImageResource(R.drawable.user);
        }
        if (user.getOnline().equals("online")){
            holder.binding.imgOnline.setVisibility(View.VISIBLE);
        }
        else {
            holder.binding.imgOnline.setVisibility(View.GONE);
        }
        holder.binding.name.setText(user.getName());
        holder.binding.imgUser.setOnClickListener(v -> {
//            long timestamp = System.currentTimeMillis();
//            viewModel.creatRoom(timestamp,user.getUid(),user.getImgUrl(),user.getName());
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("name",user.getName());
            intent.putExtra("imgUser",user.getImgUrl());
            intent.putExtra("uid",user.getUid());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (userList != null){
            return userList.size();
        }
        return 0;
    }

    public static class IsOnlineViewHolder extends RecyclerView.ViewHolder {
        private final RowRevOnlineBinding binding;
        public IsOnlineViewHolder(RowRevOnlineBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
