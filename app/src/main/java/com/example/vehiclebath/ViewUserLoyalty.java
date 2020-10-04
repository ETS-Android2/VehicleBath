package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewUserLoyalty extends AppCompatActivity {
    private TextView loyaltyValue;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_loyalty);

        Intent i = getIntent();
        mToolbar = (Toolbar) findViewById(R.id.register_user_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Your Loyalty Points - Vehicle Bath");




        loyaltyValue = (TextView) findViewById(R.id.loyalty_value);

        float total = (float) 1500.0;

        float LP = calLoyaltyPoints(total);
        String loyalty = Float.toString(LP);
        loyaltyValue.setText(loyalty);


    }

    public float calLoyaltyPoints (float total) {
        float LoyaltyP = (float) (total*0.01);

        return LoyaltyP;

    }

}