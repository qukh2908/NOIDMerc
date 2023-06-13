package com.example.noidmerchant.GUI.Products;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ProductAdd extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final int PICK_IMAGE_REQUEST = 22;
    private final DatabaseReference cateRef = database.getReference().child("danhmucsp");
    private final DatabaseReference prodRef = database.getReference().child("sanpham");
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference = storage.getReference();
    private Uri filePath;
    private AddProductBinding binding;
    private ArrayAdapter<String> dmAdapter;
    private String madm, tensp, giasp, soluong, motasp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddProductBinding.inflate(getLayoutInflater());
        ArrayList<String> categories = new ArrayList<>();
        madm = null; //reset madm
        cateRef.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
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
            dmAdapter = new ArrayAdapter<>(ProductAdd.this, android.R.layout.simple_dropdown_item_1line, categories);
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
        //Khi nhấn vào ô chon hình
        binding.btnInputhinh.setOnClickListener(v -> SelectImage());
        //Khi nhấn nút lưu
        binding.btnLuu.setOnClickListener(v -> {
            tensp = binding.edtTensp.getText().toString();
            giasp = binding.edtGia.getText().toString();
            soluong = binding.edtSoluong.getText().toString();
            motasp = binding.edtMieuta.getText().toString();
            if (validateInput(madm, tensp, giasp, soluong, motasp)) {
                StorageReference imgRef = storageReference.child("hinhSanPham/" + madm + "/" + UUID.randomUUID());
                uploadProduct(imgRef,tensp, motasp, Integer.parseInt(giasp), Integer.parseInt(soluong));
            }
        });

        setContentView(binding.getRoot());
        binding.backBtnAdd.setOnClickListener(v -> finish());
    }

    private boolean validateInput(String madm, String tensp, String giasp, String soluong, String motasp) {
        if (madm == null) {
            Toast.makeText(ProductAdd.this, "Danh mục trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tensp.trim().isEmpty() || giasp.trim().isEmpty() || soluong.trim().isEmpty() || motasp.trim().isEmpty()) {
            Toast.makeText(ProductAdd.this, "Vui lòng nhập đầy đủ tất cả thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(giasp) <= 0 || Integer.parseInt(soluong) <= 0) {
            Toast.makeText(ProductAdd.this, "Vui lòng nhập chính xác tất cả thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (filePath == null) {
            Toast.makeText(ProductAdd.this, "Hình ảnh trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
    // uploadProduct method
    private void uploadProduct(StorageReference imgRef, String tensp, String motasp, int giasp, int soluong) {
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
            Toast.makeText(ProductAdd.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}