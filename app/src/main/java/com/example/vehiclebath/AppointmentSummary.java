package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class AppointmentSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_summary);

        FloatingActionButton btnHome = (FloatingActionButton) findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(),"Thank you for working with us",Toast.LENGTH_SHORT);
//                Snackbar.make(v, "Thank you for working with us", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                startSelectCarWash();
            }

            private void startSelectCarWash() {
                Intent intent = new Intent(AppointmentSummary.this, SelectCarWash.class);
                startActivity(intent);
            }
        });

    }
}