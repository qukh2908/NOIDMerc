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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    //tao bien  Firebase Authentication
    private FirebaseAuth auth;
    //tao bien cho edittext cua email va password
    private TextInputEditText signupEmail,signupPassword;
    //bien nut dang ki
    private Button btnSignup;
    //bien quen mat khau
    private TextView loginRedirectText;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Tim den dung duong dan cac id
        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.edtEmail);
        signupPassword = findViewById(R.id.edtPassword);
        btnSignup = findViewById(R.id.btnSignUp);
        loginRedirectText = findViewById(R.id.txtSignIn);
        //Bat su kien cho dang ki
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gan user va password
                String user = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                //Xet dieu kien cho user va password
                if(user.isEmpty()){
                    signupEmail.setError("Email khong duoc de trong");
                }
                if(password.isEmpty())
                {
                    signupPassword.setError("Mat khau khong duoc de trong");
                }else {
                    //Bat su kien cho Auth
                    auth.createUserWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Xet dieu kien cho email va mat khau
                            if(task.isSuccessful()){
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
}
