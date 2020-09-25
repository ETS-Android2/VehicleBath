package com.example.vehiclebathnew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Confirm_Org_Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm__org__registration);

        Intent i = getIntent();

        Button btn_Create_OA;
        btn_Create_OA = findViewById(R.id.btn_create_OA);

        btn_Create_OA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Confirm_Org_Registration.this,Login_New.class);
                startActivity(intent);
            }
        });

    }
}