package com.example.tunashopadmin.view.order_detail_screen;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tunashopadmin.databinding.RowRevCoffeeCurrentBinding;
import com.example.tunashopadmin.model.Coffee;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CurrentCoffeeOrderAdapter extends RecyclerView.Adapter<CurrentCoffeeOrderAdapter.CurrentCoffeeOrderViewHolder>{
    private final List<Coffee> coffeeList;

    public CurrentCoffeeOrderAdapter(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
    }

    @NonNull
    @Override
    public CurrentCoffeeOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevCoffeeCurrentBinding binding = RowRevCoffeeCurrentBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CurrentCoffeeOrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentCoffeeOrderViewHolder holder, int position) {
        Coffee coffee = coffeeList.get(position);
        if (coffee == null){
            return;
        }
        Picasso.get().load(coffee.getUrlImg()).into(holder.binding.imgCoffeeCurrent);
        holder.binding.nameCoffeeCurrent.setText(coffee.getCoffeeName());
        holder.binding.sizeIceCurrent.setText(String.format("( Size: %s, Đá: %s )", coffee.getSize(), coffee.getIce()));
        holder.binding.quantityCurrent.setText(String.format("x%s",coffee.getQuantity()));
        holder.binding.totalPriceCurrent.setText(String.format("%sđ",coffee.getTotalPrice()));
    }

    @Override
    public int getItemCount() {
        if (coffeeList != null){
            return coffeeList.size();
        }
        return 0;
    }

    public static class CurrentCoffeeOrderViewHolder extends RecyclerView.ViewHolder {
        private final RowRevCoffeeCurrentBinding binding;
        public CurrentCoffeeOrderViewHolder(RowRevCoffeeCurrentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
