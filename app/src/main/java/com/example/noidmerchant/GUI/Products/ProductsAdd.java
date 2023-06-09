package com.example.noidmerchant.GUI.Products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noidmerchant.R;
import com.example.noidmerchant.databinding.AddProductBinding;

public class ProductsAdd extends AppCompatActivity {
    private AddProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddProductBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        binding.backBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}