package com.example.noidmerchant.GUI.Products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noidmerchant.Adapter.Category;
import com.example.noidmerchant.Adapter.Product;
import com.example.noidmerchant.Adapter.ProductAdapter;
import com.example.noidmerchant.R;
import com.example.noidmerchant.databinding.FragmentProductBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProductFragment extends Fragment {
    FragmentProductBinding binding;
    ArrayList<Product> list = new ArrayList<>();

    FirebaseDatabase database;
    String uid ;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false);
        ProductAdapter adapter = new ProductAdapter(list,getContext());
        binding.rcvProd.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("sanpham").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    product.getCategoryID(dataSnapshot.getKey());
                    list.add(product);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvProd.setLayoutManager(layoutManager);
        return binding.getRoot();
    }
}