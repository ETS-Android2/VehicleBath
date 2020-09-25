package com.example.vehiclebath;

import androidx.annotation.RequiresApi;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class PlaceAppointmentForm extends AppCompatActivity {

    //Date Picker
    private DatePickerDialog picker;
    private EditText dateText;
    private TextView dateView;

    //Time Picker
    private TimePickerDialog Tpicker;
    private EditText TimeText;
    //Spinner
    private Spinner spinner;
    private Button btn_addTypeDB;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_appointment_form);

        //Spinner
        spinner = findViewById(R.id.spin_type);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("CAR");
        arrayList.add("VAN");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Name = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + Name,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        //dateView=(TextView)findViewById(R.id.textView7);
        dateText=findViewById(R.id.editTextDate);
        dateText.setInputType(InputType.TYPE_NULL);
        dateText.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                final int day = cldr.get(Calendar.DAY_OF_MONTH);
                final int month = cldr.get(Calendar.MONTH);
                final int year = cldr.get(Calendar.YEAR);
                //date picker Dialog
                picker = new DatePickerDialog(PlaceAppointmentForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });


        //Time Picker
        TimeText = findViewById(R.id.editTextTime);
        TimeText.setInputType(InputType.TYPE_NULL);
        TimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar Timecldr = Calendar.getInstance();
                int hours = Timecldr.get(Calendar.HOUR_OF_DAY);
                int minutes = Timecldr.get(Calendar.MINUTE);

                Tpicker  = new TimePickerDialog(PlaceAppointmentForm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int hours, int minutes) {
                        TimeText.setText(hours + ":" + minutes );
                    }
                },hours,minutes,true);
                Tpicker.show();
            }
        });

        //DB Insertion
        btn_addTypeDB = findViewById(R.id.btnSearchCarwash);

        btn_addTypeDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAppointment();
            }

            private void createAppointment() {
                String vehicleType = spinner.getSelectedItem().toString();
                String date = dateText.getText().toString();
                String time = TimeText.getText().toString();

                if(TextUtils.isEmpty(vehicleType)){
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Please select Vehicle Type", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                }

                else if(TextUtils.isEmpty(date)){
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Please select Vehicle Type", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                }

                else if(TextUtils.isEmpty(time)){
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Please select Vehicle Type", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                }

                else{
                    loadingBar.setTitle("Create Appointment");
                    loadingBar.setMessage("Creating Appointment");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    validateAppointment(date, time, vehicleType);

                }

            }

            private void validateAppointment(final String date, final String time, final String vehicleType) {
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference();
                final String key = "A"+time+"_"+date;

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!(dataSnapshot.child("Appointments").child(key).exists())){
                            HashMap<String, Object> appdata = new HashMap<>();
                            appdata.put("Date",date);
                            appdata.put("Time",time);
                            appdata.put("VehicleType",vehicleType);

                            ref.child("Appointments").child(key).updateChildren(appdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(PlaceAppointmentForm.this, "Appointment Placed Successfully", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(PlaceAppointmentForm.this, AppointmentSummary.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(PlaceAppointmentForm.this, "Error", Toast.LENGTH_LONG).show();
                                    }
                                    loadingBar.dismiss();
                                }
                            });



                        }
                        else{
                            Toast.makeText(PlaceAppointmentForm.this, "We already have an Appointment on that time", Toast.LENGTH_LONG).show();
                            Toast.makeText(PlaceAppointmentForm.this, "Please select another time", Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}