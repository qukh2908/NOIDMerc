package com.example.noidmerchant.GUI.Statistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.noidmerchant.Database.DBOrder;
import com.example.noidmerchant.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class StatisticsActivity extends AppCompatActivity {

    ImageView back_btn;
    //    PieChart pieChart;
    EditText edtDate;
    LineDataSet lineDataSet = new LineDataSet(null,null);
    ArrayList<ILineDataSet>iLineDataSets = new ArrayList<>();
    LineData lineData;

    private LineChart lineChart;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("dathang");;
    private EditText editTextDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        back_btn = findViewById(R.id.back_btn);
        lineChart = findViewById(R.id.linechart);
        lineDataSet.setLineWidth(4);
        editTextDate = findViewById(R.id.editTextDate);

        /*datetime*/
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        StatisticsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                editTextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry>dataVal = new ArrayList<Entry>();
                if(snapshot.hasChildren()){
                    for (DataSnapshot data : snapshot.getChildren())
                    {
                        DBOrder DBOrder = data.getValue(DBOrder.class);
                        dataVal.add(new Entry(DBOrder.getThoigiandh().length(), DBOrder.getTongtiendh()));
                    }
                    showChart(dataVal);
                }
                else {
                    lineChart.clear();
                    lineChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //nút back
        back_btn.setOnClickListener(v-> {
            finish();
        });
    }

    private void showChart(ArrayList<Entry> dataVal) {
        lineDataSet.setValues(dataVal);
        lineDataSet.setLabel("Data set 1");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData  = new LineData(iLineDataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}