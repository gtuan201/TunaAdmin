package com.example.tunashopadmin.view.voucher_screen.child_of_voucher;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentExpiredVoucherBinding;
import com.example.tunashopadmin.databinding.FragmentPendingVoucherBinding;
import com.example.tunashopadmin.view.voucher_screen.adapter.PendingAndExpiredVoucherAdapter;
import com.example.tunashopadmin.viewmodel.DisplayVoucherViewModel;

public class ExpiredVoucherFragment extends Fragment {
    private FragmentExpiredVoucherBinding binding;
    private PendingAndExpiredVoucherAdapter adapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_expired_voucher, container, false);
        View view = binding.getRoot();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.revVoucherExpired.setLayoutManager(manager);
        DisplayVoucherViewModel viewModel = new ViewModelProvider(requireActivity()).get(DisplayVoucherViewModel.class);
        viewModel.getVoucherExpiredLiveData().observe(requireActivity(), vouchers -> {
            adapter = new PendingAndExpiredVoucherAdapter(vouchers,getContext());
            binding.revVoucherExpired.setAdapter(adapter);
        });
        return view;
    }
}