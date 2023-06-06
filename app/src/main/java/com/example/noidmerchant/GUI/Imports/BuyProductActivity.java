package com.example.noidmerchant.GUI.Imports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TableRow;

import com.example.noidmerchant.R;

import java.util.ArrayList;

public class BuyProductActivity extends AppCompatActivity {

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

        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}