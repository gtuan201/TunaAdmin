package com.example.tunashopadmin.view.staff_manage_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tunashopadmin.OnItemClickListener;
import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityCreatUserBinding;

import com.example.tunashopadmin.model.Shop;
import com.example.tunashopadmin.view.login_signup_screen.LoginActivity;
import com.example.tunashopadmin.view.staff_manage_screen.adapter.BottomSheetShopAdapter;
import com.example.tunashopadmin.viewmodel.LoginViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreatUserActivity extends AppCompatActivity implements OnItemClickListener{
    private ActivityCreatUserBinding binding;
    private String level = null;
    private TextView tvAddress;
    private BottomSheetDialog bottomSheetDialog;

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_creat_user);
        tvAddress = findViewById(R.id.et_address);
        bottomSheetDialog = new BottomSheetDialog(this);
        LoginViewModel viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.rgLevel.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rb_shipper:
                    level = "shipper";
                    binding.layoutLevel.setVisibility(View.VISIBLE);
                    binding.imgLevel.setImageResource(R.drawable.shipper_logo);
                    binding.tvLevel.setText("Nhân viên giao hàng");
                    break;
                case R.id.rb_staff:
                    level = "staff";
                    binding.layoutLevel.setVisibility(View.VISIBLE);
                    binding.imgLevel.setImageResource(R.drawable.employee_logo);
                    binding.tvLevel.setText("Nhân viên bán hàng");
                    break;
                case R.id.rb_manager:
                    level = "manager";
                    binding.layoutLevel.setVisibility(View.VISIBLE);
                    binding.imgLevel.setImageResource(R.drawable.leader_logo);
                    binding.tvLevel.setText("Quản lý cửa hàng");
                    break;
            }
        });
        binding.btCreatWithEmail.setOnClickListener(v -> checkInput(viewModel));
        binding.etAddress.setOnClickListener(v -> openBottomSheet());
    }

    private void openBottomSheet() {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_pick_shop,null,false);
        bottomSheetDialog.setContentView(view);
        RecyclerView rev_shop = view.findViewById(R.id.rev_shop);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rev_shop.setLayoutManager(manager);
        List<Shop> shopList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Shop");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shopList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String imgShop = ""+dataSnapshot.child("ImgUrl").getValue();
                    String nameShop = ""+dataSnapshot.child("name").getValue();
                    String addressShop =""+dataSnapshot.child("address").getValue();
                    Shop shop = new Shop();
                    shop.setImgUrlShop(imgShop);
                    shop.setName(nameShop);
                    shop.setAddress(addressShop);
                    shopList.add(shop);
                }
                BottomSheetShopAdapter adapter = new BottomSheetShopAdapter(shopList, CreatUserActivity.this);
                rev_shop.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomSheetDialog.show();
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    private void checkInput(LoginViewModel viewModel) {
        String name = binding.etName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String phone = binding.etPhoneNumber.getText().toString().trim();
        String address = binding.etAddress.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address) ||TextUtils.isEmpty(level)){
            Toast.makeText(CreatUserActivity.this,"Bạn phải hoàn thành đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
        }
        else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Vui lòng đợi");
            progressDialog.show();
            new Handler().postDelayed(progressDialog::dismiss,1500);
            new Handler().postDelayed(() -> new AlertDialog.Builder(CreatUserActivity.this)
                    .setMessage("Phiên đăng nhập đã hết hạn")
                    .show(),3500);
            viewModel.register(email,"123456",name,level,address,phone);
            new Handler().postDelayed(() -> {
                startActivity(new Intent(CreatUserActivity.this, LoginActivity.class));
                finishAffinity();
            },5000);
        }
    }

    @Override
    public void OnClickItem(Shop shop) {
        tvAddress.setText(shop.getName());
        bottomSheetDialog.dismiss();
    }
}