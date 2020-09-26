package com.example.vehiclebath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclebath.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class OrgnizationReport_or_Activity extends AppCompatActivity {

    private TextView name_OR,phone_OR,password_OR,email_OR;
    private Button organization_OR;
    private String userPhone ="";
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgnization_report_or_);

        userPhone = getIntent().getStringExtra("UserPhone");

        name_OR = (TextView)findViewById(R.id.user_name_OR);
        phone_OR = (TextView)findViewById(R.id.user_phone_OR);
        password_OR = (TextView)findViewById(R.id.user_password_OR);
        email_OR = (TextView)findViewById(R.id.user_email_OR);

        organization_OR = (Button)findViewById(R.id.btnReportSubmit_OR);

        loadingBar = new ProgressDialog(this);

        getUserDetailsToDisplay(userPhone);



        //---------------------------------------------------------------------//
        organization_OR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                CreateReport();
            }
        });
        //---------------------------------------------------------------------//
    }

    private void openDialog() {
        Dialog2 dialog2 = new Dialog2();
        dialog2.show(getSupportFragmentManager(),"Example Dialogue");
    }

    //---------------------------------------------------------------------//
    private void CreateReport()
    {
        String userName =name_OR.getText().toString();
        String userPhone =phone_OR.getText().toString();
        String userPassword =password_OR.getText().toString();
        String userEmail =email_OR.getText().toString();


        loadingBar.setTitle("Report Submission Progressing");
        loadingBar.setMessage("Please wait we are checking for you credentials");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        validate(userName,userPhone,userPassword,userEmail);
    }

    private void validate(final String userName,final  String userPhone,final  String userPassword,final  String userEmail)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("UsersReport").child(userPhone).exists()))
                {
                    HashMap<String,Object> userDataMap = new HashMap<>();

                    userDataMap.put("Phone",userPhone);
                    userDataMap.put("Password",userName);
                    userDataMap.put("Name",userPassword);
                    userDataMap.put("Email",userEmail);

                    RootRef.child("UsersReport").child(userPhone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(OrgnizationReport_or_Activity.this, "Report has been submitted successfully", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        // Intent intent = new Intent(CustomerReportActivity.this,LoginActivity.class);
                                        // startActivity(intent);

                                    }
                                    else{
                                        loadingBar.dismiss();
                                        Toast.makeText(OrgnizationReport_or_Activity.this, "Network Error : Please try again after some time", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                }
                else
                {
                    Toast.makeText(OrgnizationReport_or_Activity.this, "This "+userPhone+" number already exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(OrgnizationReport_or_Activity.this,"Please try again using another phone number",Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(OrgnizationReport_or_Activity.this,customers.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //---------------------------------------------------------------------//
    private void getUserDetailsToDisplay(String userPhone)
    {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        userRef.child(userPhone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Users users = dataSnapshot.getValue(Users.class);
                    name_OR.setText(users.getName());
                    phone_OR.setText(users.getPhone());
                    password_OR.setText(users.getPassword());
                    email_OR.setText(users.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}