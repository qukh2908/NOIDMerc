package com.example.noidmerchant.GUI.Products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.noidmerchant.R;
import com.example.noidmerchant.databinding.DetailsProductBinding;

public class DetailsActivity extends AppCompatActivity {
    private DetailsProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsProductBinding.inflate(getLayoutInflater());
        binding.backBtnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setContentView(binding.getRoot());
    }
}