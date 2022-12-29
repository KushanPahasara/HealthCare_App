package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {


    Button btnmedi,btnappoint,btnbill,btnlogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnappoint = findViewById(R.id.btnhomeapp);
        btnbill =findViewById(R.id.btnhomebill);
        btnmedi = findViewById(R.id.btnhomemedi);
        btnlogout = findViewById(R.id.btnhomelogout);


        btnmedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MedicineInfo.class);
                startActivity(i);
            }
        });

        btnappoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Appointment.class);
                startActivity(i);

            }
        });



        btnbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Bill.class);
                startActivity(i);

            }
        });



        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });


    }
}