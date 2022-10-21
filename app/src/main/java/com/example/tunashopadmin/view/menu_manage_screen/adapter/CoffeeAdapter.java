package com.example.tunashopadmin.view.menu_manage_screen.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.databinding.RowRevCoffeeBinding;
import com.example.tunashopadmin.model.Coffee;
import com.example.tunashopadmin.view.menu_manage_screen.CoffeeDetailActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>{

    private List<Coffee> list;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoffeeDetailActivity.class);
                intent.putExtra("img",coffee.getUrlImg());
                intent.putExtra("background",coffee.getBackground());
                intent.putExtra("name",coffee.getCoffeeName());
                intent.putExtra("category",coffee.getCategory());
                intent.putExtra("price",coffee.getPrice());
                intent.putExtra("description",coffee.getDescription());
                intent.putExtra("status",coffee.getStatus());
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Bạn có muốn xóa thức uống này không?")
                    .setMessage("Sẽ không thể hoàn tác")
                    .setPositiveButton("Có", (dialog, which) -> {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Coffee");
                        reference.child(coffee.getCoffeeName()).removeValue((error, ref) -> {
                            Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        });
                    })
                    .setNegativeButton("Hủy", (dialog, which) -> dialog.cancel())
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void newList(List<Coffee> filterList) {
        list = filterList;
        notifyDataSetChanged();
    }

    public static class CoffeeViewHolder extends RecyclerView.ViewHolder {
        private final RowRevCoffeeBinding binding;
        public CoffeeViewHolder(RowRevCoffeeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
