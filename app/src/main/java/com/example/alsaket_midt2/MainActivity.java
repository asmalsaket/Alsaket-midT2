package com.example.alsaket_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button btnActivity2, btnActivity3;
    TextView showDate, temperature, clouds;
    Spinner cities;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        showDate = findViewById(R.id.showDate);
        temperature = findViewById(R.id.temperature);
        btnActivity2 = findViewById(R.id.btnActivity2);
        btnActivity3 = findViewById(R.id.btnActivity3);
        clouds = findViewById(R.id.clouds);
        cities = (Spinner) findViewById(R.id.spinner);

        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        btnActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                String day = "" + datePicker.getDayOfMonth();
                String month = "" + (datePicker.getMonth() + 1);
                String year = "" + datePicker.getYear();

                showDate.setText(day + "-" + month + "-" + year);
                //Log.d("Asma", "after showing date");
            }
        });


        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                   if (i == 0) {
                                                       String url = "http://api.openweathermap.org/data/2.5/weather?q=athens&appid=1f1d6588819771e3d082ba37cbf28916&units=metric";
                                                       weather(url);
                                                   } else if (i == 1) {
                                                       String url = "http://api.openweathermap.org/data/2.5/weather?q=riyadh&appid=1f1d6588819771e3d082ba37cbf28916&units=metric";
                                                       weather(url);
                                                   } else if (i == 2) {
                                                       String url = "http://api.openweathermap.org/data/2.5/weather?q=istanbul&appid=1f1d6588819771e3d082ba37cbf28916&units=metric";
                                                       weather(url);
                                                   }
                                               }
                                           });
    }

    public void weather(String url) {
        JsonObjectRequest jsonObj =
                new JsonObjectRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Asma", "Response received");
                        Log.d("Asma", response.toString());
                        try {
                            JSONObject jsonMain = response.getJSONObject("main");
                            JSONObject jsonSystem = response.getJSONObject("sys");

                            double temp = jsonMain.getDouble("temp");
                            Log.d("Asma-temp", String.valueOf(temp));
                            temperature.setText(String.valueOf(temp) + " C");


                            String cloudsTxt = jsonMain.getString("clouds");
                            Log.d("Asma-clouds", String.valueOf(cloudsTxt));
                            clouds.setText("Clouds: " + String.valueOf(cloudsTxt));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Receive Error", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Asma", "Error in retrieving the URL");
                    }

                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}