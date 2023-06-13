package com.example.noidmerchant.GUI.Imports;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class BuyProductActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference impRef = database.getReference().child("nhaphang");
    DatabaseReference cateRef = database.getReference().child("danhmucsp");
    DatabaseReference prodRef = database.getReference().child("sanpham");
    AutoCompleteTextView edt_danhmuc, edt_sanpham;
    EditText edt_soluong;
    ArrayAdapter<String> dmAdapter;
    ArrayAdapter<String> spAdapter;
    ImageView back_btn;
    Button btn_luu;
    String tendm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyproduct);
        tendm = null; //reset tendm
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        edt_danhmuc = findViewById(R.id.edt_danhmuc);
        edt_sanpham = findViewById(R.id.edt_sanpham);
        edt_soluong = findViewById(R.id.edt_soluong);
        back_btn = findViewById(R.id.back_btn);
        btn_luu = findViewById(R.id.btn_luu);

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
        });

        edt_danhmuc.setOnItemClickListener((parent, view, position, id) -> {
            products.clear();
            edt_sanpham.setText("");
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

        btn_luu.setOnClickListener(v -> {
            String tensp = edt_sanpham.getText().toString();
            int soluongnhap = Integer.parseInt(edt_soluong.getText().toString());
            String currentDateAndTime = sdf.format(new Date());

            prodRef.orderByKey().addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if(snapshot.child("tensp").getValue().toString().equals(tensp)) {
                        String masp = snapshot.getKey();
                        assert masp != null;
                        int soluong = snapshot.child("soluong").getValue().hashCode() + soluongnhap;
                        prodRef.child(masp).child("soluong").setValue(soluong);
                        impRef.push().setValue(new DBImport(masp,soluongnhap,currentDateAndTime));
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

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}