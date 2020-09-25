package com.example.vehiclebathnew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Org_Location_Insert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org__location__insert);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        Button btn_Con_LocO;
        btn_Con_LocO = findViewById(R.id.btn_con_locO);

        btn_Con_LocO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Org_Location_Insert.this,Confirm_Org_Registration.class);
                startActivity(intent);
            }
        });
    }
}