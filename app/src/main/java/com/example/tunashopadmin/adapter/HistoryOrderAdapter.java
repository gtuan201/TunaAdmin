package com.example.tunashopadmin.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.RowRevHistoryOrderBinding;
import com.example.tunashopadmin.model.Order;

import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.HistoryOrderViewHolder>{

    private final List<Order> list;

    public HistoryOrderAdapter(List<Order> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevHistoryOrderBinding binding = RowRevHistoryOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new HistoryOrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryOrderViewHolder holder, int position) {
        Order order = list.get(position);
        if (order == null){
            return;
        }
        holder.binding.nameHistory.setText(String.format("Khách hàng: %s", order.getFullname()));
        holder.binding.idHistory.setText(String.format("Mã đơn hàng: %s", order.getId()));
        holder.binding.timeHistory.setText(String.format("%s ngày %s", order.getTimeCompleteOrder(), order.getDateCComplete()));
        holder.binding.totalHistory.setText(String.format("%sđ", order.getTotalprice()));
        holder.binding.timeOrder.setText(order.getTime());
        holder.binding.dateHistory.setText(order.getDate());
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public static class HistoryOrderViewHolder extends RecyclerView.ViewHolder {
        private final RowRevHistoryOrderBinding binding;
        public HistoryOrderViewHolder(RowRevHistoryOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
