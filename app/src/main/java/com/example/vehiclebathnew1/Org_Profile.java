package com.example.vehiclebathnew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Org_Profile extends AppCompatActivity {

    private Button dactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org__profile);

        Intent i = getIntent();

        Button btn_Upd_Org;
        btn_Upd_Org = findViewById(R.id.btn_upd_org);

        btn_Upd_Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Org_Profile.this,Update_Org.class);
                startActivity(intent);
            }
        });

        Button btn_Upd_Loc;
        btn_Upd_Loc = findViewById(R.id.btn_upd_loc);

        btn_Upd_Loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Org_Profile.this,org_location_update.class);
                startActivity(intent);
            }
        });




        dactButton = (Button) findViewById(R.id.dactButton);

        dactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }
    public void openDialog() {
        ExampleDialog dialog = new ExampleDialog();
        dialog.show(getSupportFragmentManager(), "example dialog");
    }
}