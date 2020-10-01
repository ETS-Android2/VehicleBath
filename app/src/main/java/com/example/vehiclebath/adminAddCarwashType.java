package com.example.vehiclebath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class adminAddCarwashType extends AppCompatActivity {

    private Button btn_addTypeDB;
    private EditText edt_typeName, edt_TypeDesc, edt_TypePrice;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_carwash_type);

        edt_typeName =(EditText) findViewById(R.id.edt_typeName);
        edt_TypeDesc =(EditText) findViewById(R.id.edt_TypeDesc);
        edt_TypePrice =(EditText) findViewById(R.id.edt_TypePrice);
        btn_addTypeDB = (Button) findViewById(R.id.btn_addTypeDB);
        loadingBar = new ProgressDialog(this);

        btn_addTypeDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createType();
            }

            private void createType() {
                String TypeName = edt_typeName.getText().toString();
                String TypeDesc = edt_TypeDesc.getText().toString();
                String TypePrice = edt_TypePrice.getText().toString();

                if (TextUtils.isEmpty(TypeName)){
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Please Enter the Name", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if (TextUtils.isEmpty(TypeDesc)){
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Please Enter the Description", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if (TextUtils.isEmpty(TypePrice)){
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Please Enter the Price", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    loadingBar.setTitle("Add Type");
                    loadingBar.setMessage("We are Adding the Type");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    ValidateTypeName(TypeName, TypeDesc, TypePrice);
                }
            }

            private void ValidateTypeName(final String typeName, final String typeDes, final String typePrice ) {
                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();

                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!(dataSnapshot.child("CarWashType").child(typeName).exists())){
                            HashMap<String, Object> TypeData =new HashMap<>();

                            TypeData.put("typeName",typeName);
                            TypeData.put("typeDescription",typeDes);
                            TypeData.put("typePrice",typePrice);

                            RootRef.child("CarWashType").child(typeName).updateChildren(TypeData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(adminAddCarwashType.this, "Type added Successfully", Toast.LENGTH_LONG).show();

                                    }
                                    else{
                                        Toast.makeText(adminAddCarwashType.this, "Error", Toast.LENGTH_LONG).show();
                                    }
                                    loadingBar.dismiss();
                                }
                            });

                        }
                        else{
                            Toast.makeText(adminAddCarwashType.this, typeName+ " Type Already Exists", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}