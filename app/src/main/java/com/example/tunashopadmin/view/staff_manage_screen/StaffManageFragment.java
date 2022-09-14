package com.example.tunashopadmin.view.staff_manage_screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentStaffManageBinding;
import com.example.tunashopadmin.view.login_signup_screen.LoginActivity;
import com.example.tunashopadmin.viewmodel.LoginViewModel;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StaffManageFragment extends Fragment {
    private FragmentStaffManageBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_staff_manage, container, false);
        View view = binding.getRoot();
        binding.tvCreatUser.setOnClickListener(v -> startActivity(new Intent(requireActivity(),CreatUserActivity.class)));
        LoginViewModel viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        binding.tvAdmin.setOnClickListener(v -> {
            viewModel.signOut();
            startActivity(new Intent(requireActivity(), LoginActivity.class));
            requireActivity().finishAffinity();
        });
        return view;
    }
}