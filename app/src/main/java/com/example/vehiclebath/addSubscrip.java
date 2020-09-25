package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addSubscrip extends AppCompatActivity {

    EditText subName,subPrice,subValidity;
    Spinner spAvaillabity;
    Button btnAddSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscrip);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerSubAvail);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Availability, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        subName = findViewById(R.id.etSubName);
        subPrice = findViewById(R.id.etSubPrice);
        subValidity = findViewById(R.id.etSubValidity);
        btnAddSub = findViewById(R.id.btnAdminAddSub2);

        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(subName.getText().toString())){
                    Toast.makeText(addSubscrip.this,"Enter Subscription Name!!!",Toast.LENGTH_LONG);
                }
                else if(TextUtils.isEmpty(subPrice.getText().toString())){
                    Toast.makeText(addSubscrip.this,"Enter Subscription Price!!!",Toast.LENGTH_LONG);
                }
                else if(TextUtils.isEmpty(subValidity.getText().toString())) {
                    Toast.makeText(addSubscrip.this, "Enter Subscription Validity Period!!!", Toast.LENGTH_LONG);
                }

            }
        });
    }
}