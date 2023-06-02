package com.example.noidmerchant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
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
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gan user va password
                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String username = edtUsername.getText().toString().trim();
                //Xet dieu kien cho user va password
                if(email.isEmpty()){
                    signupEmail.setError("Email khong duoc de trong");
                }
                if (name.isEmpty()) {
                    edtName.setError("Tên hiển thị không được để trống!");
                }
                if (username.isEmpty()) {
                    edtUsername.setError("Tên đăng nhập không được để trống!");
                }
                if(password.isEmpty())
                {
                    signupPassword.setError("Mat khau khong duoc de trong");
                }else {
                    //Bat su kien cho Auth
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Xet dieu kien cho email va mat khau
                            if(task.isSuccessful()){
                                database = FirebaseDatabase.getInstance();
                                reference = database.getReference("Admin");
                                DBUser dbUser = new DBUser(email, name, username, password);
                                reference.child(username).setValue(dbUser);
                                //realtime
                                Toast.makeText(RegisterActivity.this,"Dang ky thanh cong",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                            }else{
                                Toast.makeText(RegisterActivity.this,"Mat Khau hoac email bi sai"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
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
