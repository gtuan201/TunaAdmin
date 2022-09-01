package com.example.tunashopadmin.view.main_screen.fragment_order_listt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.view.main_screen.fragment_order_listt.adapter.OrderViewPagerAdapter;
import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentOrderListBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class OrderListFragment extends Fragment {
    FragmentOrderListBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_order_list, container, false);
        View view = binding.getRoot();
        OrderViewPagerAdapter adapter = new OrderViewPagerAdapter(getActivity());
        binding.viewpager2Order.setAdapter(adapter);
        new TabLayoutMediator(binding.tabOrder, binding.viewpager2Order, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Đã đặt");
                    break;
                case 1:
                    tab.setText("Đã hủy");
                    break;
                case 2:
                    tab.setText("Lịch sử");
                    break;
            }
        }).attach();
        return view;
    }
}