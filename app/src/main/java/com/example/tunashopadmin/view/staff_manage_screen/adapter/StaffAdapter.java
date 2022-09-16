package com.example.tunashopadmin.view.staff_manage_screen.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.RowRevStaffBinding;
import com.example.tunashopadmin.model.User;
import com.example.tunashopadmin.view.staff_manage_screen.UpdateStaffActivity;
import com.example.tunashopadmin.viewmodel.ManageStaffViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder>{

    private final List<User> list;
    private final Context context;
    public StaffAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevStaffBinding binding = RowRevStaffBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new StaffViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        User user = list.get(position);
        if (user == null){
            return;
        }
        ManageStaffViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ManageStaffViewModel.class);
        if (!TextUtils.isEmpty(user.getImgUrl())){
            Picasso.get().load(user.getImgUrl()).into(holder.binding.imgStaff);
        }
        else {
            holder.binding.imgStaff.setImageResource(R.drawable.user);
        }
        holder.binding.name.setText(user.getName());
        switch (user.getTypeUser()) {
            case "shipper":
                holder.binding.level.setText("( Nhân viên giao hàng )");
                break;
            case "staff":
                holder.binding.level.setText("( Nhân viên bán hàng )");
                break;
            case "manager":
                holder.binding.level.setText("( Quản lý cửa hàng )");
                break;
        }
        holder.binding.address.setText(user.getShopManage());
        holder.binding.phone.setText(user.getPhone());
        holder.binding.btDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Bạn có chắc muốn xóa không?")
                    .setNegativeButton("Không", (dialog, which) -> dialog.dismiss())
                    .setPositiveButton("Có", (dialog, which) -> {
                        viewModel.removeStaff(user.getUid());
                        dialog.dismiss();
                    })
                    .show();
        });
        holder.binding.btUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateStaffActivity.class);
            intent.putExtra("uid", user.getUid());
            intent.putExtra("name",user.getName());
            intent.putExtra("address",user.getShopManage());
            intent.putExtra("level",user.getTypeUser());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public static class StaffViewHolder extends RecyclerView.ViewHolder {
        private final RowRevStaffBinding binding;
        public StaffViewHolder(RowRevStaffBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
