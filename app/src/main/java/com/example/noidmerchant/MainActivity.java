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

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView products,orders,statistics,helps,info,settings;

        products = findViewById(R.id.productCard);
        orders = findViewById(R.id.ordersCard);
        statistics = findViewById(R.id.statisticsCard);
        helps = findViewById(R.id.helpCard);
        info = findViewById(R.id.informationCard);
        settings = findViewById(R.id.settingsCard);

        //Bat su kien Product
        products.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProductsActivity.class)));
        //Bat su kien Order
        orders.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OrdersActivity.class)));

        statistics.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, StatisticsActivity.class)));

        helps.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BuyProductActivity.class)));

        info.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InformationsActivity.class)));

        settings.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }
}
