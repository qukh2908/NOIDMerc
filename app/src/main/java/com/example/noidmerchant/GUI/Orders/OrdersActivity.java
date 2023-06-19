package com.example.noidmerchant.GUI.Orders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noidmerchant.Adapter.Orders;
import com.example.noidmerchant.Adapter.OrdersAdapter;
import com.example.noidmerchant.MainActivity;

import com.example.noidmerchant.databinding.ActivityOrdersBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class OrdersActivity extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference authRef = database.getReference().child("taikhoan");
    ArrayList<Orders> list = new ArrayList<>();
    private ActivityOrdersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());

        OrdersAdapter adapter = new OrdersAdapter(this, list);
        binding.rcvDh.setAdapter(adapter);

        setContentView(binding.getRoot());
        list.clear();
        database.getReference().child("dathang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Orders orders = dataSnapshot.getValue(Orders.class);
                    String makh = orders.getMakh();
                    authRef.child(makh).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            orders.setTenkh(snapshot.child("name").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    list.add(orders);
                }
                adapter.notifyDataSetChanged();
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