package com.example.tunashopadmin.view.add_voucher_screen.fragment_step_add_voucher;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentStepThreeBinding;
import com.example.tunashopadmin.view.preview_voucher_screen.PreviewVoucherActivity;
import com.example.tunashopadmin.viewmodel.StepAddVoucherViewModel;

public class StepThreeFragment extends Fragment {
    private FragmentStepThreeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_step_three, container, false);
        View view = binding.getRoot();
        StepAddVoucherViewModel viewModel = new ViewModelProvider(requireActivity()).get(StepAddVoucherViewModel.class);
        binding.tvSelectTimeStart.setOnClickListener(v -> openDialogPicker(binding.tvSelectTimeStart));
        binding.tvSelectTimeCancel.setOnClickListener(v -> openDialogPicker(binding.tvSelectTimeCancel));
        binding.btPreview.setOnClickListener(v -> {
            String timeStart = binding.tvSelectTimeStart.getText().toString().trim();
            String timeCancel = binding.tvSelectTimeCancel.getText().toString().trim();
            String nameVoucher = binding.etNameVoucher.getText().toString().trim();
            if (!timeCancel.equals("Chọn ngày và thời gian") && !timeStart.equals("Chọn ngày và thời gian") && !TextUtils.isEmpty(nameVoucher)){
                viewModel.getVoucherMutableLiveData().observe(requireActivity(), voucher -> {
                    voucher.setTimeStart(timeStart);
                    voucher.setTimeCancel(timeCancel);
                    voucher.setNameVoucher(nameVoucher);
                    Intent intent = new Intent(requireActivity(), PreviewVoucherActivity.class);
                    intent.putExtra("nameVoucher",voucher.getNameVoucher());
                    intent.putExtra("type",voucher.getType());
                    intent.putExtra("object",voucher.getSubject());
                    intent.putExtra("percent",voucher.getPercent());
                    intent.putExtra("amount",voucher.getAmount());
                    intent.putExtra("minTotalPrice",voucher.getMinTotalPrice());
                    intent.putExtra("maxPrice",voucher.getMaxOfPercent());
                    intent.putExtra("timeStart",voucher.getTimeStart());
                    intent.putExtra("timeCancel",voucher.getTimeCancel());
                    if (isVisible()){
                        requireActivity().startActivity(intent);
                    }
                });
            }
            else {
                Toast.makeText(getContext(),"Bạn chưa hoàn thành bước này !",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void openDialogPicker(TextView textView) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.date_and_time_picker);
        Window window = dialog.getWindow();
        if (window==null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);
        TimePicker  tp = dialog.findViewById(R.id.time_picker);
        DatePicker dp = dialog.findViewById(R.id.date_picker);
        AppCompatButton btOk = dialog.findViewById(R.id.bt_ok);
        tp.setIs24HourView(true);
        btOk.setOnClickListener(v -> {
            String strDateTime = dp.getDayOfMonth() + "-" + (dp.getMonth() + 1) + "-" + dp.getYear() + " "+ tp.getCurrentHour() + ":" + tp.getCurrentMinute();
            textView.setText(strDateTime);
            dialog.dismiss();
        });
        dialog.show();
    }
}