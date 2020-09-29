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

import com.example.vehiclebath.Model.Advertisement;
import com.example.vehiclebath.Model.Subscription;
import com.example.vehiclebath.ViewHolder.AdvertisementViewHolder;
import com.example.vehiclebath.ViewHolder.SubscriptionViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class adminSubscripDetail extends AppCompatActivity {

    private Intent intent = getIntent();
    private RecyclerView subRecyclerView;
    private DatabaseReference databaseReference;
    RecyclerView.LayoutManager layoutManager;
    private Button btnAddSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subscrip_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Subscription");
        subRecyclerView = findViewById(R.id.subRecycler);
        subRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        subRecyclerView.setLayoutManager(layoutManager);
        btnAddSub = findViewById(R.id.btnAdminAddSub1);
        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(adminSubscripDetail.this,addSubscrip.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Subscription> options = new FirebaseRecyclerOptions.Builder<Subscription>()
                .setQuery(databaseReference,Subscription.class).build();

        FirebaseRecyclerAdapter<Subscription, SubscriptionViewHolder> adapter = new FirebaseRecyclerAdapter   <Subscription, SubscriptionViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SubscriptionViewHolder subscriptionViewHolder, int i, @NonNull Subscription subscription) {
                subscriptionViewHolder.subName.setText(subscription.getName());
                subscriptionViewHolder.subPrice.setText(subscription.getPrice());
                subscriptionViewHolder.subDPerc.setText(subscription.getDiscountPercentage());
                subscriptionViewHolder.subValidity.setText(subscription.getValidity());
                subscriptionViewHolder.subAvailability.setText(subscription.getAvailability());
            }

            @NonNull
            @Override
            public SubscriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subscription,parent,false);
                SubscriptionViewHolder holder = new SubscriptionViewHolder(view);
                return holder;
            }
        };
        subRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}