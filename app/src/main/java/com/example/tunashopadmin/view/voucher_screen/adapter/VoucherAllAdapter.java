package com.example.tunashopadmin.view.voucher_screen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tunashopadmin.databinding.RowRevVoucherAllBinding;
import com.example.tunashopadmin.model.Voucher;
import java.util.List;

public class VoucherAllAdapter extends RecyclerView.Adapter<VoucherAllAdapter.VoucherAllViewHolder>{

    private final List<Voucher> voucherList;
    private final Context context;

    public VoucherAllAdapter(List<Voucher> voucherList, Context context) {
        this.voucherList = voucherList;
        this.context = context;
    }

    @NonNull
    @Override
    public VoucherAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevVoucherAllBinding binding = RowRevVoucherAllBinding.inflate(LayoutInflater.from(context),parent,false);
        return new VoucherAllViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherAllViewHolder holder, int position) {
        Voucher voucher = voucherList.get(position);
        if (voucher == null){
            return;
        }
        holder.binding.voucherName.setText(voucher.getNameVoucher());
        if (voucher.getType().equals("percent")){
            holder.binding.valueVoucher.setText(String.format("Giảm %s%% giá trị đơn hàng", voucher.getPercent()));
        }
        else {
            holder.binding.valueVoucher.setText(String.format("Giảm %s đ", voucher.getAmount()));
        }
        holder.binding.timeStart.setText(String.format("Bắt đầu: %s", voucher.getTimeStart()));
        holder.binding.timeCancel.setText(String.format("Hết hạn: %s", voucher.getTimeCancel()));
    }

    @Override
    public int getItemCount() {
        if (voucherList != null){
            return voucherList.size();
        }
        return 0;
    }

    public static class VoucherAllViewHolder extends RecyclerView.ViewHolder {
        private final RowRevVoucherAllBinding binding;
        public VoucherAllViewHolder(RowRevVoucherAllBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
