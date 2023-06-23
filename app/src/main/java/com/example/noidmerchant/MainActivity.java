package com.example.noidmerchant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.noidmerchant.GUI.Common.InformationsActivity;
import com.example.noidmerchant.GUI.Common.SettingsActivity;
import com.example.noidmerchant.GUI.Imports.BuyProductActivity;
import com.example.noidmerchant.GUI.Orders.OrdersActivity;
import com.example.noidmerchant.GUI.Products.ProductsActivity;
import com.example.noidmerchant.GUI.Statistics.StatisticsActivity;
import com.example.noidmerchant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onStart() {
        super.onStart();
        //Xem đã có quyền thông báo hay chưa
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkPermission(Manifest.permission.POST_NOTIFICATIONS, 1);
            checkPermission(Manifest.permission.FOREGROUND_SERVICE, 2);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.noidmerchant.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.productCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProductsActivity.class)));

        binding.ordersCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OrdersActivity.class)));

        binding.statisticsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, StatisticsActivity.class)));

        binding.helpCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BuyProductActivity.class)));

        binding.informationCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InformationsActivity.class)));

        binding.settingsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
