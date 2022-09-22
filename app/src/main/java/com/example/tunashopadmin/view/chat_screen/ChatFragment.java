package com.example.tunashopadmin.view.chat_screen;

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
import com.example.tunashopadmin.model.Chat;
import com.example.tunashopadmin.view.chat_screen.adapter.ChatListAdapter;
import com.example.tunashopadmin.view.chat_screen.adapter.IsOnlineAdapter;
import com.example.tunashopadmin.viewmodel.ChatViewModel;
import com.example.tunashopadmin.viewmodel.DisplayOnlineListViewModel;
import com.example.tunashopadmin.viewmodel.OfflineViewModel;

import java.util.List;

public class ChatFragment extends Fragment {
    private FragmentChatBinding binding;
    private IsOnlineAdapter adapter;
    private ChatListAdapter chatListAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat, container, false);
        View view = binding.getRoot();
        OfflineViewModel offlineViewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(OfflineViewModel.class);
        offlineViewModel.offlineUser();
        DisplayOnlineListViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(DisplayOnlineListViewModel.class);
        ChatViewModel chatViewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(ChatViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.revOnline.setHasFixedSize(true);
        binding.revOnline.setLayoutManager(manager);
        binding.revChatList.setHasFixedSize(true);
        binding.revChatList.setLayoutManager(manager2);
        viewModel.getUserOnlineMutableLiveData().observe((LifecycleOwner) requireContext(), users -> {
            adapter = new IsOnlineAdapter(users,getContext());
            binding.revOnline.setAdapter(adapter);
        });
        chatViewModel.getChatMutableLiveData().observe((LifecycleOwner) requireContext(), new Observer<List<Chat>>() {
            @Override
            public void onChanged(List<Chat> chats) {
                chatListAdapter = new ChatListAdapter(chats,getContext());
                binding.revChatList.setAdapter(chatListAdapter);
            }
        });
        return view;
    }
}