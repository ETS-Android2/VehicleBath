package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserHomePge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_pge);

        Intent i = getIntent();

        Button btn_View_Loyalty;
        btn_View_Loyalty = findViewById(R.id.btn_view_loyalty);

        btn_View_Loyalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePge.this,ViewUserLoyalty.class);
                startActivity(intent);
            }
        });

        FloatingActionButton btn_View_UserP;
        btn_View_UserP = findViewById(R.id.btn_view_userProfile);

        btn_View_UserP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePge.this,UserProfile.class);
                startActivity(intent);
            }
        });

        //Appointments

        Button btn_viewAppointments = findViewById(R.id.btn_view_vAppointments);

        btn_viewAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomePge.this,SelectCarWash.class);
            }
        });


    }
}