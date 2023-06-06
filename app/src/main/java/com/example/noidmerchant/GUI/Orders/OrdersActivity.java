package com.example.noidmerchant.GUI.Orders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.noidmerchant.R;

public class OrdersActivity extends AppCompatActivity {

    ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        back_btn = findViewById(R.id.back_btn);

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}