package com.example.alsaket_midt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    Button print, delete, act2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        print = (Button) findViewById(R.id.print);
        delete = (Button) findViewById(R.id.delete);
        act2 = (Button) findViewById(R.id.act2);
        EditText userinput = (EditText) findViewById(R.id.userinput);

        DatabaseHelper db = new DatabaseHelper(this);

        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.View();
                StringBuffer buffer = new StringBuffer();

                while (cur.moveToNext()){

                    buffer.append("ID: "+ cur.getString(0) + "\n");
                    buffer.append("Name: "+ cur.getString(2) + "\n");
                    buffer.append("National ID: "+ cur.getString(1) + "\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true);
                builder.setTitle("All Results");
                builder.setMessage(buffer.toString());
                builder.show();

                Toast.makeText(MainActivity3.this, "Successful View", Toast.LENGTH_LONG).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String name_val = userinput.getText().toString();
                    db.Delete(name_val);
                    Log.d("Asma", "after deleting value");
                    Toast.makeText(MainActivity3.this, "Deletion Successful. You deleted: " + name_val, Toast.LENGTH_SHORT).show();
                }
                catch(Exception e) {
                    String input_val = userinput.getText().toString();
                    Toast.makeText(MainActivity3.this, "you did not delete: " + input_val, Toast.LENGTH_SHORT).show();
                }
            }
        });

        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this,MainActivity2.class));
            }
        });
    }
}