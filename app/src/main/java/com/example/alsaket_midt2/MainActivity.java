package com.example.alsaket_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button btnActivity2, btnActivity3;
    TextView showDate, temperature, clouds;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker =  findViewById(R.id.datePicker);
        showDate = findViewById(R.id.showDate);
        temperature = findViewById(R.id.temperature);
        btnActivity2 = findViewById(R.id.btnActivity2);
        btnActivity3 = findViewById(R.id.btnActivity3);
        clouds = findViewById(R.id.clouds);

        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity (new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        btnActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity (new Intent(MainActivity.this, MainActivity3.class));
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                String day = ""+datePicker.getDayOfMonth();
                String month =  ""+ (datePicker.getMonth() + 1);
                String year = ""+ datePicker.getYear();

                showDate.setText(day+"-"+month+"-"+year);
            }
        });

    }
}