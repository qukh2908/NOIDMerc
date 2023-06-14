package com.example.noidmerchant.GUI.Products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.noidmerchant.databinding.DetailsProductBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProductDetails extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseStorage storage = FirebaseStorage.getInstance();
    final DatabaseReference prodRef = database.getReference().child("sanpham");
    final StorageReference prodStorageRef = storage.getReference().child("hinhSanPham");
    private DetailsProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Nút back
        binding.backBtnAdd.setOnClickListener(view -> finish());
    }
}