package com.example.noidmerchant.GUI.Imports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.noidmerchant.R;

public class BuyProductActivity extends AppCompatActivity {

    AutoCompleteTextView edt_danhmuc;
    ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyproduct);
        edt_danhmuc = findViewById(R.id.edt_danhmuc);
        back_btn = findViewById(R.id.back_btn);

        String[] drinks = {"Cà phê", "Nước ngọt", "Sinh tố"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, drinks);
        edt_danhmuc.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView


        //nút back
        back_btn.setOnClickListener(v -> finish());
    }
}