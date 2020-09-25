package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class orgYourAdvertisement extends AppCompatActivity {

    FloatingActionButton addAdvertise;
    Button btnUpdate1,btnUpdate2,btnUpdate3,btnDelete1,btnDelete2,btnDelete3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_your_advertisement);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addAdvertise = findViewById(R.id.fltBtnAddAdvertise1);
        btnUpdate1=findViewById(R.id.btnUpdateAdvertise1_1);
        btnUpdate2=findViewById(R.id.btnUpdateAdvertise1_2);
        btnUpdate3=findViewById(R.id.btnUpdateAdvertise1_3);
        btnDelete1=findViewById(R.id.btnDeleteAdvertise1_1);
        btnDelete2=findViewById(R.id.btnDeleteAdvertise1_2);
        btnDelete3=findViewById(R.id.btnDeleteAdvertise1_3);

        addAdvertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(orgYourAdvertisement.this,orgAddAdvertisement.class);
                startActivity(intent);
            }
        });
    }

}