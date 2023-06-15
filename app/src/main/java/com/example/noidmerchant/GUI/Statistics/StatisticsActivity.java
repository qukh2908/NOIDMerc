package com.example.noidmerchant.GUI.Statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

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

    private LineChart lineChart ;
    private List<String> xValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> finish());
        lineChart = findViewById(R.id.linechart);
        Description description = new Description();
        description.setText("so do doanh thu");
        description.setPosition(150f, 15f);
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false);
        xValues = Arrays.asList("Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ nhật");

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(7);
        xAxis.setGranularity(1f);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0, 10f));
        entries1.add(new Entry(1, 10f));
        entries1.add(new Entry(2, 15f));
        entries1.add(new Entry(3, 20f));
        entries1.add(new Entry(4, 25f));
        entries1.add(new Entry(5, 10f));
        entries1.add(new Entry(6, 40f));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0, 10f));
        entries2.add(new Entry(1, 76f));
        entries2.add(new Entry(2, 31f));
        entries2.add(new Entry(3, 34f));
        entries2.add(new Entry(4, 29f));
        entries2.add(new Entry(5, 43f));
        entries2.add(new Entry(6, 20f));


        LineDataSet dataSet1 = new LineDataSet(entries1, "Doanh Thu");
        dataSet1.setColors(Color.BLUE);

        LineDataSet dataSet2 = new LineDataSet(entries2, "Chi");
        dataSet2.setColors(Color.RED);

        LineData lineData = new LineData(dataSet1, dataSet2);

        lineChart.setData(lineData);
        lineChart.invalidate();


        edtDate = (EditText) findViewById(R.id.editTextDate);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ChonNgay();
            }
        });
    }
    private void ChonNgay() {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                //i: năm - i1:tháng - i2:ngày
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtDate.setText(simpleDateFormat.format(calendar.getTime()));

            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }


        //nút back

    }