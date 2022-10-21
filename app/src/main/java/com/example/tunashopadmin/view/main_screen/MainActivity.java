package com.example.tunashopadmin.view.main_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityMainBinding;
import com.example.tunashopadmin.view.add_voucher_screen.AddVoucherActivity;
import com.example.tunashopadmin.view.chat_screen.ChatFragment;
import com.example.tunashopadmin.view.main_screen.fragment_order_listt.OrderListFragment;
import com.example.tunashopadmin.view.menu_manage_screen.ManageMenuFragment;
import com.example.tunashopadmin.view.staff_manage_screen.StaffManageFragment;
import com.example.tunashopadmin.view.voucher_screen.VoucherFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    StaffManageFragment staffManageFragment = new StaffManageFragment();
    ManageMenuFragment manageMenuFragment = new ManageMenuFragment();
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
        fragmentManager.beginTransaction().add(R.id.container_fragment,orderListFragment,"0").commit();
        fragmentManager.beginTransaction().add(R.id.container_fragment,voucherFragment,"1").hide(voucherFragment).commit();
        fragmentManager.beginTransaction().add(R.id.container_fragment,chatFragment,"2").hide(chatFragment).commit();
        fragmentManager.beginTransaction().add(R.id.container_fragment,staffManageFragment,"4").hide(staffManageFragment).commit();
        fragmentManager.beginTransaction().add(R.id.container_fragment,manageMenuFragment,"5").hide(manageMenuFragment).commit();
        binding.navDrawerView.getMenu().findItem(R.id.order_list).setChecked(true);
        inforUser();
        binding.navDrawerView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.voucher:
                    fragmentManager.beginTransaction().hide(active).show(voucherFragment).commit();
                    binding.titleMenuManage.setVisibility(View.GONE);
                    binding.searchOrderView.setVisibility(View.GONE);
                    binding.btHelp.setVisibility(View.GONE);
                    binding.titleStaffManage.setVisibility(View.GONE);
                    binding.titleChat.setVisibility(View.GONE);
                    binding.btAddVoucher.setVisibility(View.VISIBLE);
                    binding.titleVoucher.setVisibility(View.VISIBLE);
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    binding.navDrawerView.getMenu().findItem(R.id.order_list).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.staff_manager).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.menu_manager).setChecked(false);
                    active = voucherFragment;
                    return true;
                case R.id.chat:
                    fragmentManager.beginTransaction().hide(active).show(chatFragment).commit();
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    binding.titleMenuManage.setVisibility(View.GONE);
                    binding.searchOrderView.setVisibility(View.GONE);
                    binding.btHelp.setVisibility(View.GONE);
                    binding.btAddVoucher.setVisibility(View.GONE);
                    binding.titleVoucher.setVisibility(View.GONE);
                    binding.titleStaffManage.setVisibility(View.GONE);
                    binding.titleChat.setVisibility(View.VISIBLE);
                    binding.navDrawerView.getMenu().findItem(R.id.order_list).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.staff_manager).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.chat).setChecked(true);
                    binding.navDrawerView.getMenu().findItem(R.id.voucher).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.wallet).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.menu_manager).setChecked(false);
                    active = chatFragment;
                    return true;
                case R.id.staff_manager:
                    fragmentManager.beginTransaction().hide(active).show(staffManageFragment).commit();
                    binding.titleMenuManage.setVisibility(View.GONE);
                    binding.searchOrderView.setVisibility(View.GONE);
                    binding.btHelp.setVisibility(View.GONE);
                    binding.btAddVoucher.setVisibility(View.GONE);
                    binding.titleVoucher.setVisibility(View.GONE);
                    binding.titleChat.setVisibility(View.GONE);
                    binding.titleStaffManage.setVisibility(View.VISIBLE);
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    binding.navDrawerView.getMenu().findItem(R.id.staff_manager).setChecked(true);
                    binding.navDrawerView.getMenu().findItem(R.id.order_list).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.chat).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.voucher).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.wallet).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.menu_manager).setChecked(false);
                    active = staffManageFragment;
                    return true;
                case R.id.menu_manager:
                    fragmentManager.beginTransaction().hide(active).show(manageMenuFragment).commit();
                    binding.titleMenuManage.setVisibility(View.VISIBLE);
                    binding.searchOrderView.setVisibility(View.GONE);
                    binding.btHelp.setVisibility(View.GONE);
                    binding.btAddVoucher.setVisibility(View.GONE);
                    binding.titleVoucher.setVisibility(View.GONE);
                    binding.titleChat.setVisibility(View.GONE);
                    binding.titleStaffManage.setVisibility(View.GONE);
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    binding.navDrawerView.getMenu().findItem(R.id.menu_manager).setChecked(true);
                    binding.navDrawerView.getMenu().findItem(R.id.staff_manager).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.order_list).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.chat).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.voucher).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.wallet).setChecked(false);
                    active = manageMenuFragment;
                    return true;
                case R.id.setting:
                    FirebaseAuth.getInstance().signOut();
                case R.id.order_list:
                default:
                    fragmentManager.beginTransaction().hide(active).show(orderListFragment).commit();
                    binding.searchOrderView.setVisibility(View.VISIBLE);
                    binding.btHelp.setVisibility(View.VISIBLE);
                    binding.titleMenuManage.setVisibility(View.GONE);
                    binding.btAddVoucher.setVisibility(View.GONE);
                    binding.titleChat.setVisibility(View.GONE);
                    binding.titleVoucher.setVisibility(View.GONE);
                    binding.titleStaffManage.setVisibility(View.GONE);
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    binding.navDrawerView.getMenu().findItem(R.id.staff_manager).setChecked(false);
                    binding.navDrawerView.getMenu().findItem(R.id.menu_manager).setChecked(false);
                    active = orderListFragment;
                    return true;

            }
        });
        binding.btAddVoucher.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddVoucherActivity.class)));
    }

    private void inforUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        reference.child(user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = ""+snapshot.child("user_name").getValue();
                        String position = ""+snapshot.child("userType").getValue();
                        String shopName = ""+snapshot.child("address").getValue();
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
}