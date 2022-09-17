package com.example.tunashopadmin.view.chat_sceen;

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
import com.example.tunashopadmin.databinding.FragmentChatBinding;
import com.example.tunashopadmin.model.User;
import com.example.tunashopadmin.view.chat_sceen.adapter.IsOnlineAdapter;
import com.example.tunashopadmin.viewmodel.DisplayOnlineListViewModel;
import com.example.tunashopadmin.viewmodel.OfflineViewModel;

import java.util.List;

public class ChatFragment extends Fragment {
    private FragmentChatBinding binding;
    private IsOnlineAdapter adapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat, container, false);
        View view = binding.getRoot();
        OfflineViewModel offlineViewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(OfflineViewModel.class);
        offlineViewModel.offlineUser();
        DisplayOnlineListViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(DisplayOnlineListViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.revOnline.setHasFixedSize(true);
        binding.revOnline.setLayoutManager(manager);
        viewModel.getUserOnlineMutableLiveData().observe((LifecycleOwner) requireContext(), users -> {
            adapter = new IsOnlineAdapter(users,getContext());
            binding.revOnline.setAdapter(adapter);
        });
        return view;
    }
}