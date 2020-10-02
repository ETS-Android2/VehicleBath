package com.example.vehiclebath;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclebath.Prevalent1.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity1 extends AppCompatActivity {

    private CircleImageView profileImageView;
    private EditText NameEdit,PhoneEdit,EmailEdit;
    private TextView profileChangeTextBtn, closeTextBtn, saveTextButton;
    private Button resetPasswordP, deleteAccountA;


    private Uri imageUri;
    private String myUri = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePictureRef;
    private String checker = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings1);

        //storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("Profile Picture");

        profileImageView = (CircleImageView) findViewById(R.id.settings_profile_img1);
        NameEdit = (EditText) findViewById(R.id.settings_name);
        PhoneEdit = (EditText) findViewById(R.id.settings_phone_num);
        EmailEdit = (EditText) findViewById(R.id.settings_email);
        profileChangeTextBtn = (TextView) findViewById(R.id.profile_img_change1);
        closeTextBtn = (TextView) findViewById(R.id.close_settings1);
        saveTextButton = (TextView) findViewById(R.id.update_settings1);
        //resetPasswordP = (Button) findViewById(R.id.reset_pwdU);
        deleteAccountA = (Button) findViewById(R.id.deleteU);

        userInfoDisplay1(NameEdit,PhoneEdit,EmailEdit);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checker.equals("clicked"))
                {
                    userInfoSaved();
                }
                else {
                    updateOnlyUserInfo();
                }
            }
        });

        profileChangeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker = "clicked";

                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(SettingsActivity1.this);

            }
        });

        deleteAccountA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence options[] = new CharSequence[]
                        {
                                "Yes",
                                "No"
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity1.this);
                builder.setTitle("Do you want to permanently delete this account ? ");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0)
                        {
                            DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());
                            UsersRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists())
                                    {
                                        if (snapshot.child("Phone").exists()) {
                                            String name = snapshot.child("Name").getValue().toString();
                                            String email = snapshot.child("Email").getValue().toString();
                                            String phone = snapshot.child("Phone").getValue().toString();

                                            DeleteUser(phone);

                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }
                        else
                        {
                            finish();
                        }
                    }
                });
                builder.show();

            }
        });

    }

    private void DeleteUser(String phone) {
        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());
        UsersRef.child(phone).removeValue();

        startActivity(new Intent(SettingsActivity1.this,LoginNew.class));
        Toast.makeText(SettingsActivity1.this,"Account Deleted successfully!!", Toast.LENGTH_LONG).show();
        finish();

    }


    private void userInfoDisplay1(EditText nameEdit, EditText phoneEdit, EditText emailEdit) {

        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());
        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    if (snapshot.child("Phone").exists())
                    {
//                        String image = snapshot.child("image").getValue().toString();
                        String name = snapshot.child("Name").getValue().toString();
                        String email = snapshot.child("Email").getValue().toString();
                        String phone = snapshot.child("Phone").getValue().toString();

//                        Picasso.get().load(image).into(profileImageView);
                        NameEdit.setText(name);
                        EmailEdit.setText(email);
                        PhoneEdit.setText(phone);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void updateOnlyUserInfo() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

        HashMap<String , Object> userMap = new HashMap<>();
        userMap.put("Name", NameEdit.getText().toString());
        userMap.put("Email", EmailEdit.getText().toString());
        userMap.put("Phone", PhoneEdit.getText().toString());
        ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);



        startActivity(new Intent(SettingsActivity1.this,MainActivity.class));
        Toast.makeText(SettingsActivity1.this,"Profile Info Updated Successfully", Toast.LENGTH_LONG).show();
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            profileImageView.setImageURI(imageUri);
        }
        else {
            Toast.makeText(this,"Error!!",Toast.LENGTH_SHORT).show();

            startActivity(new Intent(SettingsActivity1.this, SettingsActivity1.class));
            finish();
        }

    }

    private void userInfoSaved() {

        if (TextUtils.isEmpty(NameEdit.getText().toString()))
        {
            Toast.makeText(this,"Name is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(PhoneEdit.getText().toString()))
        {
            Toast.makeText(this,"Number is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(EmailEdit.getText().toString()))
        {
            Toast.makeText(this,"Email is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (checker.equals("clicked"))
        {
            uploadImage();
        }
    }

    private void uploadImage() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Upload Profile");
        progressDialog.setMessage("Please wait, while we are updating your info!! ");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if (imageUri != null)
        {
            final StorageReference fileRef = storageProfilePictureRef
                    .child(Prevalent.currentOnlineUser.getPhone() + ".jpg");

            uploadTask = fileRef.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {

                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }

                    return fileRef.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful())
                            {
                                Uri downloadUrl = task.getResult();
                                myUri = downloadUrl.toString();

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

                                HashMap<String , Object> userMap = new HashMap<>();
                                userMap.put("Name", NameEdit.getText().toString());
                                userMap.put("Email", EmailEdit.getText().toString());
                                userMap.put("Phone", PhoneEdit.getText().toString());
                                userMap.put("image", myUri);
                                ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);

                                progressDialog.dismiss();

                                startActivity(new Intent(SettingsActivity1.this,UserHomePge.class));
                                Toast.makeText(SettingsActivity1.this,"Profile Info Updated Successfully", Toast.LENGTH_LONG).show();
                                finish();

                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(SettingsActivity1.this,"Error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else {
            Toast.makeText(this,"Image is not selected.",Toast.LENGTH_SHORT).show();
        }


    }



//    private void userInfoDisplay(final CircleImageView profileImageView, EditText nameEdit, EditText phoneEdit, EditText emailEdit) {
//
//        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());
//        UsersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists())
//                {
//                    if (snapshot.child("image").exists())
//                    {
//                        String image = snapshot.child("image").getValue().toString();
//                        String name = snapshot.child("Name").getValue().toString();
//                        String email = snapshot.child("Email").getValue().toString();
//                        String phone = snapshot.child("Password").getValue().toString();
//
//                        Picasso.get().load(image).into(profileImageView);
//                        NameEdit.setText(name);
//                        EmailEdit.setText(email);
//                        PhoneEdit.setText(phone);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//    }


}

