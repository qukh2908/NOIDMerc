package com.example.noidmerchant.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.noidmerchant.R;
import com.example.noidmerchant.database.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ProductsActivity extends AppCompatActivity {
    private TabLayout mTab;
    private ViewPager mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        mTab = findViewById(R.id.tab_layout);
        mView = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mView.setAdapter(viewPagerAdapter);
        mTab.setupWithViewPager(mView);

    }
}