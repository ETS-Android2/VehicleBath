package com.example.vehiclebath;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class addSubscripUser extends AppCompatActivity {

    private Spinner subPlan;
    private EditText phone;
    private Button btnAddUserSub;
    private DatabaseReference databaseReference;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscrip_user);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        arrayList = new ArrayList<>();
        subPlan = findViewById(R.id.spinnerSubPlan);
        phone = findViewById(R.id.etUserPhone);
        btnAddUserSub = findViewById(R.id.btnAdminAddUserSub);
        arrayAdapter = new ArrayAdapter<String>(addSubscripUser.this,android.R.layout.simple_spinner_dropdown_item,arrayList);

        subPlan.setAdapter(arrayAdapter);
        getSubPlans();

        btnAddUserSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserSubscription();
            }
        });

    }

    private void getSubPlans() {
        databaseReference.child("Subscription").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot item:snapshot.getChildren()){
                    arrayList.add(item.child("Name").getValue().toString());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addUserSubscription() {
        final String phoneNumber = phone.getText().toString().trim();
        String subscriptionPlan = subPlan.getSelectedItem().toString().trim();

        if(TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(this,"Please Enter Phone Number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(subscriptionPlan)){
            Toast.makeText(this,"Please Select Subscription Plan",Toast.LENGTH_SHORT).show();
        }
        else {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child("Users").child(phoneNumber).exists()){

                    }
                    else{
                        Toast.makeText(addSubscripUser.this,"Added Phone Number does not Exist",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}