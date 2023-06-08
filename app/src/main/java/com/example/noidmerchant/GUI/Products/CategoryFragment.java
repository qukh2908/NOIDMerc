package com.example.noidmerchant.GUI.Products;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.Adapter.Category;
import com.example.noidmerchant.Adapter.CategoryAdapter;
import com.example.noidmerchant.R;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        CategoryAdapter adapter = new CategoryAdapter(list,getContext());
        database = FirebaseDatabase.getInstance();
        database.getReference().child("danhmucsp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Category category = dataSnapshot.getValue(Category.class);
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
        binding.rcvDir.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0){
                    binding.addCate.hide();
                }else {
                    binding.addCate.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        binding.addCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mai code
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogview = getLayoutInflater().inflate(R.layout.add_category,null);
                builder.setView(dialogview);
                AlertDialog dialog = builder.create();

            }
        });
        return binding.getRoot();
    }
}