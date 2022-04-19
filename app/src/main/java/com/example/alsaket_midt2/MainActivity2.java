package com.example.alsaket_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView name, id, nid;
    Button insert, act1, act3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         name = (TextView) findViewById(R.id.name);
         id = (TextView) findViewById(R.id.id);
         nid = (TextView) findViewById(R.id.nID);
         insert = (Button) findViewById(R.id.insert);
         act1 = (Button) findViewById(R.id.act1);
         act3 = (Button) findViewById(R.id.act3);

        act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
            }
        });

        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity3.class));
            }
        });

        DatabaseHelper db =new DatabaseHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id_val = Integer.parseInt(id.getText().toString());
                    int nid_val = Integer.parseInt(nid.getText().toString());
                    String name_val = name.getText().toString();

                    db.Add(id_val, name_val, nid_val);
                    Log.d("Asma", "after adding value");
                }
                catch(Exception e) {
                    Toast.makeText(MainActivity2.this, "Insertion was UNSUCCESSFUL, try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    }