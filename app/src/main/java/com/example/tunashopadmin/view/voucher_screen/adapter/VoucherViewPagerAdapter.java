package com.example.tunashopadmin.view.voucher_screen.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tunashopadmin.view.voucher_screen.child_of_voucher.ExpiredVoucherFragment;
import com.example.tunashopadmin.view.voucher_screen.child_of_voucher.OnGoingVoucherFragment;
import com.example.tunashopadmin.view.voucher_screen.child_of_voucher.PendingVoucherFragment;

public class VoucherViewPagerAdapter extends FragmentStateAdapter {
    public VoucherViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new PendingVoucherFragment();
            case 2:
                return new ExpiredVoucherFragment();
            case 0:
            default:
                return new OnGoingVoucherFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
