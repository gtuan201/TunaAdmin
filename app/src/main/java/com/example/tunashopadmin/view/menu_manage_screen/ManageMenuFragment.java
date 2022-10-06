package com.example.tunashopadmin.view.menu_manage_screen;

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
import com.example.tunashopadmin.databinding.FragmentManageMenuBinding;
import com.example.tunashopadmin.model.Coffee;
import com.example.tunashopadmin.view.menu_manage_screen.adapter.CoffeeAdapter;
import com.example.tunashopadmin.viewmodel.DisplayCoffeeViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ManageMenuFragment extends Fragment {
    private FragmentManageMenuBinding binding;
    private CoffeeAdapter adapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_manage_menu, container, false);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.revCoffee.setLayoutManager(manager);
        DisplayCoffeeViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(DisplayCoffeeViewModel.class);
        viewModel.getCoffeeLiveData().observe((LifecycleOwner) requireContext(), coffees -> {
            adapter = new CoffeeAdapter(coffees,getContext());
            binding.revCoffee.setAdapter(adapter);
            binding.revCoffee.setHasFixedSize(true);
        });
        return binding.getRoot();
    }
}