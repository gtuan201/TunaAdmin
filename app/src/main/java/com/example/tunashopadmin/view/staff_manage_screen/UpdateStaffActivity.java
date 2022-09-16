package com.example.tunashopadmin.view.staff_manage_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tunashopadmin.OnItemClickListener;
import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityUpdateStaffBinding;
import com.example.tunashopadmin.model.Shop;
import com.example.tunashopadmin.view.staff_manage_screen.adapter.BottomSheetShopAdapter;
import com.example.tunashopadmin.viewmodel.DisplayStaffShopViewModel;
import com.example.tunashopadmin.viewmodel.ManageStaffViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class UpdateStaffActivity extends AppCompatActivity implements OnItemClickListener {
    private ActivityUpdateStaffBinding binding;
    private BottomSheetDialog bottomSheetDialog;
    private TextView tvAddress;
    private String level;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_staff);
        tvAddress = findViewById(R.id.et_address_update);
        ManageStaffViewModel viewModel = new ViewModelProvider(this).get(ManageStaffViewModel.class);
        bottomSheetDialog = new BottomSheetDialog(this);
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        String address = intent.getStringExtra("address");
        String name = intent.getStringExtra("name");
        String strLevel = intent.getStringExtra("level");
        level = strLevel;
        binding.etName.setText(name);
        binding.etAddressUpdate.setText(address);
        if (strLevel.equals("shipper")){
            binding.rgLevel.check(R.id.rb_shipper);
        }
        else if (strLevel.equals("staff")){
            binding.rgLevel.check(R.id.rb_staff);
        }
        else {
            binding.rgLevel.check(R.id.rb_manager);
        }
        binding.etAddressUpdate.setOnClickListener(v -> openBottomSheet());
        binding.rgLevel.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rb_shipper:
                    level = "shipper";
                    break;
                case R.id.rb_staff:
                    level = "staff";
                    break;
                case R.id.rb_manager:
                    level = "manager";
                    break;
            }
        });
        binding.btComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameUpdate = binding.etName.getText().toString().trim();
                String addressUpdate = binding.etAddressUpdate.getText().toString().trim();
                if (name.equals(nameUpdate) && address.equals(addressUpdate) && level.equals(strLevel)){
                    Toast.makeText(UpdateStaffActivity.this,"Ủa? Không sửa gì à?",Toast.LENGTH_SHORT).show();
                }
                else {
                    viewModel.updateStaff(uid,nameUpdate,addressUpdate,level);
                }
            }
        });
    }
    private void openBottomSheet() {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_pick_shop,null,false);
        bottomSheetDialog.setContentView(view);
        RecyclerView rev_shop = view.findViewById(R.id.rev_shop);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rev_shop.setLayoutManager(manager);
        DisplayStaffShopViewModel viewModel = new ViewModelProvider(this).get(DisplayStaffShopViewModel.class);
        viewModel.getListShopMutableLiveData().observe(this, shops -> {
            BottomSheetShopAdapter adapter = new BottomSheetShopAdapter(shops,UpdateStaffActivity.this);
            rev_shop.setAdapter(adapter);
        });
        bottomSheetDialog.show();
    }

    @Override
    public void OnClickItem(Shop shop) {
        tvAddress.setText(shop.getName());
        bottomSheetDialog.dismiss();
    }
}