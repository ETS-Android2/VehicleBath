package com.example.vehiclebath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vehiclebath.CarWashTypeHolder.Admin_all_table_ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminViewAllAppointments extends AppCompatActivity {

    private RecyclerView table_recycler1;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_appointments);

        reference = FirebaseDatabase.getInstance().getReference().child("Appointments");
        table_recycler1 = findViewById(R.id.tablerowrecycler1);
        table_recycler1.setHasFixedSize(true);
        table_recycler1.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Appointments> options =
                new FirebaseRecyclerOptions.Builder<Appointments>().setQuery(reference, Appointments.class).build();

        FirebaseRecyclerAdapter<Appointments, Admin_all_table_ViewHolder> adapter =
                new FirebaseRecyclerAdapter<Appointments, Admin_all_table_ViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Admin_all_table_ViewHolder holder, int position, @NonNull Appointments model) {
                        holder.CName1.setText(model.getC_Name());
                        holder.type1.setText(model.getCarWashType());
                        String date =  model.getDate()+ " " + model.getTime();
                        holder.dateTime1.setText(date);

                    }

                    @NonNull
                    @Override
                    public Admin_all_table_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_all_appointment_table_layout,parent,false);
                        Admin_all_table_ViewHolder holder = new Admin_all_table_ViewHolder(view);
                        return holder;

                    }
                };
        table_recycler1.setAdapter(adapter);
        adapter.startListening();
    }
}