package com.example.vehiclebathnew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Verify_User_Pwd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify__user__pwd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btn_Verify_AccU;
        btn_Verify_AccU= findViewById(R.id.btn_verify_accU);

        btn_Verify_AccU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Verify_User_Pwd.this,Change_User_Pwd.class);
                startActivity(intent);
            }
        });

    }
}