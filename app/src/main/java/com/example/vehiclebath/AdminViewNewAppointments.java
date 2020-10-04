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

import com.example.vehiclebath.CarWashTypeHolder.AdminAppointmentTableViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminViewNewAppointments extends AppCompatActivity {

    private RecyclerView table_recycler;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_new_appointments);

        reference = FirebaseDatabase.getInstance().getReference().child("Appointments");
        table_recycler = findViewById(R.id.tablerowrecycler);
        table_recycler.setHasFixedSize(true);
        table_recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Appointments> options =
                new FirebaseRecyclerOptions.Builder<Appointments>().setQuery(reference, Appointments.class).build();

        FirebaseRecyclerAdapter<Appointments, AdminAppointmentTableViewHolder> adapter =
                new FirebaseRecyclerAdapter<Appointments, AdminAppointmentTableViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminAppointmentTableViewHolder holder, int position, @NonNull Appointments model) {
                        holder.CName.setText(model.getC_Name());
                        holder.type.setText(model.getCarWashType());
                        String date =  model.getDate()+ "  " + model.getTime();
                        holder.dateTime.setText(date);

                        String dateI =  model.getDate().replace("/","");
                        String time = model.getTime();

                        final String Aid = "A"+dateI+"_"+time;

                        holder.btnF.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(AdminViewNewAppointments.this,admin_main.class);
                                intent.putExtra("Aid", Aid);
                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public AdminAppointmentTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_table_layout,parent,false);
                        AdminAppointmentTableViewHolder holder = new AdminAppointmentTableViewHolder(view);
                        return holder;

                    }
                };
        table_recycler.setAdapter(adapter);
        adapter.startListening();
    }
}