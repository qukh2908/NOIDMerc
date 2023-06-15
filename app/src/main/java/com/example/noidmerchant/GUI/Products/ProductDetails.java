package com.example.noidmerchant.GUI.Products;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noidmerchant.Adapter.Product;
import com.example.noidmerchant.Database.DBProduct;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ProductDetails extends AppCompatActivity {
    final int PICK_IMAGE_REQUEST = 22;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseStorage storage = FirebaseStorage.getInstance();
    final DatabaseReference prodRef = database.getReference().child("sanpham");
    final DatabaseReference cateRef = database.getReference().child("danhmucsp");
    final StorageReference prodStorageRef = storage.getReference().child("hinhSanPham");
    final ArrayList<String> categories = new ArrayList<>();
    private DialogInterface.OnClickListener dialogClickListener;
    private ArrayAdapter<String> dmAdapter;
    private DetailsProductBinding binding;
    private String madm, masp, defaultImageUrl, tensp, giasp, soluong, motasp;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            Product product = (Product) bundle.get("sanpham");
            madm = product.getCateKey();
            masp = product.getKey();
            cateRef.child(madm).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    binding.edDanhmuc.setText(Objects.requireNonNull(snapshot.child("tendm").getValue()).toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            defaultImageUrl = product.getImageUrl(); filePath = null;
            binding.edtTensp.setText(product.getName());
            binding.edtGia.setText(product.getPrice());
            binding.edtSoluong.setText(product.getQuantity());
            binding.edtMieuta.setText(product.getDescription());
            Picasso.get().load(defaultImageUrl).into(binding.imgHinhanh);
        } else {
            Toast.makeText(ProductDetails.this, "Không lấy được thông tin sản phẩm", Toast.LENGTH_SHORT).show();
            finish();
        }
        //Thêm tên danh mục vào mảng
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
        //Khi nhấn chon hình
        binding.btnInputhinh.setOnClickListener(v -> SelectImage());
        binding.imgHinhanh.setOnClickListener(v-> SelectImage());
        //Nút lưu
        binding.btnLuu.setOnClickListener(v-> {
            tensp = binding.edtTensp.getText().toString();
            giasp = binding.edtGia.getText().toString();
            soluong = binding.edtSoluong.getText().toString();
            motasp = binding.edtMieuta.getText().toString();
            if (validateInput(madm, tensp, giasp, soluong, motasp, filePath)) {
                if(filePath != null) { //Nếu chọn hình mới (đường dẫn cũ trống)
                    StorageReference newImgRef = prodStorageRef.child( madm + "/" + UUID.randomUUID());
                    updateProduct(masp, newImgRef, tensp, motasp, Integer.parseInt(giasp), Integer.parseInt(soluong));
                } else {
                    updateProduct(masp, null, tensp, motasp, Integer.parseInt(giasp), Integer.parseInt(soluong));
                }
            }
        });
        //Nút xóa
        binding.btnXoa.setOnClickListener(v-> {
            dialogClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        deleteProduct(defaultImageUrl, masp);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetails.this);
            builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?")
                    .setPositiveButton("Có", dialogClickListener)
                    .setNegativeButton("Không", dialogClickListener)
                    .show();
        });
        //Nút back
        binding.backBtnAdd.setOnClickListener(view -> finish());
    }

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
    // Select Image method
    @SuppressWarnings("deprecation")
    private void SelectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }
    //Kiểm tra điều kiện
    private boolean validateInput(String madm, String tensp, String giasp,
                                  String soluong, String motasp, Uri filePath) {
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
        if (filePath == null && defaultImageUrl == null) {
            Toast.makeText(ProductDetails.this, "Hình ảnh trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //updateProduct method
    private void updateProduct(String masp, StorageReference newImgRef, String tensp,
                               String motasp, int giasp, int soluong) {
        if (newImgRef != null) {
            StorageReference previousImgRef = storage.getReferenceFromUrl(defaultImageUrl);
            previousImgRef.delete();
            newImgRef.putFile(filePath)
                    .addOnSuccessListener(taskSnapshot ->
                            Toast.makeText(this, "Image upload success", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Image upload fail", Toast.LENGTH_SHORT).show());
            newImgRef.putFile(filePath).continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    throw Objects.requireNonNull(task.getException());
                }
                return newImgRef.getDownloadUrl();
            }).addOnCompleteListener(task -> {
                String newImageUrl = task.getResult().toString();
                prodRef.child(masp).setValue(new DBProduct(madm, newImageUrl, tensp, motasp, giasp, soluong));
                Toast.makeText(ProductDetails.this, "Lưu sản phẩm thành công", Toast.LENGTH_SHORT).show();
                finish();
            });
        } else {
            prodRef.child(masp).setValue(new DBProduct(madm, defaultImageUrl, tensp, motasp, giasp, soluong));
            Toast.makeText(ProductDetails.this, "Lưu sản phẩm thành công", Toast.LENGTH_SHORT).show();
            finish();
        }
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