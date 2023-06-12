package com.example.noidmerchant.GUI.Orders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.noidmerchant.Database.DBExport;
import com.example.noidmerchant.Database.DBOrder;
import com.example.noidmerchant.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class OrdersActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseUser userUID = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = userUID.getUid(); //Lấy mã khách hàng hiện đang login
    private DatabaseReference prodRef = database.getReference("sanpham");
    private DatabaseReference expRef = database.getReference().child("xuatban");
    private DatabaseReference ordRef = database.getReference().child("donhangmua");
    private ImageView back_btn;
    private String masp = ""; //masp tùy khách chọn sp nào thì getkey sp đó gán vào
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
//                expRef.push().setValue(new DBExport(uid, masp, soluongxb, giaxb, " "));
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
//        }); //Thêm xuất bán lên realtime DB


        //Thêm don hang mới lên realtime DB
//        expRef.orderByKey().addChildEventListener(new ChildEventListener() {
//            int xbLength = -1;
//            int sum = 0;
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                xbLength++;
//                DBExport[] export = new DBExport[xbLength + 1];
//
//                String matk = snapshot.child("matk").getValue().toString();
//                String masp = snapshot.child("masp").getValue().toString();
//                int soluongxb = snapshot.child("soluongxb").getValue().hashCode();
//                int giaxb = snapshot.child("giaxb").getValue().hashCode();
//                int phigiaohang = 30000;
//                String madhm = snapshot.child("madhm").getValue().toString();
//
//                export[xbLength] = new DBExport(matk, masp, soluongxb,giaxb,madhm);
//                sum += export[xbLength].getGiaKhCoMadhm();
//                Log.i("ADMIN", "Gia tong:" + sum);
//                if(xbLength==2) {
//                    sum += phigiaohang;
//                    ordRef.push().setValue(new DBOrder(matk, "Đang giao", "NOTE",phigiaohang, sum, ServerValue.TIMESTAMP));
//                }
//            }
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//
//        }); //Thêm don hang mới lên realtime DB

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}