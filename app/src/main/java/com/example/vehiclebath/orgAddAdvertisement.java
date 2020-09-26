package com.example.vehiclebath;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class orgAddAdvertisement extends AppCompatActivity {

    private EditText adName,adDesc,imgName;
    private ImageView imageView;
    private StorageReference storageReference;
    private static final int PICK_IMAGE_REQUEST = 99;
    private Uri imageUri;
    private DatabaseReference databaseReference;
    private StorageTask storageTask;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_add_advertisement);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storageReference = FirebaseStorage.getInstance().getReference("AdvertisementImage");
        databaseReference = FirebaseDatabase.getInstance().getReference();

        adName = findViewById(R.id.etAdName);
        adDesc = findViewById(R.id.etAdDesc);
        imgName = findViewById(R.id.etImgName);
        imageView = findViewById(R.id.viewImg);
        Button browseImg = findViewById(R.id.browseImg);
        Button addImage = findViewById(R.id.btnAddAdvertise2);

        browseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (storageTask != null && storageTask.isInProgress()) {
                    Toast.makeText(orgAddAdvertisement.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    addAdvertisement();
                }
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void addAdvertisement() {
        final String advertisementName = adName.getText().toString().trim();
        final String advertisementDescription = adDesc.getText().toString().trim();
        final String advertisementImageName = imgName.getText().toString().trim();

        if(TextUtils.isEmpty(advertisementName)){
            Toast.makeText(this,"Please Enter Advertisement Name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(advertisementDescription)){
            Toast.makeText(this,"Please Enter Advertisement Description",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(advertisementImageName)){
            Toast.makeText(this,"Please Enter Image Name",Toast.LENGTH_SHORT).show();
        }
        else if(imageUri == null){
            Toast.makeText(this,"Please Select Image",Toast.LENGTH_SHORT).show();
        }
        else {

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!(snapshot.child("Advertisement").child(advertisementName).exists())){
                        StorageReference fileReference = storageReference.child(advertisementName).child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
                        storageTask = fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(0);
                                    }
                                }, 500);
                                HashMap<String,Object> subDataMap = new HashMap<>();
                                subDataMap.put("Name",advertisementName);
                                subDataMap.put("Description",advertisementDescription);
                                subDataMap.put("ImageName",advertisementImageName);
                                subDataMap.put("ImageUrl",taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

                                databaseReference.child("Advertisement").child(advertisementName).updateChildren(subDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(orgAddAdvertisement.this,"New Advertisement is Added",Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(orgAddAdvertisement.this,adminAdvertiseDetails.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(orgAddAdvertisement.this,"Failed to Add New Advertisement",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(orgAddAdvertisement.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        });
                            }
                        });
                    }
                    else {
                        Toast.makeText(orgAddAdvertisement.this,"Added Advertisement exist",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}