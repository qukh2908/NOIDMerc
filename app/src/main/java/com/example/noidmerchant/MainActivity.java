package com.example.noidmerchant;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.noidmerchant.GUI.Common.InformationsActivity;
import com.example.noidmerchant.GUI.Common.SettingsActivity;
import com.example.noidmerchant.GUI.Imports.BuyProductActivity;
import com.example.noidmerchant.GUI.Orders.OrdersActivity;
import com.example.noidmerchant.GUI.Products.ProductsActivity;
import com.example.noidmerchant.GUI.Statistics.StatisticsActivity;
import com.example.noidmerchant.databinding.ActivityMainBinding;
import com.example.noidmerchant.databinding.DetailsProductBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.productCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProductsActivity.class)));

        binding.ordersCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OrdersActivity.class)));

        binding.statisticsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, StatisticsActivity.class)));

        binding.helpCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BuyProductActivity.class)));

        binding.informationCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InformationsActivity.class)));

        binding.settingsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }
}
