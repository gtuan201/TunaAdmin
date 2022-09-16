package com.example.tunashopadmin.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tunashopadmin.model.Shop;
import com.example.tunashopadmin.model.User;
import com.example.tunashopadmin.repository.ManageStaffRepository;
import com.example.tunashopadmin.view.staff_manage_screen.CreatUserActivity;
import com.example.tunashopadmin.view.staff_manage_screen.adapter.BottomSheetShopAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayStaffShopViewModel extends ViewModel {
    private final MutableLiveData<List<User>> listStaffMutableLiveData;
    private final MutableLiveData<List<Shop>> listShopMutableLiveData;

    public DisplayStaffShopViewModel() {
        listStaffMutableLiveData = new MutableLiveData<>();
        listShopMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getListStaffMutableLiveData() {
        List<User> list = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String img = ""+dataSnapshot.child("imgUser").getValue();
                    String uid = ""+dataSnapshot.child("uid").getValue();
                    String name = ""+dataSnapshot.child("user_name").getValue();
                    String phone = ""+dataSnapshot.child("phone").getValue();
                    String address = ""+dataSnapshot.child("address").getValue();
                    String type = ""+dataSnapshot.child("userType").getValue();
                    if (type.equals("staff") || type.equals("shipper") || type.equals("manager")){
                        User user = new User();
                        user.setImgUrl(img);
                        user.setName(name);
                        user.setUid(uid);
                        user.setPhone(phone);
                        user.setShopManage(address);
                        user.setTypeUser(type);
                        list.add(user);
                    }
                }
                listStaffMutableLiveData.postValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listStaffMutableLiveData;
    }

    public MutableLiveData<List<Shop>> getListShopMutableLiveData() {
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
                listShopMutableLiveData.postValue(shopList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listShopMutableLiveData;
    }
}
