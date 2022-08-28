package com.example.tunashopadmin.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.adapter.CancelOrderAdapter;
import com.example.tunashopadmin.model.Order;
import com.example.tunashopadmin.viewmodel.OrderViewModel;

import java.util.List;

public class CancelOrderFragment extends Fragment {
    private CancelOrderAdapter adapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.example.tunashopadmin.databinding.FragmentCancelOrderBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cancel_order, container, false);
        View view = binding.getRoot();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.revCancelOrder.setLayoutManager(manager);
        OrderViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(OrderViewModel.class);
        viewModel.getCancelListMutableLiveData().observe((LifecycleOwner) requireContext(), orderList -> {
            adapter = new CancelOrderAdapter(orderList);
            binding.revCancelOrder.setAdapter(adapter);
        });
        return view;
    }
}