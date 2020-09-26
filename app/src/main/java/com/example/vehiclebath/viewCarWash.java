package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class viewCarWash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car_wash);

        Button btnSearch = findViewById(R.id.btnPlaceAppointment);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelectCarWash();
            }

            private void startSelectCarWash(){
                Intent intent = new Intent(viewCarWash.this, PlaceAppointmentForm.class);
                startActivity(intent);
            }
        });
    }

}