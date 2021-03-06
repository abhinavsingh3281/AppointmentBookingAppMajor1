package com.example.appointmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AppointmentBookSuccessful extends AppCompatActivity {

    Button proceddd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_book_successful);

        proceddd=findViewById(R.id.proceddd);

        proceddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppointmentBookSuccessful.this,MainActivity.class));
                Toast.makeText(AppointmentBookSuccessful.this, "Thank you for using our services.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}