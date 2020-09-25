package com.example.vehiclebathnew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class User_Profile extends AppCompatActivity {

    private Button dactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);

        Intent i = getIntent();

        Button btn_Verify_PwdU;
        btn_Verify_PwdU = findViewById(R.id.btn_verify_pwdU);

        btn_Verify_PwdU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Profile.this,Verify_User_Pwd.class);
                startActivity(intent);
            }
        });

        Button btn_Upd_User;
        btn_Upd_User = findViewById(R.id.btn_upd_user);

        btn_Upd_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Profile.this,Update_User.class);
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