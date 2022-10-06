package com.example.tunashopadmin.view.menu_manage_screen.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.databinding.RowRevCoffeeBinding;
import com.example.tunashopadmin.model.Coffee;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>{

    private final List<Coffee> list;
    private final Context context;

    public CoffeeAdapter(List<Coffee> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevCoffeeBinding binding = RowRevCoffeeBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CoffeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        Coffee coffee = list.get(position);
        if (coffee == null){
            return;
        }
        Picasso.get().load(coffee.getUrlImg()).into(holder.binding.imgCoffee);
        holder.binding.nameCoffee.setText(coffee.getCoffeeName());
        holder.binding.categoryCoffee.setText(coffee.getCategory());
        holder.binding.priceCoffee.setText(String.format("%sđ", coffee.getPrice()));
        holder.binding.statusCoffee.setText(coffee.getStatus());
        if (coffee.getStatus().equals("Đang bán")){
            holder.binding.statusCoffee.setTextColor(Color.GREEN);
        }
        else  holder.binding.statusCoffee.setTextColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public static class CoffeeViewHolder extends RecyclerView.ViewHolder {
        private final RowRevCoffeeBinding binding;
        public CoffeeViewHolder(RowRevCoffeeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
