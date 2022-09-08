package com.example.tunashopadmin.view.voucher_screen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tunashopadmin.databinding.RowRevVoucherMemberBinding;
import com.example.tunashopadmin.model.Voucher;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VoucherMemberAdapter extends RecyclerView.Adapter<VoucherMemberAdapter.VoucherMemberViewHolder>{

    private final List<Voucher> voucherList;
    private final Context context;

    public VoucherMemberAdapter(List<Voucher> voucherList, Context context) {
        this.voucherList = voucherList;
        this.context = context;
    }

    @NonNull
    @Override
    public VoucherMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevVoucherMemberBinding binding = RowRevVoucherMemberBinding.inflate(LayoutInflater.from(context),parent,false);
        return new VoucherMemberViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherMemberViewHolder holder, int position) {
        Voucher voucher = voucherList.get(position);
        if (voucher == null){
            return;
        }
        holder.binding.itemMinPrice.setText(String.format("Giá trị tối thiểu: %sđ", voucher.getMinTotalPrice()));
        holder.binding.itemTimeCancel.setText(String.format("Hết hạn: %s", voucher.getTimeCancel()));
        if (voucher.getType().equals("percent")){
            holder.binding.itemNameVoucher.setText(String.format("Giảm %s%% giá trị đơn hàng", voucher.getPercent()));
            holder.binding.imgVoucher.setText(String.format("%s%%", voucher.getPercent()));
        }
        else {
            String amount = voucher.getAmount();
            int intAmount = Integer.parseInt(amount);
            holder.binding.itemNameVoucher.setText(String.format("Giảm %sđ", amount));
            if (10000 <= intAmount && intAmount <1000000){
                intAmount = intAmount / 1000;
                holder.binding.imgVoucher.setText(String.format("%sK", intAmount));
            }
            else if (intAmount >= 1000000){
                intAmount = intAmount / 1000000;
                holder.binding.imgVoucher.setText(String.format("%str", intAmount));
            }
        }
    }

    @Override
    public int getItemCount() {
        if (voucherList != null){
            return voucherList.size();
        }
        return 0;
    }

    public static class VoucherMemberViewHolder extends RecyclerView.ViewHolder {
        private final RowRevVoucherMemberBinding binding;
        public VoucherMemberViewHolder(RowRevVoucherMemberBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
