package com.example.noidmerchant.GUI.Products;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.noidmerchant.Adapter.Category;
import com.example.noidmerchant.Adapter.Product;
import com.example.noidmerchant.Database.DBProduct;
import com.example.noidmerchant.R;
import com.example.noidmerchant.databinding.DetailsProductBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class ProductDetails extends AppCompatActivity {
    final int PICK_IMAGE_REQUEST = 22;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseStorage storage = FirebaseStorage.getInstance();
    final DatabaseReference prodRef = database.getReference().child("sanpham");
    final DatabaseReference cateRef = database.getReference().child("danhmucsp");
    final StorageReference prodStorageRef = storage.getReference().child("hinhSanPham");
    private DetailsProductBinding binding;
    private ArrayAdapter<String> dmAdapter;
    private ArrayList<String> categories = new ArrayList<>();
    private String madm,imageUrl,masp;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        if (bundle==null){
            return;
        }
        Product product  = (Product) bundle.get("sanpham");
        binding.edDanhmuc.setText(product.getNameID());
        binding.edtTensp.setText(product.getName());
        binding.edtGia.setText(product.getPrice());
        binding.edtSoluong.setText(product.getQuanl());
        binding.edtMieuta.setText(product.getDes());
        imageUrl = null; //reset url
        madm = null; //reset madm
        //Gán madm từ masp
//        if(masp!=null) {
//            prodRef.child(masp).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    madm = snapshot.child("madm").getValue().toString();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
        //Gán tên danh mục vào input
        cateRef.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(madm!=null && snapshot.getKey().equals(madm)) {
                    binding.edDanhmuc.setText(snapshot.child("tendm").getValue().toString());
                }
                categories.add(Objects.requireNonNull(snapshot.child("tendm").getValue()).toString());
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
        //Khi nhấn vào ô danh mục
        binding.edDanhmuc.setOnClickListener(view -> {
            dmAdapter = new ArrayAdapter<>(ProductDetails.this, android.R.layout.simple_dropdown_item_1line, categories);
            binding.edDanhmuc.setAdapter(dmAdapter);
            binding.edDanhmuc.showDropDown();
        });
        //Khi chọn một danh mục
        binding.edDanhmuc.setOnItemClickListener((parent, view, position, id) -> {
            String tendm = binding.edDanhmuc.getText().toString();
            madm = null; //Bỏ madm cũ
            cateRef.orderByKey().addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if (Objects.requireNonNull(snapshot.child("tendm").getValue()).toString().equals(tendm)) {
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
        //Gán hình và thông tin sản phẩm vào các input
        prodRef.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(masp!=null && snapshot.getKey().equals(masp)) {
                    if (snapshot.child("hinhsp").getValue() != null) {
                        imageUrl = Objects.requireNonNull(snapshot.child("hinhsp").getValue().toString());
                        Picasso.get().load(imageUrl).into(binding.imgHinhanh);
                    } else {
                        binding.imgHinhanh.setImageResource(R.mipmap.ic_launcher);
                    }
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

        //Nút lưu
        binding.btnLuu.setOnClickListener(v-> {

        });

        //Nút xóa
        binding.btnXoa.setOnClickListener(v-> deleteProduct(imageUrl, masp));

        //Nút back
        binding.backBtnAdd.setOnClickListener(view -> finish());
    }
    // Select Image method
    @SuppressWarnings("deprecation")
    private void SelectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }
    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(getContentResolver(), filePath);
                binding.imgHinhanh.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //Kiểm tra điều kiện
    private boolean validateInput(String madm, String tensp, String giasp, String soluong, String motasp) {
        if (madm == null) {
            Toast.makeText(ProductDetails.this, "Danh mục trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tensp.trim().isEmpty() || giasp.trim().isEmpty() || soluong.trim().isEmpty() || motasp.trim().isEmpty()) {
            Toast.makeText(ProductDetails.this, "Vui lòng nhập đầy đủ tất cả thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(giasp) <= 0 || Integer.parseInt(soluong) <= 0) {
            Toast.makeText(ProductDetails.this, "Vui lòng nhập chính xác tất cả thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (filePath == null) {
            Toast.makeText(ProductDetails.this, "Hình ảnh trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    // uploadProduct method
    private void updateProduct(StorageReference imgRef, String tensp, String motasp, int giasp, int soluong) {
        imgRef.putFile(filePath)
                .addOnSuccessListener(taskSnapshot ->
                        Toast.makeText(this, "Image upload success", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Image upload fail", Toast.LENGTH_SHORT).show());

        imgRef.putFile(filePath).continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw Objects.requireNonNull(task.getException());
            } return imgRef.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            String imageUri = task.getResult().toString();
            prodRef.push().setValue(new DBProduct(madm, imageUri, tensp, motasp, giasp, soluong));
            Toast.makeText(ProductDetails.this, "Lưu sản phẩm thành công", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
    //deleteProduct method
    private void deleteProduct(String imageUrl, String masp) {
        if(imageUrl != null && masp != null) {
            StorageReference imgRef = storage.getReferenceFromUrl(imageUrl);
            prodRef.child(masp).removeValue();
            imgRef.delete();
            Toast.makeText(this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Không đủ điều kiên xóa", Toast.LENGTH_SHORT).show();
        }
    }
}