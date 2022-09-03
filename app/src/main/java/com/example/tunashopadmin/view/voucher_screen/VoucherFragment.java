package com.example.tunashopadmin.view.voucher_screen;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentVoucherBinding;
import com.example.tunashopadmin.view.voucher_screen.adapter.VoucherViewPagerAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class VoucherFragment extends Fragment {
    private FragmentVoucherBinding binding;
    private VoucherViewPagerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_voucher, container, false);
        View view = binding.getRoot();
        adapter = new VoucherViewPagerAdapter(getActivity());
        binding.viewpager2Voucher.setAdapter(adapter);
        new TabLayoutMediator(binding.tabVoucher, binding.viewpager2Voucher, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Đang áp dụng");
                    break;
                case 1:
                    tab.setText("Sắp tới");
                    break;
                case 2:
                    tab.setText("Hết hiệu lực");
                    break;
            }
        }).attach();
        return view;
    }
}