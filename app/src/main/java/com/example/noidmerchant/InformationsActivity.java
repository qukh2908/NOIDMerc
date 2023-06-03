package com.example.noidmerchant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class InformationsActivity extends AppCompatActivity {

    ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);
        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(v -> {
            finish();
        });
    }
}