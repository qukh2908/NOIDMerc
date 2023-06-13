package com.example.noidmerchant.GUI.Products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.noidmerchant.databinding.DetailsProductBinding;

public class ProductDetails extends AppCompatActivity {
    private DetailsProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsProductBinding.inflate(getLayoutInflater());
        binding.backBtnDetail.setOnClickListener(view -> finish());
        setContentView(binding.getRoot());
    }
}