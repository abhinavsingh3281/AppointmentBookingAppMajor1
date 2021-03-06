package com.example.appointmentapplication.SDMPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appointmentapplication.AppointmentBookSuccessful;
import com.example.appointmentapplication.DM.DM;
import com.example.appointmentapplication.MainActivity;
import com.example.appointmentapplication.R;
import com.example.appointmentapplication.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static android.graphics.Typeface.BOLD;

public class SDM1Main extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {
    private DatabaseReference mDatabase;
    Button b1;
    EditText Username, mobilenumber, reasontomeet, email, visitors;
    String s;
    Spinner sp1;
    String name, mobileno, date, time, stremail, strreview, strvisitor;
    TextView text_datepicker;

    int Year, Month, Day, Hour, Minute;
    DatePickerDialog datePickerDialog ;
    Calendar calendar ;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_d_m1_main);


        email = findViewById(R.id.firebaseemailsdm1);
        reasontomeet = findViewById(R.id.firebaseReasonsdm1);
        visitors = findViewById(R.id.visitorsdm1);
        b1 = (Button) findViewById(R.id.btnProceedsdm1);
        text_datepicker = (TextView)findViewById(R.id.text_datepicker);
        sp1 = (Spinner) findViewById(R.id.spinnertimesdm1);
        Username = findViewById(R.id.firebasenamesdm1);
        mobilenumber = findViewById(R.id.firebasephonenumbersdm1);

        progressBar=findViewById(R.id.progressbarsdm1);

//      Invoice=findViewById(R.id.btnInvoice);

//        datehash.put(calendar.get(Calendar.DAY_OF_MONTH),"current");
//        datehash.put(calendar.get(Calendar.SATURDAY),"present");

        mDatabase = FirebaseDatabase.getInstance().getReference("users").child("SDM").child("SDM1");

        calendar = Calendar.getInstance();

        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        Hour = calendar.get(Calendar.HOUR_OF_DAY);
        Minute = calendar.get(Calendar.MINUTE);

        //getWeekendDays();

        final Button button_datepicker = (Button) findViewById(R.id.button_datepicker);
        button_datepicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(SDM1Main.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setTitle("Date Picker");


                Calendar min_date_c = Calendar.getInstance();
                min_date_c.set(Calendar.DATE,Day+1);
                datePickerDialog.setMinDate(min_date_c);



                // Setting Max Date to next 2 years
                // Setting Max Date to next 2 years
                Calendar max_date_c = Calendar.getInstance();
                max_date_c.set(Calendar.DATE, Day+5);
                datePickerDialog.setMaxDate(max_date_c);



                //Disable all SUNDAYS and SATURDAYS between Min and Max Dates
                for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
                    int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
                    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                        Calendar[] disabledDays =  new Calendar[1];
                        disabledDays[0] = loopdate;
                        datePickerDialog.setDisabledDays(disabledDays);
                    }
                }



                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialogInterface) {

                        Toast.makeText(SDM1Main.this, "Datepicker Canceled", Toast.LENGTH_SHORT).show();
                    }
                });



                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });

        List<User> userList = new ArrayList<>();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b1.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                date=text_datepicker.getText().toString();
                strvisitor = visitors.getText().toString();
                String s1 = sp1.getSelectedItem().toString();
                time = s1;
                name = Username.getText().toString();
                mobileno = mobilenumber.getText().toString();
                stremail = email.getText().toString();
                strreview = reasontomeet.getText().toString();

                if (name.isEmpty() || mobileno.isEmpty() || date.isEmpty() || time.isEmpty() || stremail.isEmpty() || strvisitor.isEmpty() || strreview.isEmpty())
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.VISIBLE);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SDM1Main.this);
                    builder1.setMessage("Note : All Entries are compulsory , Please enter all the details.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else {
                    if (stremail.contains("@") && stremail.contains(".com")) {

                    Query mquery = FirebaseDatabase.getInstance().getReference("users").child("SDM").child("SDM1").orderByChild("date").equalTo(date);

                    mquery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.getChildrenCount() > 0) {
                                Query mquery1 = FirebaseDatabase.getInstance().getReference("users").child("SDM").child("SDM1").orderByChild("time").equalTo(time);

                                mquery1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                        if (snapshot1.getChildrenCount() > 0) {
                                            Toast.makeText(SDM1Main.this, "Appointment Already Booked", Toast.LENGTH_SHORT).show();

                                            b1.setVisibility(View.VISIBLE);
                                            progressBar.setVisibility(View.INVISIBLE);
                                        } else {

                                            writeNewUser(name, mobileno, date, time, stremail, strreview, strvisitor);
                                            startActivity(new Intent(SDM1Main.this, AppointmentBookSuccessful.class));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            } else {
                                writeNewUser(name, mobileno, date, time, stremail, strreview, strvisitor);
                                startActivity(new Intent(SDM1Main.this, AppointmentBookSuccessful.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                    else {

                    Toast.makeText(SDM1Main.this, "Enter Valid E-Mail Id", Toast.LENGTH_SHORT).show();
                    b1.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }

                }
            }

        });


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void writeNewUser(String name, String mobileno, String date, String time, String email, String review, String visitor) {


        User user = new User(name, mobileno, date, time, email, review, visitor);
        mDatabase.push().setValue(user);
        Toast.makeText(getApplicationContext(), "Data Sent Successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDateSet(DatePickerDialog view, int Year, int Month, int Day) {

        String date = Day+"/"+(Month+1)+"/"+Year;

        Toast.makeText(SDM1Main.this, date, Toast.LENGTH_LONG).show();


        text_datepicker.setText(date);

    }

}