package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class adminAdvertiseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_advertise_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}