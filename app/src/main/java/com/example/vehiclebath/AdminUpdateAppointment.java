package com.example.vehiclebath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminUpdateAppointment extends AppCompatActivity {

    private EditText eACusName, eADate, EAtime, EAtime2;
    private Button updateAppointment;
    private String newTime, key, key1;

    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_appointment);

        eACusName = findViewById(R.id.eACusName);
        eADate = findViewById(R.id.eADate);
        EAtime = findViewById(R.id.EAtime);
        EAtime2 = findViewById(R.id.EAtime2);

        final String date =getIntent().getStringExtra("date");
        final String time =getIntent().getStringExtra("time");

        eACusName.setText(getIntent().getStringExtra("cName"));
        eADate.setText(getIntent().getStringExtra("washType"));
        EAtime.setText(date);
        EAtime2.setText(time);

        key = "A"+date.replace("/","")+"_"+time ;
        key1 = getIntent().getStringExtra("Key1");
        ref = FirebaseDatabase.getInstance().getReference();

        updateAppointment = findViewById(R.id.updateAppointment);

        updateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateExistAppointment();
            }
        });




    }

    private void updateExistAppointment() {
        newTime = EAtime2.getText().toString();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ref.child("ClashAppointments").child(key).child("Time").setValue(newTime).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AdminUpdateAppointment.this, "Appointment Clash Resolved Successfully", Toast.LENGTH_LONG).show();
                            ref.child("ClashAppointments").child(key1).removeValue();
                        }
                        else{
                            Toast.makeText(AdminUpdateAppointment.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}