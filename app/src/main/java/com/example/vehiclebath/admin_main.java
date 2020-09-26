package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_main extends AppCompatActivity {

    Button orgbutton,cusbutton,userBlackbutton,addButton,subButton,repButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        orgbutton = findViewById(R.id.btnOrganization);
        cusbutton =  findViewById(R.id.btnCustomer);
        userBlackbutton = findViewById(R.id.btnUserBlack);
        addButton = findViewById(R.id.btnAdd);
        subButton = findViewById(R.id.btnSub);
        repButton = findViewById(R.id.btnReoprtCustomer);

        orgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(admin_main.this,Orgnization.class);
                startActivity(intent);
            }
        });

        cusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(admin_main.this,customers.class);
                startActivity(intent);
            }
        });

        userBlackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(admin_main.this,customer_blacklist.class);
                startActivity(intent);
            }
        });

        repButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_main.this,CustomerReportActivity.class);
                startActivity(intent);
            }
        });


    }
}