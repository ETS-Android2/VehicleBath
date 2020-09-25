package com.example.vehiclebathnew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class View_User_Loyalty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__user__loyalty);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();


    }
}