package com.example.tunashopadmin.view.add_voucher_screen.fragment_step_add_voucher;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentStepTwoBinding;
import com.example.tunashopadmin.model.Voucher;
import com.example.tunashopadmin.view.add_voucher_screen.StepVoucherActivity;
import com.example.tunashopadmin.viewmodel.StepAddVoucherViewModel;

public class StepTwoFragment extends Fragment {

    private FragmentStepTwoBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_step_two, container, false);
        View view = binding.getRoot();
        binding.etAmount.setOnFocusChangeListener((v, hasFocus) -> binding.etAmount.setBackgroundResource(R.drawable.edit_text_custom3));
        binding.etMinTotalPrice.setOnFocusChangeListener((v, hasFocus) -> binding.etMinTotalPrice.setBackgroundResource(R.drawable.edit_text_custom3));
        binding.etPercent.setOnFocusChangeListener((v, hasFocus) -> binding.etPercent.setBackgroundResource(R.drawable.edit_text_custom3));
        binding.etMaxOfPercent.setOnFocusChangeListener((v, hasFocus) -> binding.etMaxOfPercent.setBackgroundResource(R.drawable.edit_text_custom3));
        StepVoucherActivity activity = (StepVoucherActivity) getActivity();
        assert activity != null;
        String type = activity.getType();
        if (type.equals("percent")){
            binding.layoutPercent.setVisibility(View.VISIBLE);
            binding.layoutMax.setVisibility(View.VISIBLE);
            binding.layoutMoney.setVisibility(View.GONE);
        }
        else {
            binding.layoutPercent.setVisibility(View.GONE);
            binding.layoutMax.setVisibility(View.GONE);
            binding.layoutMoney.setVisibility(View.VISIBLE);
        }
        StepAddVoucherViewModel viewModel = new ViewModelProvider(requireActivity()).get(StepAddVoucherViewModel.class);
        binding.btContinue.setOnClickListener(v -> {
            if (type.equals("percent")){
                String percent = binding.etPercent.getText().toString().trim();
                String max = binding.etMaxOfPercent.getText().toString().trim();
                String min = binding.etMinTotalPrice.getText().toString().trim();
                if (TextUtils.isEmpty(percent)){
                    binding.tvWarning2.setVisibility(View.VISIBLE);
                    binding.etPercent.setBackgroundResource(R.drawable.edit_text_custom4);
                }
                else {
                    binding.tvWarning2.setVisibility(View.GONE);
                    binding.etPercent.setBackgroundResource(R.drawable.edit_text_custom3);
                    viewModel.getVoucherMutableLiveData().observe(requireActivity(), voucher -> {
                        voucher.setPercent(percent);
                        voucher.setMaxOfPercent(max);
                        voucher.setMinTotalPrice(min);
                        voucher.setType(type);
                        activity.nextStep(1);
                    });
                }
            }
            else {
                String amount = binding.etAmount.getText().toString().trim();
                String min = binding.etMinTotalPrice.getText().toString().trim();
                if (TextUtils.isEmpty(amount)){
                    binding.tvWarning1.setVisibility(View.VISIBLE);
                    binding.etAmount.setBackgroundResource(R.drawable.edit_text_custom4);
                }
                else {
                    binding.tvWarning1.setVisibility(View.GONE);
                    binding.etAmount.setBackgroundResource(R.drawable.edit_text_custom3);
                    viewModel.getVoucherMutableLiveData().observe(requireActivity(), voucher -> {
                        voucher.setAmount(amount);
                        voucher.setMinTotalPrice(min);
                        voucher.setType(type);
                        activity.nextStep(1);
                    });
                }
            }
        });
        return view;
    }
}