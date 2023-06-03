package com.example.noidmerchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InformationsActivity extends AppCompatActivity {
    String path = "taikhoan/";
    Button btnSave;
    FirebaseDatabase database;
    DatabaseReference refEmail,refName,refAdd,refPhone;
    EditText txtEmail,txtPhone,txtAdd,txtName;
    String uid;
    ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);
        back_btn = findViewById(R.id.back_btn);
        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPhone = findViewById(R.id.txt_sdt);
        txtAdd = findViewById(R.id.txt_diachi);
        btnSave = findViewById(R.id.btn_luu);
        database = FirebaseDatabase.getInstance();
        FirebaseUser userUID =  FirebaseAuth.getInstance().getCurrentUser();
//        assert userUID != null ;
        uid = userUID.getUid();

        if(uid != null) {
            Log.d("ADebugTag", "UID: " + uid);
        }

        refEmail = database.getReference(path + uid + "/email");
        refName = database.getReference(path + uid + "/name");
        refPhone = database.getReference(path + uid + "/phone");
        refAdd = database.getReference(path + uid + "/address");

        refName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gName = snapshot.getValue(String.class);
                txtName.setText(gName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        refPhone.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gPhone = snapshot.getValue(String.class);
                txtPhone.setText(gPhone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        refAdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gAdd = snapshot.getValue(String.class);
                txtAdd.setText(gAdd);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        refEmail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gEmail = snapshot.getValue(String.class);
                txtEmail.setText(gEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        back_btn.setOnClickListener(v -> {
            finish();
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refEmail.setValue(txtEmail.getText().toString());
                refAdd.setValue(txtAdd.getText().toString());
                refName.setValue(txtName.getText().toString());
                refPhone.setValue(txtPhone.getText().toString());
            }
        });
    }
}