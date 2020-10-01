package com.example.vehiclebath.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclebath.Interface.AdClickListner;
import com.example.vehiclebath.R;

public class SubscriptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView subName,subPrice,subDPerc,subValidity,subAvailability;
    public AdClickListner subClickListner;

    public SubscriptionViewHolder(@NonNull View itemView) {
        super(itemView);

        subName = (TextView) itemView.findViewById(R.id.tvSubName);
        subPrice = (TextView) itemView.findViewById(R.id.tvSubPrice);
        subDPerc = (TextView) itemView.findViewById(R.id.tvSubDisPerc);
        subValidity = (TextView) itemView.findViewById(R.id.tvSubValidity);
        subAvailability = (TextView) itemView.findViewById(R.id.tvSubAvailability);
    }

    public void setAdClickListner(AdClickListner adClickListner){
        this.subClickListner=adClickListner;
    }

    @Override
    public void onClick(View view) {
        subClickListner.onclick(view, getAdapterPosition(), false);
    }
}
