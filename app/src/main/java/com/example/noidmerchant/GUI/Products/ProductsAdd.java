package com.example.noidmerchant.GUI.Products;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noidmerchant.R;
import com.example.noidmerchant.databinding.AddProductBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductsAdd extends AppCompatActivity {
    private AddProductBinding binding;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cateRef = database.getReference().child("danhmucsp");
    ArrayAdapter<String> dmAdapter;
    String tendm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddProductBinding.inflate(getLayoutInflater());
        ArrayList<String> categories = new ArrayList<String>();
        cateRef.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                categories.add(snapshot.child("tendm").getValue().toString());
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
        binding.edDanhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dmAdapter = new ArrayAdapter<> (ProductsAdd.this, android.R.layout.simple_dropdown_item_1line, categories);
                binding.edDanhmuc.setAdapter(dmAdapter);
                binding.edDanhmuc.showDropDown();
            }
        });
        binding.edDanhmuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tendm = binding.edDanhmuc.getText().toString();
                cateRef.orderByKey().addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.child("tendm").getValue().toString().equals(tendm)) {
                            String madm = snapshot.getKey();
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
            }
        });
        setContentView(binding.getRoot());
        binding.backBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}