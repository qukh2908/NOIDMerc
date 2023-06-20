package com.example.noidmerchant.GUI.Orders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.noidmerchant.Adapter.Cart;
import com.example.noidmerchant.Adapter.CartAdapter;
import com.example.noidmerchant.Adapter.Orders;
import com.example.noidmerchant.databinding.DetailsOrderBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class OrdersDetailActivity extends AppCompatActivity {
    private String makh, madh;
    ArrayList<Cart> list = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ordRef = database.getReference().child("dathang");
    final DatabaseReference authRef = database.getReference().child("taikhoan");
    private DialogInterface.OnClickListener dialogClickListener;
    private DetailsOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsOrderBinding.inflate(getLayoutInflater());
        CartAdapter adapter = new CartAdapter(list,this);
        binding.rcvDetailDh.setAdapter(adapter);
        makh = null;
        madh = null;
        list.clear();
        database.getReference().child("dathang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    String masp = cart.getMasp();
                    ordRef.child("sanpham").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            cart.setTensp((String) snapshot.child("tensp").getValue());
                            cart.setSoluong(Math.toIntExact(snapshot.child("soluong").getChildrenCount()));
                            cart.setGiasp(Math.toIntExact(snapshot.child("giasp").getChildrenCount()));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    list.add(cart);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvDetailDh.setLayoutManager(layoutManager);
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            Orders orders = (Orders) bundle.get("dathang");
            makh = orders.getMakh();
            madh = orders.getMadh();
            if(orders.getTinhtrang().equals("Đã hủy")) {
                binding.btnHuy.setVisibility(View.INVISIBLE);
                binding.btnXacnhan.setVisibility(View.INVISIBLE);
            }
            authRef.child(makh).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    binding.txtDiachi.setText(Objects.requireNonNull(snapshot.child("address").getValue()).toString());
                    binding.txtTenkh.setText(Objects.requireNonNull(snapshot.child("name").getValue()).toString());
                    binding.txtSdt.setText(Objects.requireNonNull(snapshot.child("phone").getValue()).toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            binding.txtMd.setText(madh);
            binding.txtTt.setText(orders.getTinhtrang());
            double updatedPrice = orders.getTongtiendh();
            DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
            String formattedPrice = decimalFormat.format(updatedPrice);
            binding.txtTongtien.setText(formattedPrice);
        } else {
            Toast.makeText(OrdersDetailActivity.this, "Không lấy được thông tin sản phẩm", Toast.LENGTH_SHORT).show();
            finish();
        }
        //Nút xác nhận
        binding.btnXacnhan.setOnClickListener(v -> {

        });
        //Nút hủy
        binding.btnHuy.setOnClickListener(v -> {
            dialogClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        if(madh != null) {
                            ordRef.child(madh).child("tinhtrang").setValue("Đã hủy");
                            binding.btnHuy.setEnabled(false);
                            binding.btnXacnhan.setEnabled(false);
                            finish();
                        }
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(OrdersDetailActivity.this);
            builder.setMessage("Hủy đơn hàng này?")
                    .setPositiveButton("Có", dialogClickListener)
                    .setNegativeButton("Không", dialogClickListener)
                    .show();
        });
        binding.backBtnAdd.setOnClickListener(v -> finish());
    }
}