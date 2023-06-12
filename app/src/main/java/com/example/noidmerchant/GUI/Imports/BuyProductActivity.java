package com.example.noidmerchant.GUI.Imports;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noidmerchant.Database.DBImport;
import com.example.noidmerchant.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;


public class BuyProductActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference impRef = database.getReference().child("nhaphang");
    DatabaseReference cateRef = database.getReference().child("danhmucsp");
    DatabaseReference prodRef = database.getReference().child("sanpham");
    AutoCompleteTextView edt_danhmuc, edt_sanpham;
    ArrayAdapter<String> dmAdapter;
    ArrayAdapter<String> spAdapter;
    ImageView back_btn;
    String tendm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyproduct);

        edt_danhmuc = findViewById(R.id.edt_danhmuc);
        edt_sanpham = findViewById(R.id.edt_sanpham);
        back_btn = findViewById(R.id.back_btn);

        ArrayList<String> categories = new ArrayList<>();
        ArrayList<String> products = new ArrayList<>();
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

        edt_danhmuc.setOnClickListener(v -> {
            dmAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, categories);
            edt_danhmuc.setAdapter(dmAdapter);
            edt_danhmuc.showDropDown();
            products.clear();
            edt_sanpham.setText("");
        });

        edt_danhmuc.setOnItemClickListener((parent, view, position, id) -> {
            tendm = edt_danhmuc.getText().toString();
            cateRef.orderByKey().addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if(snapshot.child("tendm").getValue().toString().equals(tendm)) {
                        String madm = snapshot.getKey();
                        prodRef.orderByKey().addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                              if(snapshot.child("madm").getValue().toString().equals(madm)) {
                                  products.add(snapshot.child("tensp").getValue().toString());
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

        edt_sanpham.setOnClickListener(v -> {
            spAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, products);
            edt_sanpham.setAdapter(spAdapter);
            edt_sanpham.showDropDown();
        });

        //Thêm nhập hàng lên realtime DB
        //impRef.push().setValue(new DBImport("example", 0, ServerValue.TIMESTAMP));
        //Thêm nhập hàng lên realtime DB

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}