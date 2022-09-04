package com.example.tunashopadmin.view.add_voucher_screen.fragment_step_add_voucher;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentStepThreeBinding;
import com.example.tunashopadmin.model.Voucher;
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
        viewModel.getVoucherMutableLiveData().observe(requireActivity(), new Observer<Voucher>() {
            @Override
            public void onChanged(Voucher voucher) {

            }
        });
        return view;
    }
}