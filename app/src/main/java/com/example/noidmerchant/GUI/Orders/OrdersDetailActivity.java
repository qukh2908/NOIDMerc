package com.example.noidmerchant.GUI.Orders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.noidmerchant.Database.DBProductsInOrder;
import com.example.noidmerchant.Adapter.ProductsInOrderAdapter;
import com.example.noidmerchant.Database.DBOrder;
import com.example.noidmerchant.databinding.DetailsOrderBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class OrdersDetailActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ordRef = database.getReference().child("dathang");
    final DatabaseReference authRef = database.getReference().child("taikhoan");
    final ArrayList<DBProductsInOrder> productsList = new ArrayList<>();
    private String makh, madh;
    private DialogInterface.OnClickListener dialogClickListener;
    private DetailsOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ProductsInOrderAdapter productsInOrderAdapter = new ProductsInOrderAdapter(productsList, this);
        //Reset khi khởi động
        makh = null;
        madh = null;
        productsList.clear();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            DBOrder DBOrder = (DBOrder) bundle.get("dathang");
            makh = DBOrder.getMakh();
            madh = DBOrder.getMadh();
            //Lấy thông tin từ mã khách hàng
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
            binding.txtTt.setText(DBOrder.getTinhtrang());
            //Ẩn hiện nút tùy vào tình trạng đơn
            if (!DBOrder.getTinhtrang().equals("Đang chờ xác nhận")) {
                binding.btnHuy.setVisibility(View.INVISIBLE);
                binding.btnXacnhan.setVisibility(View.INVISIBLE);
            }
            double updatedPrice = DBOrder.getTongtiendh();
            DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
            String formattedPrice = decimalFormat.format(updatedPrice);
            binding.txtTongtien.setText(formattedPrice);
            //Set layout cho rcv sản phẩm
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            binding.rcvProductsList.setLayoutManager(layoutManager);
            //Set adapter cho rcv sản phẩm
            ProductsInOrderAdapter adapter = new ProductsInOrderAdapter(productsList, this);
            binding.rcvProductsList.setAdapter(adapter);
            ordRef.child(madh).child("sanpham").orderByKey().addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.i("ADMIN",madh + "/sanpham/" + snapshot.getKey());
                    String masp = snapshot.getKey();
                    String tensp = snapshot.child("tensp").getValue().toString();
                    long soluong = snapshot.child("soluong").getValue(long.class);
                    long giasp = snapshot.child("giasp").getValue(long.class);
                    long tongtien = snapshot.child("tongtien").getValue(long.class);

                    DBProductsInOrder sanpham = new DBProductsInOrder(masp, tensp, "", "", soluong, giasp, tongtien);
                    productsList.add(sanpham);
                    productsInOrderAdapter.notifyDataSetChanged();
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
        } else {
            Toast.makeText(OrdersDetailActivity.this, "Không lấy được thông tin sản phẩm", Toast.LENGTH_SHORT).show();
            finish();
        }
        //Nút xác nhận
        binding.btnXacnhan.setOnClickListener(v -> {
            if (madh != null) {
                ordRef.child(madh).child("tinhtrang").setValue("Đang giao");
                finish();
            }
        });
        //Nút hủy
        binding.btnHuy.setOnClickListener(v -> {
            dialogClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        if (madh != null) {
                            ordRef.child(madh).child("tinhtrang").setValue("Đã hủy");
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
        //Nút quay lại
        binding.backBtnAdd.setOnClickListener(v -> finish());
    }
}