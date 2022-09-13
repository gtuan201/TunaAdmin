package com.example.tunashopadmin.view.voucher_screen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.databinding.RowRevVoucherBinding;
import com.example.tunashopadmin.model.Voucher;

import java.util.List;

public class PendingAndExpiredVoucherAdapter extends RecyclerView.Adapter<PendingAndExpiredVoucherAdapter.PendingAndExpiredVoucherViewHolder>{

    private final List<Voucher> voucherList;
    private final Context context;

    public PendingAndExpiredVoucherAdapter(List<Voucher> voucherList, Context context) {
        this.voucherList = voucherList;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingAndExpiredVoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRevVoucherBinding binding = RowRevVoucherBinding.inflate(LayoutInflater.from(context),parent,false);
        return new PendingAndExpiredVoucherViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PendingAndExpiredVoucherViewHolder holder, int position) {
        Voucher voucher = voucherList.get(position);
        if (voucher == null){
            return;
        }
        holder.binding.nameVoucher.setText(voucher.getNameVoucher());
        if (voucher.getSubject().equals("member")){
            holder.binding.subject.setText("Đối tượng: Khách hàng là thành viên");
        }
        else {
            holder.binding.subject.setText("Đối tượng: Toàn bộ cửa hàng");
        }
        if (voucher.getType().equals("percent")){
            holder.binding.value.setText(String.format("Giảm %s%% giá trị đơn hàng", voucher.getPercent()));
        }
        else {
            holder.binding.value.setText(String.format("Giảm %sđ", voucher.getAmount()));
        }
        if (voucher.getStatus().equals("isComing")){
            holder.binding.isComingOrCanceled.setText(String.format("Sắp tới: %s", voucher.getTimeStart()));
        }
        else {
            holder.binding.isComingOrCanceled.setText(String.format("Đã hết hạn vào: %s", voucher.getTimeCancel()));
        }
    }

    @Override
    public int getItemCount() {
        if (voucherList != null){
            return voucherList.size();
        }
        return 0;
    }

    public static class PendingAndExpiredVoucherViewHolder extends RecyclerView.ViewHolder {
        private final RowRevVoucherBinding binding;
        public PendingAndExpiredVoucherViewHolder(RowRevVoucherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
