package com.example.appointmentapplication.SDMPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appointmentapplication.R;

public class SDMMain extends AppCompatActivity {

    Button btnsdm1,btnsdm2,btnsdm3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_d_m_main);

        btnsdm1=findViewById(R.id.btnsdm1);
        btnsdm2=findViewById(R.id.btnsdm2);
        btnsdm3=findViewById(R.id.btnsdm3);

        btnsdm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SDM1Main.class));
            }
        });
        btnsdm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SDM2Main.class));
            }
        });
        btnsdm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SDM3Main.class));
            }
        });

    }
}