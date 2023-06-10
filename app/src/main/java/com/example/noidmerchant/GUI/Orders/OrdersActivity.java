package com.example.noidmerchant.GUI.Orders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noidmerchant.Database.DBExport;
import com.example.noidmerchant.Database.DBProduct;
import com.example.noidmerchant.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class OrdersActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseUser userUID = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = userUID.getUid(); //Lấy mã khách hàng hiện đang login
    private DatabaseReference prodRef = database.getReference("sanpham");
    private DatabaseReference expRef = database.getReference().child("xuatban");
    private DatabaseReference ordRef = database.getReference().child("donhangmua");
    private ImageView back_btn;
    private String masp = "-NXasd12szJE21VHadkf"; //masp tùy khách chọn sp nào thì getkey sp đó gán vào
    private int soluongxb = 2; //số lợng tùy khách nhập

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        back_btn = findViewById(R.id.back_btn);

        //Thêm xuất bán lên realtime DB
//        prodRef.child(masp).limitToFirst(1).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Object giasp = snapshot.getValue();
//                int giaxb = giasp.hashCode() * soluongxb;
//                expRef.push().child(maxb).setValue(new DBExport(uid, masp, soluongxb, giaxb, " "));
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Object giasp = snapshot.getValue();
//                int giaxb = giasp.hashCode() * soluongxb;
//                expRef.child(maxb).child("giaxb").setValue(giaxb);
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                expRef.child(maxb).child("madhm").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Object madhm = snapshot.getValue();
//                        if(madhm.toString().trim().isEmpty()) {
//                            expRef.child(maxb).removeValue();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        //
//                    }
//                });
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                //
//            }
//
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