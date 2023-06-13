package com.example.noidmerchant.GUI.Products;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
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

public class ProductAdd extends AppCompatActivity {
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
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
            dmAdapter = new ArrayAdapter<> (ProductAdd.this, android.R.layout.simple_dropdown_item_1line, categories);
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

        binding.btnInputhinh.setOnClickListener(v-> {
            SelectImage();
        });

        binding.btnLuu.setOnClickListener(v-> {
            String tensp = binding.edtTensp.getText().toString();
            int giasp = Integer.parseInt(binding.edtGia.getText().toString());
            int soluong = Integer.parseInt(binding.edtSoluong.getText().toString());
            String motasp = binding.edtMieuta.getText().toString();
            String hinhsp = "https://firebasestorage.googleapis.com/v0/b/noidapp221.appspot.com/o/hinhSanPham%2FCoffee%2Fcafe_1.png?alt=media&token=6d951c58-81be-4b5e-b46e-2c4d59d44f1a";
            prodRef.push().setValue(new DBProduct(madm, hinhsp, tensp, motasp, giasp, soluong));
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            finish();
        });

        setContentView(binding.getRoot());
        binding.backBtnAdd.setOnClickListener(v -> finish());
    }
    // Select Image method
    private void SelectImage()
    {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image from here..."),PICK_IMAGE_REQUEST);
    }
    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(getContentResolver(),filePath);
                binding.imgHinhanh.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}