package com.example.noidmerchant.GUI.Orders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noidmerchant.Database.DBExport;
import com.example.noidmerchant.Database.DBProduct;
import com.example.noidmerchant.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrdersActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference prodRef = database.getReference("sanpham");
    DatabaseReference expRef = database.getReference().child("xuatban");
    DatabaseReference ordRef = database.getReference().child("donhangmua");
    ImageView back_btn;
    FirebaseUser userUID = FirebaseAuth.getInstance().getCurrentUser();
    String uid= userUID.getUid(); //Lấy mã khách hàng hiện đang login

//    String masp = "-NXP2TOjFqGV2o1xzY3l"; //masp tùy khách chọn sp nào thì getkey sp đó gán vào
//    int soluongxb = 2; //số lợng tùy khách nhập

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        back_btn = findViewById(R.id.back_btn);

        //Thêm xuất bán lên realtime DB
//        prodRef.child(masp).child("giasp").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                int giasp = snapshot.getValue(int.class);
//                int giaxp = giasp * soluongxb;
//                expRef.push().setValue(new DBExport(uid, masp, soluongxb, giaxp, " "));
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                //
//            }
//        });
        //Thêm xuất bán lên realtime DB

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}