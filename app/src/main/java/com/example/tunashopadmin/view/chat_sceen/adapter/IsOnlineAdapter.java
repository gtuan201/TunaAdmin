package com.example.tunashopadmin.view.chat_sceen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.RowRevOnlineBinding;
import com.example.tunashopadmin.model.User;
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
        if (!user.getImgUrl().isEmpty()){
            Picasso.get().load(user.getImgUrl()).into(holder.binding.imgUser);
        }
        else {
            holder.binding.imgUser.setImageResource(R.drawable.user);
        }
        holder.binding.name.setText(user.getName());
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
