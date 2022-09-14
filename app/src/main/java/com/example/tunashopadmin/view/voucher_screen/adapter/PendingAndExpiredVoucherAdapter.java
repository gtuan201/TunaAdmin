package com.example.tunashopadmin.view.voucher_screen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.RowRevVoucherBinding;
import com.example.tunashopadmin.model.Voucher;
import com.google.android.material.bottomsheet.BottomSheetDialog;

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
        holder.binding.tvDetailVoucher.setOnClickListener(v -> openBottomSheet(voucher));
    }

    private void openBottomSheet(Voucher voucher) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_voucher_detail,null,false);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);
        TextView name = view.findViewById(R.id.voucher_name_sheet);
        TextView value = view.findViewById(R.id.value_voucher_sheet);
        TextView subject = view.findViewById(R.id.subject_sheet);
        TextView min = view.findViewById(R.id.min_price_sheet);
        TextView max = view.findViewById(R.id.max_price_sheet);
        TextView timeStart = view.findViewById(R.id.time_start_sheet);
        TextView timeCancel = view.findViewById(R.id.time_cancel_sheet);
        name.setText(String.format("Mã: %s", voucher.getNameVoucher()));
        min.setText(String.format("- Điều kiện áp dụng: Đơn hàng trị giá tối thiểu %s", voucher.getMinTotalPrice()));
        if (voucher.getSubject().equals("member")){
            subject.setText("- Đối tượng: Khách hàng là thành viên");
        }
        else {
            subject.setText("- Đối tượng: Toàn bộ khách hàng");
        }
        timeStart.setText(String.format("- Bắt đầu: %s", voucher.getTimeStart()));
        timeCancel.setText(String.format("- Kết thúc: %s", voucher.getTimeCancel()));
        if (voucher.getType().equals("percent")){
            max.setVisibility(View.VISIBLE);
            max.setText(String.format("- Giảm tối đa: %s", voucher.getMaxOfPercent()));
            value.setText(String.format("Giảm %s%% giá trị đơn hàng", voucher.getPercent()));
        }
        else {
            max.setVisibility(View.GONE);
            value.setText(String.format("Giảm: %sđ", voucher.getAmount()));
        }
        bottomSheetDialog.show();
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
