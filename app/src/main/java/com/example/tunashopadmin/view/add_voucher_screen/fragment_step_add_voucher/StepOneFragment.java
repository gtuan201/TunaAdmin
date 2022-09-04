package com.example.tunashopadmin.view.add_voucher_screen.fragment_step_add_voucher;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentStepOneBinding;
import com.example.tunashopadmin.model.Voucher;
import com.example.tunashopadmin.view.add_voucher_screen.StepVoucherActivity;
import com.example.tunashopadmin.viewmodel.StepAddVoucherViewModel;

public class StepOneFragment extends Fragment {
    private FragmentStepOneBinding binding;
    String subject;
    private final Voucher voucher = new Voucher();
    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_step_one, container, false);
        View view = binding.getRoot();
        binding.textAll.setOnClickListener(v -> binding.rgMemOrAll.check(R.id.all));
        binding.textMember.setOnClickListener(v -> binding.rgMemOrAll.check(R.id.member));
        binding.rgMemOrAll.check(R.id.all);
        StepVoucherActivity activity = (StepVoucherActivity) getActivity();
        StepAddVoucherViewModel viewModel = new ViewModelProvider(requireActivity()).get(StepAddVoucherViewModel.class);
        subject = "all";
        binding.rgMemOrAll.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.all:
                    subject = "all";
                    break;
                case R.id.member:
                    subject = "member";
                    break;
            }
        });
        binding.btContinue.setOnClickListener(v -> {
            assert activity != null;
            activity.nextStep(0);
            activity.setTextStep("Tạo mã giảm giá - Bước 2");
            voucher.setSubject(subject);
            viewModel.setData(voucher);
        });
        return view;
    }
}