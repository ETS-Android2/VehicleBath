package com.example.vehiclebath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.vehiclebath.Model.Advertisement;
import com.example.vehiclebath.ViewHolder.AdvertisementViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class orgYourAdvertisement extends AppCompatActivity {

    private Intent intent = getIntent();
    private RecyclerView adRecyclerView;
    private DatabaseReference databaseReference;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_your_advertisement);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Advertisement");
        adRecyclerView = findViewById(R.id.adRecycler);
        adRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Advertisement> options = new FirebaseRecyclerOptions.Builder<Advertisement>()
                .setQuery(databaseReference,Advertisement.class).build();

        FirebaseRecyclerAdapter<Advertisement, AdvertisementViewHolder> adapter = new FirebaseRecyclerAdapter   <Advertisement, AdvertisementViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdvertisementViewHolder advertisementViewHolder, int i, @NonNull Advertisement advertisement) {
                advertisementViewHolder.adName.setText(advertisement.getName());
                advertisementViewHolder.adDesc.setText(advertisement.getDescription());
                Picasso.get().load(advertisement.getImageUrl()).into(advertisementViewHolder.imageViewAd);


            }

            @NonNull
            @Override
            public AdvertisementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_advertisement,parent,false);
                AdvertisementViewHolder holder = new AdvertisementViewHolder(view);
                return holder;
            }
        };
        adRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}