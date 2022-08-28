package com.example.tunashopadmin.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.databinding.RowRevCancelBinding;
import com.example.tunashopadmin.model.Order;

import java.util.List;

public class CancelOrderAdapter extends RecyclerView.Adapter<CancelOrderAdapter.CancelOrderViewHolder>{
    private final List<Order> list;

    public CancelOrderAdapter(List<Order> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CancelOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevCancelBinding binding = RowRevCancelBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CancelOrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CancelOrderViewHolder holder, int position) {
        Order order = list.get(position);
        if (order == null){
            return;
        }
        holder.binding.idCancel.setText(String.format("Mã đơn hàng: %s", order.getId()));
        holder.binding.nameCancel.setText(String.format("Khách hàng: %s", order.getFullname()));
        holder.binding.priceCancel.setText(String.format("%sđ", order.getTotalprice()));
        holder.binding.reasonCancel.setText(String.format("Lý do hủy: %s", order.getReason()));
        holder.binding.timeCancel.setText(String.format("Thời gian hủy: %s", order.getTimeCancel()));

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public static class CancelOrderViewHolder extends RecyclerView.ViewHolder {
        private final RowRevCancelBinding binding;
        public CancelOrderViewHolder(RowRevCancelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
