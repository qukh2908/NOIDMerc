package com.example.noidmerchant.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.noidmerchant.gui.fragment.DirectoryFragment;
import com.example.noidmerchant.gui.fragment.ProductFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DirectoryFragment();
            case 1:
                return new ProductFragment();
            default:
                return new DirectoryFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position)
        {
            case 0 :
                title= "DANH MỤC";
                break;
            case 1:
                title= "SẢN PHẨM";
                break;
        }
        return title;
    }
}