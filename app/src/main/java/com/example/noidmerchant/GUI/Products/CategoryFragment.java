package com.example.noidmerchant.GUI.Products;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noidmerchant.Adapter.Category;
import com.example.noidmerchant.Adapter.CategoryAdapter;
import com.example.noidmerchant.databinding.FragmentCategoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {
    public CategoryFragment() {
        // Required empty public constructor
    }
    FragmentCategoryBinding binding;
    ArrayList<Category> list = new ArrayList<>();

    FirebaseDatabase database;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        CategoryAdapter adapter = new CategoryAdapter(list,getContext());

        database = FirebaseDatabase.getInstance();
        database.getReference().child("danhmucsp").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Category category = dataSnapshot.getValue(Category.class);
                    assert category != null;
                    category.getNameID(dataSnapshot.getKey());
                    list.add(category);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.rcvDir.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvDir.setLayoutManager(layoutManager);

        return binding.getRoot();
    }
}