package com.example.tunashopadmin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.OrderDetailActivity;
import com.example.tunashopadmin.R;
import com.example.tunashopadmin.model.Order;

import java.lang.invoke.LambdaConversionException;
import java.util.List;

public class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.CurrentOrderViewHolder>{

    private final List<Order> orderList;
    private final Context context;

    public CurrentOrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public CurrentOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_rev_current,parent,false);
        return new CurrentOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentOrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        if (order==null){
            return;
        }
        holder.name.setText(String.format("Khách hàng: %s", order.getFullname()));
        holder.id.setText(String.format("Mã đơn hàng: %s", order.getId()));
        holder.address.setText(String.format("Địa chỉ: %s", order.getAddress()));
        holder.time.setText(order.getTime());
        holder.date.setText(order.getDate());
        holder.totalPrice.setText(String.format("%sđ", order.getTotalprice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("id",order.getId());
                intent.putExtra("uid",order.getUid());
                intent.putExtra("name",order.getFullname());
                intent.putExtra("address",order.getAddress());
                intent.putExtra("phoneNumber",order.getPhoneNumber());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (orderList != null){
            return orderList.size();
        }
        return 0;
    }

    public static class CurrentOrderViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView id;
        private final TextView address;
        private final TextView time;
        private final TextView date;
        private final TextView totalPrice;
        public CurrentOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_current);
            id = itemView.findViewById(R.id.id_current);
            address = itemView.findViewById(R.id.address_current);
            time = itemView.findViewById(R.id.time_current);
            date = itemView.findViewById(R.id.date_current);
            totalPrice = itemView.findViewById(R.id.total_current);
        }
    }
}
