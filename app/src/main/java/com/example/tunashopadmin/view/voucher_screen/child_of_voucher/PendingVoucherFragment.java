package com.example.tunashopadmin.view.voucher_screen.child_of_voucher;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentPendingVoucherBinding;
import com.example.tunashopadmin.model.Voucher;
import com.example.tunashopadmin.view.voucher_screen.adapter.PendingAndExpiredVoucherAdapter;
import com.example.tunashopadmin.viewmodel.DisplayVoucherViewModel;

import java.util.List;

public class PendingVoucherFragment extends Fragment {

    private FragmentPendingVoucherBinding binding;
    private PendingAndExpiredVoucherAdapter adapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pending_voucher, container, false);
        View view = binding.getRoot();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.revVoucherPending.setLayoutManager(manager);
        DisplayVoucherViewModel viewModel = new ViewModelProvider(requireActivity()).get(DisplayVoucherViewModel.class);
        viewModel.getVoucherPendingLiveData().observe(requireActivity(), vouchers -> {
            adapter = new PendingAndExpiredVoucherAdapter(vouchers,getContext());
            binding.revVoucherPending.setAdapter(adapter);
        });
        return view;
    }
}