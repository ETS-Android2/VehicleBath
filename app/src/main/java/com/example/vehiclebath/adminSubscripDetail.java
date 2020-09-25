package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminSubscripDetail extends AppCompatActivity {

    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subscrip_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn1 = findViewById(R.id.btnAdminAddSub1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminSubscripDetail.this,addSubscrip.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.btnAdminUpdateSub1);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminSubscripDetail.this,adminSelectUpdateSubscription.class);
                startActivity(intent);
            }
        });
    }
}