package com.example.tunashopadmin.view.staff_manage_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentStaffManageBinding;
import com.example.tunashopadmin.model.User;
import com.example.tunashopadmin.view.staff_manage_screen.adapter.StaffAdapter;
import com.example.tunashopadmin.viewmodel.DisplayStaffShopViewModel;

import java.util.List;

public class StaffManageFragment extends Fragment {
    private FragmentStaffManageBinding binding;
    private StaffAdapter adapter;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_staff_manage, container, false);
        View view = binding.getRoot();
        LinearLayoutManager manager = new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false);
        binding.revStaff.setLayoutManager(manager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        DisplayStaffShopViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(DisplayStaffShopViewModel.class);
        viewModel.getListStaffMutableLiveData().observe((LifecycleOwner) requireContext(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter = new StaffAdapter(users, getContext());
                binding.revStaff.setAdapter(adapter);
            }
        });
        binding.tvCreatUser.setOnClickListener(v -> startActivity(new Intent(requireActivity(),CreatUserActivity.class)));
        return view;
    }
}