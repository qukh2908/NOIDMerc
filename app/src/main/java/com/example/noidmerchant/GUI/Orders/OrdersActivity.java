package com.example.noidmerchant.GUI.Orders;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noidmerchant.databinding.ActivityOrdersBinding;
import com.google.firebase.database.FirebaseDatabase;

public class OrdersActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ActivityOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());

        //nút back
        binding.backBtn.setOnClickListener(v -> finish());
    }
}