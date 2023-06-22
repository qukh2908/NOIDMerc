package com.example.noidmerchant.GUI.Orders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noidmerchant.Database.DBOrder;
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
import java.util.Objects;

public class OrdersActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference authRef = database.getReference().child("taikhoan");
    final DatabaseReference ordRef = database.getReference().child("dathang");
    ArrayList<DBOrder> list = new ArrayList<>();
    //ArrayList<DBProductsInOrder> productItems = new ArrayList<>();
    private ActivityOrdersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        OrdersAdapter adapter = new OrdersAdapter(this, list);
        binding.rcvDh.setAdapter(adapter);
        ordRef.orderByChild("tinhtrang").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String makh = Objects.requireNonNull(snapshot.child("makh").getValue()).toString();
                String madh = Objects.requireNonNull(snapshot.child("madh").getValue()).toString();
                String thoigiandh = Objects.requireNonNull(snapshot.child("thoigiandh").getValue()).toString();
                String ghichudh = Objects.requireNonNull(snapshot.child("ghichudh").getValue()).toString();
                String tinhtrang = Objects.requireNonNull(snapshot.child("tinhtrang").getValue()).toString();
                long soluongdh = snapshot.child("soluongdh").getValue(long.class);
                long tongtiendh =  snapshot.child("tongtiendh").getValue(long.class);
                //DBProductsInOrder sanpham = snapshot.child("sanpham").getValue(DBProductsInOrder.class);
                //productItems.add(sanpham);
                authRef.child(makh).addValueEventListener(new ValueEventListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String tenkh = Objects.requireNonNull(snapshot.child("name").getValue()).toString();
                        DBOrder DBOrder = new DBOrder(makh,madh,thoigiandh,ghichudh,tinhtrang,tenkh,soluongdh,tongtiendh);
                        list.add(DBOrder);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                list.clear();
                ordRef.removeEventListener(this);
                ordRef.addChildEventListener(this);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                list.clear();
                ordRef.removeEventListener(this);
                ordRef.addChildEventListener(this);
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
//    private Map<String, DBProductsInOrder> convertProductItemsToMap(ArrayList<DBProductsInOrder> items) {
//        Map<String, DBProductsInOrder> itemsMap = new HashMap<>();
//        for (DBProductsInOrder item : items) {
//            itemsMap.put(item.getMasp(), item);
//        } return itemsMap;
//    }
}