package com.example.noidmerchant.GUI.Products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.noidmerchant.Database.DBCategory;
import com.example.noidmerchant.Database.DBProduct;
import com.example.noidmerchant.R;
import com.example.noidmerchant.Adapter.ViewPagerAdapter;

import com.example.noidmerchant.databinding.ActivityProductsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductsActivity extends AppCompatActivity {
    private TabLayout mTab;
    private ViewPager mView;
    private ActivityProductsBinding binding;
    private ImageView back_btn;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference cateRef = database.getReference().child("danhmucsp");
    private DatabaseReference prodRef = database.getReference().child("sanpham");
    private String masp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mTab = findViewById(R.id.tab_layout);
        mView = findViewById(R.id.view_pager);
        back_btn = findViewById(R.id.back_btn);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mView.setAdapter(viewPagerAdapter);
        mTab.setupWithViewPager(mView);

        //Thêm danh mục lên realtime DB
        //cateRef.push().setValue(new DBCategory("Other"));
        //Thêm danh mục lên realtime DB

        //Thêm sản phẩm lên realtime DB
//        masp = prodRef.push().getKey();
//        prodRef.child(masp).setValue(new DBProduct(
//                "-NX9r4LR1f6twnVU28_R",
//                "https://firebasestorage.googleapis.com/v0/b/noidapp221.appspot.com/o/hinhSanPham%2FCoffee%2Fcafe_1.png?alt=media&token=6d951c58-81be-4b5e-b46e-2c4d59d44f1a",
//                "Tên SP",
//                "Mô Tả SP",
//                100000,
//                999));
        //Thêm sản phẩm lên realtime DB

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}