package com.example.vehiclebath;

import androidx.annotation.RequiresApi;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
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

import java.util.ArrayList;
import java.util.Calendar;

public class PlaceAppointmentForm extends AppCompatActivity {

    //Date Picker
    DatePickerDialog picker;
    EditText dateText;
    TextView dateView;

    //Time Picker
    TimePickerDialog Tpicker;
    EditText TimeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_appointment_form);

        //Spinner
        Spinner spinner = findViewById(R.id.spin_type);
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

        Button btnSearch = findViewById(R.id.btnSearchCarwash);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelectCarWash();
            }

            private void startSelectCarWash(){
                Intent intent = new Intent(PlaceAppointmentForm.this, AppointmentSummary.class);
                startActivity(intent);
            }
        });
    }
}