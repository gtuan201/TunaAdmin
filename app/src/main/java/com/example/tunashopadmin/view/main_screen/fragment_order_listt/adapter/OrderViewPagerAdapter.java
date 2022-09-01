package com.example.tunashopadmin.view.main_screen.fragment_order_listt.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tunashopadmin.view.main_screen.fragment_order_listt.child_of_order_list.CancelOrderFragment;
import com.example.tunashopadmin.view.main_screen.fragment_order_listt.child_of_order_list.CurrentOrderFragment;
import com.example.tunashopadmin.view.main_screen.fragment_order_listt.child_of_order_list.OlderOrderFragment;

public class OrderViewPagerAdapter extends FragmentStateAdapter {
    public OrderViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new CancelOrderFragment();
            case 2:
                return new OlderOrderFragment();
            case 0:
            default:return new CurrentOrderFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
