package com.example.noidmerchant.GUI.Products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noidmerchant.Adapter.Category;
import com.example.noidmerchant.Adapter.Product;
import com.example.noidmerchant.Adapter.ProductAdapter;
import com.example.noidmerchant.R;
import com.example.noidmerchant.databinding.FragmentProductBinding;
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
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("sanpham");
        DatabaseReference cafe = databaseRef.child("cafe");
        DatabaseReference milktea = databaseRef.child("milktea");
        DatabaseReference pakage = databaseRef.child("package");
        DatabaseReference snack = databaseRef.child("snack");
        DatabaseReference tea = databaseRef.child("tea");
        //Set item cafe
        cafe.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name  = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class));
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name,price,imageUrl);
                if(product != null)
                {
                    list.add(product);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //set item tra sua
        milktea.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name  = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class));
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name,price,imageUrl);
                if(product != null)
                {
                    list.add(product);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // set item cho package
        pakage.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name  = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class));
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name,price,imageUrl);
                if(product != null)
                {
                    list.add(product);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //set item cho banh
        snack.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name  = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class));
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name,price,imageUrl);
                if(product != null)
                {
                    list.add(product);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //set item cho tea
        tea.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name  = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class));
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name,price,imageUrl);
                if(product != null)
                {
                    list.add(product);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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