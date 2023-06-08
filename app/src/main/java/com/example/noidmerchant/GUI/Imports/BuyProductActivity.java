package com.example.noidmerchant.GUI.Imports;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noidmerchant.Database.DBImport;
import com.example.noidmerchant.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;


public class BuyProductActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference impRef = database.getReference().child("nhaphang");
    AutoCompleteTextView edt_danhmuc;
    ArrayAdapter<String> dmAdapter;
    ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyproduct);

        edt_danhmuc = findViewById(R.id.edt_danhmuc);
        back_btn = findViewById(R.id.back_btn);

        ArrayList<String> drinks = new ArrayList<String>();
        drinks.add("ABC");
        drinks.add("123");

        edt_danhmuc.setOnClickListener(v-> {
            dmAdapter = new ArrayAdapter<> (this, android.R.layout.simple_dropdown_item_1line, drinks);
            edt_danhmuc.setAdapter(dmAdapter);
            edt_danhmuc.showDropDown();
        });

        //Thêm nhập hàng lên realtime DB
        //impRef.push().setValue(new DBImport("example", 0, ServerValue.TIMESTAMP));
        //Thêm nhập hàng lên realtime DB

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}