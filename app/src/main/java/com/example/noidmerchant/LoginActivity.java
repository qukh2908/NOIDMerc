package com.example.noidmerchant;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    // tao bien Authentication
    private FirebaseAuth auth;
    //Tao bien login email va password
    private TextInputEditText logUsername,logPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        logUsername = findViewById(R.id.loginUsername);
        logPassword = findViewById(R.id.loginPassword);
        //Tao bien nut dang nhap
        Button loginButton = findViewById(R.id.btnSignIn);
        //Tao bien cho Dang ky va quen mat khau
        TextView signupRedirectText = findViewById(R.id.txtRegAccount);
        TextView forgotPassword = findViewById(R.id.txtForgotPassword);
//      Authentication
        loginButton.setOnClickListener(v -> {
            String email = logUsername.getText().toString();
            String pass = logPassword.getText().toString();

            if(!email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (!pass.isEmpty()){
                    auth.signInWithEmailAndPassword(email,pass)
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(LoginActivity.this,"Dang Nhap Thanh cong",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this,"Sai mat khau hoac email vui long nhap lai",Toast.LENGTH_SHORT).show());
                }else{
                    logPassword.setError("Empty fields are not allowed");
                }
            }else if (email.isEmpty()){
                logUsername.setError("Empty fields are not allowed");
            }else{
                logUsername.setError("Please enter correct email");
            }
        });
        //      Authentication

        /* Realtime DB */
        loginButton.setOnClickListener(v -> {
            if (!checkUsername() | !checkPassword()) {
                //
            } else {
                checkUser();
            }
        });
        /* Mở activity Tạo tài khoản */
        signupRedirectText.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        /* Mở dialog quên mật khẩu */
        forgotPassword.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgotpassword, null);
            TextInputEditText emailBox = dialogView.findViewById(R.id.edtForgotEmail);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();
            dialogView.findViewById(R.id.btnConfirm).setOnClickListener(v12 -> {
                String userEmail = emailBox.getText().toString();
                if(TextUtils.isEmpty(userEmail)&&!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    Toast.makeText(LoginActivity.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(LoginActivity.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                    }
                });
            });
            dialogView.findViewById(R.id.btnCancel).setOnClickListener(v1 -> dialog.dismiss());
            if(dialog.getWindow()!=null)
            {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            dialog.show();
        });
    }
    public Boolean checkUsername(){
        String val =logUsername.getText().toString();
        if(val.isEmpty()){
            logUsername.setError("Không được để tên đăng nhập trống!");
            return false;
        }else{
            logUsername.setError(null);
            return true;
        }
    }
    public Boolean checkPassword () {
        String val = logPassword.getText().toString();
        if (val.isEmpty()){
            logPassword.setError("Không được để tên đăng nhập trống!");
            return false;
        }else{
            logPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername = logUsername.getText().toString().trim();
        String userPassword = logPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("taikhoan");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    logUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userPassword)) {
                        logUsername.setError(null);
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công! Chào mừng " + nameFromDB, Toast.LENGTH_SHORT).show();
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
                        logPassword.setError("Sai mật khẩu!");
                        logPassword.requestFocus();
                    }
                } else {
                    logUsername.setError("Tên đăng nhập không tồn tại!");
                    logUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
