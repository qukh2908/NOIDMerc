package com.example.noidmerchant.GUI.Products;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.noidmerchant.Adapter.ViewPagerAdapter;
import com.example.noidmerchant.R;
import com.example.noidmerchant.databinding.ActivityProductsBinding;
import com.google.android.material.tabs.TabLayout;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.noidmerchant.databinding.ActivityProductsBinding binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TabLayout mTab = findViewById(R.id.tab_layout);
        ViewPager mView = findViewById(R.id.view_pager);
        ImageView back_btn = findViewById(R.id.back_btn);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mView.setAdapter(viewPagerAdapter);
        mTab.setupWithViewPager(mView);

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}