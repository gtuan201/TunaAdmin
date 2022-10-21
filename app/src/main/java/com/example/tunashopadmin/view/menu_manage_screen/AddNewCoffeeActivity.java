package com.example.tunashopadmin.view.menu_manage_screen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityAddNewCoffeeBinding;
import com.example.tunashopadmin.viewmodel.AddCoffeeViewModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddNewCoffeeActivity extends AppCompatActivity {
    private ActivityAddNewCoffeeBinding binding;
    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_CODE2 = 2;
    private Uri uriBack, uriImg;
    private String urlBack, urlImg,name,cate,des,price;
    private AddCoffeeViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_coffee);
        viewModel = new ViewModelProvider(this).get(AddCoffeeViewModel.class);
        binding.btAddBackground.setOnClickListener(v -> pickImg("back"));
        binding.imgBackground.setOnClickListener(v -> pickImg("back"));
        binding.addImgCoffee.setOnClickListener(v -> pickImg("img"));
        binding.btAddImg.setOnClickListener(v -> pickImg("img"));
        binding.btAddCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = binding.nameCoffee.getText().toString().trim();
                cate = binding.categoryCoffee.getText().toString().trim();
                price = binding.priceCoffee.getText().toString().trim();
                des = binding.descriptionCoffee.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(AddNewCoffeeActivity.this,"Bạn chưa nhập tên đồ uống",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(cate)){
                    Toast.makeText(AddNewCoffeeActivity.this,"Bạn chưa nhập loại đồ uống",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(price)){
                    Toast.makeText(AddNewCoffeeActivity.this,"Bạn chưa nhập giá đồ uống",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(des)){
                    Toast.makeText(AddNewCoffeeActivity.this,"Bạn chưa mô tả đồ uống",Toast.LENGTH_SHORT).show();
                }
                else {
                    ProgressDialog progressDialog = new ProgressDialog(AddNewCoffeeActivity.this);
                    progressDialog.setMessage("Vui lòng đợi");
                    progressDialog.show();
                    uploadImage(progressDialog);
                }
            }
        });
    }

    private void uploadImage(ProgressDialog progressDialog) {
        long timestamp = System.currentTimeMillis();
        String filePath = "ImgCoffee/"+timestamp;
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(filePath);
        storageReference.putFile(Uri.parse(""+uriImg))
                .addOnSuccessListener(taskSnapshot -> {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful());
                    urlImg = ""+uriTask.getResult();
                    uploadImage2(urlImg,progressDialog);
                });
    }
    private void uploadImage2(String urlImg, ProgressDialog progressDialog) {
        long timestamp = System.currentTimeMillis();
        String filePath = "ImgCoffee/"+timestamp;
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(filePath);
        storageReference.putFile(Uri.parse(""+uriBack))
                .addOnSuccessListener(taskSnapshot -> {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful());
                    urlBack = ""+uriTask.getResult();
                    uploadToRealtime(urlImg,urlBack,progressDialog);
                });
    }

    private void uploadToRealtime(String urlImg, String urlBack, ProgressDialog progressDialog) {
        viewModel.addCoffee(urlImg,urlBack,name,cate,price,des);
        new Handler().postDelayed(progressDialog::dismiss,2200);
    }

    private void pickImg(String type) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (type.equals("back")){
            startActivityIfNeeded(Intent.createChooser(intent, "Add Coffee"),REQUEST_CODE);
        }
        else startActivityIfNeeded(Intent.createChooser(intent, "Add Coffee"),REQUEST_CODE2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK){
                if (data != null){
                    uriBack = data.getData();
                    binding.btAddBackground.setVisibility(View.GONE);
                    binding.imgBackground.setImageURI(uriBack);
                }
            }
        }
        if (requestCode == REQUEST_CODE2){
            if (resultCode == RESULT_OK){
                if (data != null){
                    uriImg = data.getData();
                    binding.btAddImg.setVisibility(View.GONE);
                    binding.addImgCoffee.setVisibility(View.VISIBLE);
                    binding.addImgCoffee.setImageURI(uriImg);
                }
            }
        }
    }
}