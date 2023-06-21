package com.example.noidmerchant.GUI.Statistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.noidmerchant.Adapter.Orders;
import com.example.noidmerchant.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.SimpleFormatter;

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
                        Orders orders = data.getValue(Orders.class);
                        dataVal.add(new Entry(orders.getThoigiandh().length(),orders.getTongtiendh()));
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