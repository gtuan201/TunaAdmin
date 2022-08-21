package com.example.tunashopadmin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tunashopadmin.databinding.ActivityMainBinding;
import com.example.tunashopadmin.fragment.ChatFragment;
import com.example.tunashopadmin.fragment.OrderListFragment;
import com.example.tunashopadmin.fragment.VoucherFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private TextView tvName, tvShop;
    private RoundedImageView imgShop;
    OrderListFragment orderListFragment = new OrderListFragment();
    VoucherFragment voucherFragment = new VoucherFragment();
    ChatFragment chatFragment = new ChatFragment();
    Fragment active = orderListFragment;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        tvName = binding.navDrawerView.getHeaderView(0).findViewById(R.id.name_position);
        tvShop = binding.navDrawerView.getHeaderView(0).findViewById(R.id.address_shop);
        imgShop = binding.navDrawerView.getHeaderView(0).findViewById(R.id.imgShop);
        binding.toolbar.setTitle("");
        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,R.string.openDrawer,R.string.closeDrawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navDrawerView.setItemIconTintList(null);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container_fragment,orderListFragment).commit();
        replaceFragment(voucherFragment);
        replaceFragment(chatFragment);
        inforUser();
        binding.navDrawerView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.order_list:
                    fragmentManager.beginTransaction().hide(active).show(orderListFragment).commit();
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    active = orderListFragment;
                    return true;
                case R.id.voucher:
                    fragmentManager.beginTransaction().hide(active).show(voucherFragment).commit();
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    active = voucherFragment;
                    return true;
                case R.id.chat:
                    fragmentManager.beginTransaction().hide(active).show(chatFragment).commit();
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    active = chatFragment;
                    return true;
            }
            return false;
        });
    }

    private void inforUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = ""+snapshot.child("user_name").getValue();
                        String position = ""+snapshot.child("userType").getValue();
                        String shopName = ""+snapshot.child("shop").getValue();
                        tvName.setText(String.format("%s - %s", name, position));
                        tvShop.setText(shopName);
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Shop");
                        ref.child(shopName)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String imgUrl = ""+snapshot.child("ImgUrl").getValue();
                                        Picasso.get().load(imgUrl).into(imgShop);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container_fragment,fragment).hide(fragment).commit();
    }
}