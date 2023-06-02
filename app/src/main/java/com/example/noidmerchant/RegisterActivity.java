package com.example.noidmerchant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    //tao bien  Firebase Authentication
    private FirebaseAuth auth;

    private FirebaseDatabase database;

    private DatabaseReference reference;
    //tao bien cho edittext cua email va password
    private TextInputEditText signupEmail,signupPassword,edtName, edtUsername;
    //bien nut dang ki
    private Button btnSignup;
    //bien quen mat khau
    private TextView loginRedirectText;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addView();
        // Tim den dung duong dan cac id
        //Bat su kien cho dang ki
        btnSignup.setOnClickListener(v -> {
            // gan user va password
            String email = signupEmail.getText().toString().trim();
            String matkhau = signupPassword.getText().toString().trim();
            String hoten = edtName.getText().toString().trim();
            String tendangnhap = edtUsername.getText().toString().trim();
            String vaitro = "cuahang";

            //Xet dieu kien cho user va password
            if(email.isEmpty()){
                signupEmail.setError("Email khong duoc de trong");
            }
            if (hoten.isEmpty()) {
                edtName.setError("Tên hiển thị không được để trống!");
            }
            if (tendangnhap.isEmpty()) {
                edtUsername.setError("Tên đăng nhập không được để trống!");
            }
            if(matkhau.isEmpty())
            {
                signupPassword.setError("Mat khau khong duoc de trong");
            } else {
                //Bat su kien cho Auth
                auth.createUserWithEmailAndPassword(email,matkhau).addOnCompleteListener(task -> {
                    //Xet dieu kien cho email va mat khau
                    if(task.isSuccessful()){
                        database = FirebaseDatabase.getInstance();
                        reference = database.getReference("taikhoan");
                        DBUser dbUser = new DBUser(email, hoten, tendangnhap, matkhau, vaitro);
                        reference.child(tendangnhap).setValue(dbUser);
                        //realtime
                        Toast.makeText(RegisterActivity.this,"Dang ky thanh cong",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this,"Mat Khau hoac email bi sai"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        loginRedirectText.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this,LoginActivity.class)));
    }
    private void addView(){
        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtUsername = findViewById(R.id.edtUsername);
        signupPassword = findViewById(R.id.edtPassword);
        btnSignup = findViewById(R.id.btnSignUp);
        loginRedirectText = findViewById(R.id.txtSignIn);
    }
}
