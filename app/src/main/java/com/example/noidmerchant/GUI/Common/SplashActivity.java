package com.example.noidmerchant.GUI.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.noidmerchant.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash2);

    Handler handler = new Handler();
         handler.postDelayed(() -> {
             startActivity(new Intent(SplashActivity.this, LoginActivity.class));

             finish();
         },2000);
    }
}