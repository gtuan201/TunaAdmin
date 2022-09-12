package com.example.tunashopadmin.view.voucher_screen.child_of_voucher;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentOnGoingVoucherBinding;
import com.example.tunashopadmin.model.Voucher;
import com.example.tunashopadmin.view.voucher_screen.adapter.VoucherAllAdapter;
import com.example.tunashopadmin.view.voucher_screen.adapter.VoucherMemberAdapter;
import com.example.tunashopadmin.viewmodel.DisplayVoucherViewModel;

import java.util.List;

public class OnGoingVoucherFragment extends Fragment {
    private VoucherMemberAdapter adapter;
    private VoucherAllAdapter allAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentOnGoingVoucherBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_on_going_voucher, container, false);
        View view = binding.getRoot();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.revVoucherMember.setLayoutManager(manager);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        binding.revVoucherAll.setLayoutManager(layoutManager);
        DisplayVoucherViewModel viewModel = new ViewModelProvider(requireActivity()).get(DisplayVoucherViewModel.class);
        viewModel.getVoucherMemberLiveData().observe(requireActivity(), vouchers -> {
            adapter = new VoucherMemberAdapter(vouchers,requireContext());
            binding.revVoucherMember.setAdapter(adapter);
            binding.revVoucherMember.setHasFixedSize(true);
        });
        viewModel.getVoucherAllLiveData().observe(requireActivity(), vouchers -> {
            allAdapter = new VoucherAllAdapter(vouchers,requireContext());
            binding.revVoucherAll.setAdapter(allAdapter);
        });
        return view;
    }
}