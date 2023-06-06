package com.example.noidmerchant.GUI.Products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.noidmerchant.R;
import com.example.noidmerchant.Adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductsActivity extends AppCompatActivity {
    private TabLayout mTab;
    private ViewPager mView;
    ImageView back_btn;


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cateRef = database.getReference().child("danhmucsp");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        mTab = findViewById(R.id.tab_layout);
        mView = findViewById(R.id.view_pager);
        back_btn = findViewById(R.id.back_btn);
        ViewPagerAdapter viewPagerAdapter = new     ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mView.setAdapter(viewPagerAdapter);
        mTab.setupWithViewPager(mView);

        //Thêm danh mục lên realtime DB
        //cateRef.push().setValue(new DBCategory("Coffee"));
        //Thêm danh mục lên realtime DB
        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}