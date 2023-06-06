package com.example.noidmerchant.GUI.Common;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noidmerchant.MainActivity;
import com.example.noidmerchant.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private TextInputEditText loginEmail, loginPassword;
    private Button btnSignIn;
    private TextView txtForgotPassword;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Log.i("ADMIN", "Current user: " + currentUser.getUid());
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        /* Authentication */
        btnSignIn.setOnClickListener(v -> {
            String email = loginEmail.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();

            /* Kiểm tra Email cửa hàng */
            if (!email.equals("kylekhanh1028@gmail.com")) {
                Toast.makeText(LoginActivity.this, "Địa chỉ email hoặc tên đăng nhập không được cho phép!", Toast.LENGTH_SHORT).show();
            }
            /* Kiểm tra Email cửa hàng */
            else //noinspection ConstantConditions
                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (!password.isEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show());
                } else {
                    loginPassword.setError("Mật khẩu không được để trống");
                }
            } else //noinspection ConstantConditions
                    if (email.isEmpty()) {
                loginEmail.setError("Tên đăng nhập không được để trống!");
            } else {
                loginEmail.setError("vui lòng nhập email thực!");
            }
        });
        /* Authentication */

        /* Mở dialog quên mật khẩu */
        txtForgotPassword.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            View dialogview = getLayoutInflater().inflate(R.layout.dialog_forgotpassword, null);
            TextInputEditText edtForgotEmail = dialogview.findViewById(R.id.edtForgotEmail);

            builder.setView(dialogview);
            AlertDialog dialog = builder.create();

            dialogview.findViewById(R.id.btnConfirm).setOnClickListener(v1 -> {
                String userEmail = edtForgotEmail.getText().toString();
                if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                    Toast.makeText(LoginActivity.this, "Nhập email đăng kí tài khoản!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Vui lòng kiểm tra Email!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(LoginActivity.this, "Không thể gửi, vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                    }
                });
            });
            dialogview.findViewById(R.id.btnCancel).setOnClickListener(v12 -> dialog.dismiss());
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            dialog.show();
        });
    }
}