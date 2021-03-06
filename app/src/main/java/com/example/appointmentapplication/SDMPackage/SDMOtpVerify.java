package com.example.appointmentapplication.SDMPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appointmentapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SDMOtpVerify extends AppCompatActivity {

    private EditText inputcode1,inputcode2,inputcode3,inputcode4,inputcode5,inputcode6;
    private ProgressBar progressBar;
    private Button verify;
    private String VerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_d_m_otp_verify);
        TextView textMobile=findViewById(R.id.textmobile);
        textMobile.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));

        inputcode1=findViewById(R.id.inputcode1sdm);
        inputcode2=findViewById(R.id.inputcode2sdm);
        inputcode3=findViewById(R.id.inputcode3sdm);
        inputcode4=findViewById(R.id.inputcode4sdm);
        inputcode5=findViewById(R.id.inputcode5sdm);
        inputcode6=findViewById(R.id.inputcode6sdm);

        progressBar=findViewById(R.id.progressbarsdmverify);
        verify=findViewById(R.id.buttonverifysdm);

        VerificationId=getIntent().getStringExtra("verificationId");

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputcode1.getText().toString().trim().isEmpty()
                        || inputcode2.getText().toString().trim().isEmpty()
                        || inputcode3.getText().toString().trim().isEmpty()
                        ||inputcode4.getText().toString().trim().isEmpty()
                        ||inputcode5.getText().toString().trim().isEmpty()
                        ||inputcode6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(SDMOtpVerify.this,"Please Enter valid OTP",Toast.LENGTH_SHORT).show();
                    return;
                }
                String code=
                        inputcode1.getText().toString()+inputcode2.getText().toString()+
                                inputcode3.getText().toString()+
                                inputcode4.getText().toString()+
                                inputcode5.getText().toString()+inputcode6.getText().toString();

                if(VerificationId!=null)
                {
                    progressBar.setVisibility(View.VISIBLE);
                    verify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(
                            VerificationId,code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    verify.setVisibility(View.VISIBLE);

                                    if(task.isSuccessful())
                                    {
                                        Intent intent=new Intent(getApplicationContext(), SDMMain.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(SDMOtpVerify.this,"Verification Code Entered is Invalid",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        findViewById(R.id.resendotpsdm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+getIntent().getStringExtra("mobile"),60,
                        TimeUnit.SECONDS,
                        SDMOtpVerify.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                        {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(SDMOtpVerify.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newverificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                //   progressBar.setVisibility(View.GONE);
                                VerificationId=newverificationId;
                                Toast.makeText(SDMOtpVerify.this,"OTP SENT SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        setupOTPInputs();


    }

    public void setupOTPInputs()
    {
        inputcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty())
                {
                    inputcode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty())
                {
                    inputcode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty())
                {
                    inputcode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty())
                {
                    inputcode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty())
                {
                    inputcode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}