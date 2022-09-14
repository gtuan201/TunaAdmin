package com.example.tunashopadmin.view.staff_manage_screen.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tunashopadmin.OnItemClickListener;
import com.example.tunashopadmin.databinding.RowRevShopBinding;
import com.example.tunashopadmin.model.Shop;
import com.squareup.picasso.Picasso;
import java.util.List;

public class BottomSheetShopAdapter extends RecyclerView.Adapter<BottomSheetShopAdapter.BottomSheetShopViewHolder> {

    private final List<Shop> shopList;
    private final OnItemClickListener listener;
    public BottomSheetShopAdapter(List<Shop> shopList, OnItemClickListener listener) {
        this.shopList = shopList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BottomSheetShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevShopBinding binding = RowRevShopBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new BottomSheetShopViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomSheetShopViewHolder holder, int position) {
        Shop shop = shopList.get(position);
        if (shop == null){
            return;
        }
        Picasso.get().load(shop.getImgUrlShop()).into(holder.binding.imgShop);
        holder.binding.tvShopName.setText(shop.getName());
        holder.binding.tvAddressShop.setText(shop.getAddress());
        if (listener != null){
            holder.itemView.setOnClickListener(v -> listener.OnClickItem(shop));
        }
    }

    @Override
    public int getItemCount() {
        if (shopList != null){
            return  shopList.size();
        }
        return 0;
    }

    public static class BottomSheetShopViewHolder extends RecyclerView.ViewHolder {
        private final RowRevShopBinding binding;
        public BottomSheetShopViewHolder(RowRevShopBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
