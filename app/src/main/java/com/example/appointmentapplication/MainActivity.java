package com.example.appointmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appointmentapplication.ADM.ADM;
import com.example.appointmentapplication.ADM.ADMOtp;
import com.example.appointmentapplication.DM.DM;
import com.example.appointmentapplication.DM.DMOtp;
import com.example.appointmentapplication.SDMPackage.SDM1Main;
import com.example.appointmentapplication.SDMPackage.SDMMain;
import com.example.appointmentapplication.SDMPackage.SDMOTP;

public class MainActivity extends AppCompatActivity {

    private Button dm,adm,sdm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm=findViewById(R.id.dm);
        adm=findViewById(R.id.adm);
        sdm=findViewById(R.id.sdm);

        dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DMOtp.class));
            }
        });

        adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ADMOtp.class));
            }
        });

        sdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SDMOTP.class));
            }
        });
    }
}