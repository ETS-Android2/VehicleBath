package com.example.vehiclebathnew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.paperdb.Paper;

public class User_Home_Pge extends AppCompatActivity {

    private Button logoutbtn1;

    //private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__home__pge);

        logoutbtn1 = (Button) findViewById(R.id.logout1);

        logoutbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Paper.book().destroy();

                Intent intent = new Intent(User_Home_Pge.this,Login_New.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();

        Button btn_View_Loyalty;
        btn_View_Loyalty = findViewById(R.id.btn_view_loyalty);

        btn_View_Loyalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Home_Pge.this,View_User_Loyalty.class);
                startActivity(intent);
            }
        });

        FloatingActionButton btn_View_UserP;
        btn_View_UserP = findViewById(R.id.btn_view_userProfile);

        btn_View_UserP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(User_Home_Pge.this,User_Profile.class);
                    startActivity(intent);
            }
        });


    }
}