package com.example.vehiclebath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclebath.Model.Users;
import com.example.vehiclebath.UserHolder.UserViewHolder;
import com.example.vehiclebath.UserHolder.UserViewHolderReport;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CustomerReportActivity extends AppCompatActivity {

    //private Button CreateCustomerReport;
    private TextView reportName,reportPhone,reportPassword,reportEmail;
    private ProgressDialog loadingBar;


    Intent i = getIntent();
    private DatabaseReference UserRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_report);
        //setContentView(R.layout.customer_list_report_layout);

/*
        CreateCustomerReport = (Button)findViewById(R.id.btnReportSubmit);
        reportName = (TextView)findViewById(R.id.user_name_report);
        reportPhone = (TextView)findViewById(R.id.user_phone_report);
        reportPassword = (TextView)findViewById(R.id.user_password_report);
        reportEmail = (TextView)findViewById(R.id.user_email_report);


        CreateCustomerReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

*/

        //-----------------------------------------------------//

        UserRef = FirebaseDatabase.getInstance().getReference().child("UsersReport");

        recyclerView = findViewById(R.id.recyle_report);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




    }

    //----------------------------------------------------------------------------------------//

  /*  private void CreateReport()
    {
        String userName =reportName.getText().toString();
        String userPhone =reportPhone.getText().toString();
        String userPassword =reportPassword.getText().toString();
        String userEmail =reportEmail.getText().toString();

        loadingBar.setTitle("Report Submission Progressing");
        loadingBar.setMessage("Please wait we are checking for you credentials");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        validate(userName,userPhone,userPassword,userEmail);

    }

    private void validate(final String userName,final  String userPhone,final  String userPassword,final  String userEmail) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
                                        Toast.makeText(CustomerReportActivity.this, "Report has been submitted successfully", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                       // Intent intent = new Intent(CustomerReportActivity.this,LoginActivity.class);
                                        // startActivity(intent);

                                    }
                                    else{
                                        loadingBar.dismiss();
                                        Toast.makeText(CustomerReportActivity.this, "Network Error : Please try again after some time", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                }
                else
                {
                    Toast.makeText(CustomerReportActivity.this, "This "+userPhone+" number already exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(CustomerReportActivity.this,"There is already a report been submitted on this number!",Toast.LENGTH_SHORT).show();


                    //Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    //startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

*/
    //----------------------------------------------------------------------------------------//

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(UserRef,Users.class)
                        .build();

        FirebaseRecyclerAdapter<Users, UserViewHolderReport> adapter =
                new FirebaseRecyclerAdapter<Users, UserViewHolderReport>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull UserViewHolderReport holder, int position, @NonNull Users model) {

                        holder.UserName.setText(model.getName());
                        holder.UserEmail.setText(model.getEmail());
                        holder.UserPassword.setText(model.getPassword());
                        holder.UserPhone.setText(model.getPhone());



                    }

                    @NonNull
                    @Override
                    public UserViewHolderReport onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_report_layout,parent,false);
                        UserViewHolderReport holder = new UserViewHolderReport(view);
                        return holder;

                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}