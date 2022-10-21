package com.example.tunashopadmin.view.menu_manage_screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentManageMenuBinding;
import com.example.tunashopadmin.model.Coffee;
import com.example.tunashopadmin.view.menu_manage_screen.adapter.CoffeeAdapter;
import com.example.tunashopadmin.viewmodel.DisplayCoffeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ManageMenuFragment extends Fragment {
    private FragmentManageMenuBinding binding;
    private CoffeeAdapter adapter;
    private List<Coffee> coffeeList,filterList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_manage_menu, container, false);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.revCoffee.setLayoutManager(manager);
        coffeeList = new ArrayList<>();
        DisplayCoffeeViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(DisplayCoffeeViewModel.class);
        viewModel.getCoffeeLiveData().observe((LifecycleOwner) requireContext(), coffees -> {
            coffeeList.clear();
            adapter = new CoffeeAdapter(coffees,getContext());
            binding.revCoffee.setAdapter(adapter);
            binding.revCoffee.setHasFixedSize(true);
            coffeeList.addAll(coffees);
        });
        binding.btAddCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireContext().startActivity(new Intent(getContext(),AddNewCoffeeActivity.class));
            }
        });
        binding.searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String key = s.toString().trim().toLowerCase();
                filterList = new ArrayList<>();
                if (key.length() > 0){
                    for (int i = 0 ;i < coffeeList.size();i++){
                        if (coffeeList.get(i).getCoffeeName().toLowerCase().contains(key)){
                            filterList.add(coffeeList.get(i));
                        }
                    }
                    adapter.newList(filterList);
                }
                else adapter.newList(coffeeList);
            }
        });
        return binding.getRoot();
    }
}