package com.example.tunashopadmin.fragment;

import android.content.Intent;
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

import com.example.tunashopadmin.OrderDetailActivity;
import com.example.tunashopadmin.R;
import com.example.tunashopadmin.adapter.CurrentOrderAdapter;
import com.example.tunashopadmin.databinding.FragmentCurrentOrderBinding;
import com.example.tunashopadmin.model.Order;
import com.example.tunashopadmin.viewmodel.OrderViewModel;

import java.util.List;
import java.util.Objects;

public class CurrentOrderFragment extends Fragment {
    private FragmentCurrentOrderBinding binding;
    private CurrentOrderAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_current_order, container, false);
        View view = binding.getRoot();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.revCurrentOrder.setLayoutManager(manager);
        OrderViewModel orderViewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(OrderViewModel.class);
        orderViewModel.getListMutableLiveData().observe((LifecycleOwner) requireContext(), orders -> {
            adapter = new CurrentOrderAdapter(orders,getContext());
            binding.revCurrentOrder.setAdapter(adapter);
        });
        return view;
    }
}