package com.example.tunashopadmin.view.add_voucher_screen.fragment_step_add_voucher;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.FragmentStepOneBinding;

public class StepOneFragment extends Fragment {
    private FragmentStepOneBinding binding;
    String subject;
    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_step_one, container, false);
        View view = binding.getRoot();
        binding.textAll.setOnClickListener(v -> binding.rgMemOrAll.check(R.id.all));
        binding.textMember.setOnClickListener(v -> binding.rgMemOrAll.check(R.id.member));
        binding.rgMemOrAll.check(R.id.all);
        subject = "all";
        binding.rgMemOrAll.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.all:
                    subject = "all";
                    break;
                case R.id.member:
                    subject = "member";
                    break;
            }
        });
        return view;
    }
}