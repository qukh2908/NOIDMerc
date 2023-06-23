package com.example.noidmerchant.GUI.Statistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.noidmerchant.Database.DBOrder;
import com.example.noidmerchant.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    ImageView back_btn;
    //    PieChart pieChart;
    EditText edtDate;

    LineData lineData;

    private LineChart lineChart;
    private List<String>list;

    private EditText editTextDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        back_btn = findViewById(R.id.back_btn);
        lineChart = findViewById(R.id.linechart);

        editTextDate = findViewById(R.id.editTextDate);
        Description description = new Description();
        description.setText("Thong ke");
        description.setPosition(150f,15f);
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false);
        list = Arrays.asList("Thu hai","Thu ba","Thu tu","Thu nam","Thu sau","Thu Bay","Chu nhat");
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));
        xAxis.setLabelCount(7);
        xAxis.setGranularity(1f);

        YAxis yAxis  = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        List<Entry>entries = new ArrayList<>();
        entries.add(new Entry(0,10f));
        entries.add(new Entry(1,10f));
        entries.add(new Entry(2,15f));
        entries.add(new Entry(3,45f));
        entries.add(new Entry(4,56f));
        entries.add(new Entry(5,14f));
        entries.add(new Entry(6,32f));

        List<Entry>entries1 = new ArrayList<>();
        entries1.add(new Entry(0,5f));
        entries1.add(new Entry(1,12f));
        entries1.add(new Entry(2,7f));
        entries1.add(new Entry(3,9f));
        entries1.add(new Entry(4,75f));
        entries1.add(new Entry(5,44f));
        entries1.add(new Entry(6,21f));



        LineDataSet dataSet =new LineDataSet(entries,"Maths");
        dataSet.setColor(Color.BLUE);

        LineDataSet dataSet1 =new LineDataSet(entries1,"Sicence");
        dataSet1.setColor(Color.RED);

        lineData = new LineData(dataSet,dataSet1);
        lineChart.setData(lineData);
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

        //nút back
        back_btn.setOnClickListener(v-> {
            finish();
        });
    }


}