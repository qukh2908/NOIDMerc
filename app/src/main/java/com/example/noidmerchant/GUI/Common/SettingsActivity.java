package com.example.noidmerchant.GUI.Common;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noidmerchant.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    private DialogInterface.OnClickListener dialogClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageView back_btn = findViewById(R.id.back_btn);
        TextView tv_logout = findViewById(R.id.tv_logout);

        //Nút đăng xuất
        tv_logout.setOnClickListener(v-> {
            dialogClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
            builder.setMessage("Bạn có chắc muốn rời bỏ tôi?")
                    .setPositiveButton("Có", dialogClickListener)
                    .setNegativeButton("Không", dialogClickListener)
                    .show();
        });

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}