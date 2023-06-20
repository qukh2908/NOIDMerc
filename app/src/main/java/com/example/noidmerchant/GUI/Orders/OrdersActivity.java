package com.example.noidmerchant.GUI.Orders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noidmerchant.Adapter.Orders;
import com.example.noidmerchant.Adapter.OrdersAdapter;
import com.example.noidmerchant.MainActivity;

import com.example.noidmerchant.databinding.ActivityOrdersBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class OrdersActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference authRef = database.getReference().child("taikhoan");
    final DatabaseReference ordRef = database.getReference().child("dathang");
    ArrayList<Orders> list = new ArrayList<>();
    private ActivityOrdersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        OrdersAdapter adapter = new OrdersAdapter(this, list);
        binding.rcvDh.setAdapter(adapter);
        setContentView(binding.getRoot());
        ordRef.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String makh = snapshot.child("makh").getValue().toString();
                String madh = snapshot.child("madh").getValue().toString();
                String thoigiandh = snapshot.child("thoigiandh").getValue().toString();
                String ghichudh = snapshot.child("ghichudh").getValue().toString();
                String tinhtrang = snapshot.child("tinhtrang").getValue().toString();
                long soluongdh = snapshot.child("soluongdh").getValue(long.class);
                long tongtiendh =  snapshot.child("tongtiendh").getValue(long.class);
                authRef.child(makh).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String tenkh = snapshot.child("name").getValue().toString();
                        Orders orders = new Orders(makh,madh,thoigiandh,ghichudh,tinhtrang,tenkh,soluongdh,tongtiendh);
                        list.add(orders);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                list.clear();
//                ordRef.removeEventListener(this);
//                ordRef.addChildEventListener(this);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                ordRef.removeEventListener(this);
//                ordRef.addChildEventListener(this);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvDh.setLayoutManager(layoutManager);

        //nút back
        binding.backBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}