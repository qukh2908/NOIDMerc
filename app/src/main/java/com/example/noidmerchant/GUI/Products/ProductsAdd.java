package com.example.noidmerchant.GUI.Products;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noidmerchant.Database.DBProduct;
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
    private final DatabaseReference cateRef = database.getReference().child("danhmucsp");
    private final DatabaseReference prodRef = database.getReference().child("sanpham");
    private ArrayAdapter<String> dmAdapter;
    private String madm = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddProductBinding.inflate(getLayoutInflater());

        ArrayList<String> categories = new ArrayList<>();
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

        binding.edDanhmuc.setOnClickListener(view -> {
            dmAdapter = new ArrayAdapter<> (ProductsAdd.this, android.R.layout.simple_dropdown_item_1line, categories);
            binding.edDanhmuc.setAdapter(dmAdapter);
            binding.edDanhmuc.showDropDown();
            madm = null;
        });

        binding.edDanhmuc.setOnItemClickListener((parent, view, position, id) -> {
            String tendm = binding.edDanhmuc.getText().toString();
            cateRef.orderByKey().addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if (snapshot.child("tendm").getValue().toString().equals(tendm)) {
                        madm = snapshot.getKey();
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
        });

        binding.btnLuu.setOnClickListener(v-> {
            String tensp = binding.edtTensp.getText().toString();
            int giasp = Integer.parseInt(binding.edtGia.getText().toString());
            int soluong = Integer.parseInt(binding.edtSoluong.getText().toString());
            String motasp = binding.edtMieuta.getText().toString();
            String hinhsp = "https://firebasestorage.googleapis.com/v0/b/noidapp221.appspot.com/o/hinhSanPham%2FCoffee%2Fcafe_1.png?alt=media&token=6d951c58-81be-4b5e-b46e-2c4d59d44f1a";
            prodRef.push().setValue(new DBProduct(madm, hinhsp, tensp, motasp, giasp, soluong));

        });

        setContentView(binding.getRoot());
        binding.backBtnAdd.setOnClickListener(v -> finish());
    }
}