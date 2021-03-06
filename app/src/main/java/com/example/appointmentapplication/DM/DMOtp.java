package com.example.appointmentapplication.DM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.appointmentapplication.ADM.ADMOtp;
import com.example.appointmentapplication.ADM.ADMOtpVerify;
import com.example.appointmentapplication.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class DMOtp extends AppCompatActivity {


    private Button button;
    private EditText phoneno;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_m_otp);

        phoneno=findViewById(R.id.inputMobiledm);
        button=findViewById(R.id.buttongetOtpdm);
        progressBar=findViewById(R.id.progressbardm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneno.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Mobile",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+phoneno.getText().toString(),60,
                        TimeUnit.SECONDS,
                        DMOtp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                        {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);
                                Toast.makeText(DMOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                //   progressBar.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);
                                Intent intent=new Intent(getApplicationContext(), DMOtpVerify.class);
                                intent.putExtra("mobile",phoneno.getText().toString());
                                intent.putExtra("verificationId",verificationId);
                                startActivity(intent);
                            }
                        });

            }
        });
    }
}
