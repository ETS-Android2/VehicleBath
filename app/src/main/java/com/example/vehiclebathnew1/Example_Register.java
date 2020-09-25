package com.example.vehiclebathnew1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Example_Register extends AppCompatActivity {

    private Button createAccount;
    private EditText inputname, inputnumber , inputpassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example__register);

        createAccount = (Button) findViewById(R.id.user_ca);
        inputname = (EditText) findViewById(R.id.ex_name);
        inputnumber = (EditText) findViewById(R.id.ex_number);
        inputpassword = (EditText) findViewById(R.id.ex_pwd);
        loadingBar = new ProgressDialog(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });

    }

    private void CreateAccount() {
        String name = inputname.getText().toString();
        String number = inputnumber.getText().toString();
        String password = inputpassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this,"Please Enter Name",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(number)) {
            Toast.makeText(this,"Please Enter Number",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validatePhoneNumber(name,number,password);
        }
    }

    private void validatePhoneNumber(final String name, final String number, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(number).exists())) {
                    HashMap<String , Object> userDataMap = new HashMap<>();
                    userDataMap.put("Phone", number);
                    userDataMap.put("Password", password);
                    userDataMap.put("Name", name);

                    RootRef.child("Users").child(number).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Example_Register.this,"Your registration is successful",Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(Example_Register.this,Login_New.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        loadingBar.dismiss();
                                        Toast.makeText(Example_Register.this,"Network Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else {
                    Toast.makeText(Example_Register.this,"This "+number+ " exist",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}