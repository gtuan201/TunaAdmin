package com.example.tunashopadmin.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.adapter.HistoryOrderAdapter;
import com.example.tunashopadmin.databinding.FragmentOlderOrderBinding;
import com.example.tunashopadmin.viewmodel.OrderViewModel;

import java.util.List;

public class OlderOrderFragment extends Fragment {
    private HistoryOrderAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentOlderOrderBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_older_order, container, false);
        View view = binding.getRoot();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.revHistoryOrder.setLayoutManager(manager);
        OrderViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(OrderViewModel.class);
        viewModel.getHistoryOrderMutableLiveData().observe((LifecycleOwner) requireContext(), orderList -> {
            adapter = new HistoryOrderAdapter(orderList);
            binding.revHistoryOrder.setAdapter(adapter);
        });
        return view;
    }
}