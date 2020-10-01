package com.example.vehiclebath.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclebath.Interface.AdClickListner;
import com.example.vehiclebath.R;

public class AdvertisementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView adName,adDesc;
    public ImageView imageViewAd;
    public AdClickListner adClickListner;

    public AdvertisementViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewAd = (ImageView) itemView.findViewById(R.id.viewAdImg);
        adName = (TextView) itemView.findViewById(R.id.tvAdName);
        adDesc = (TextView) itemView.findViewById(R.id.tvAdDesc);
    }

    public void setAdClickListner(AdClickListner adClickListner){
        this.adClickListner=adClickListner;
    }

    @Override
    public void onClick(View view) {
        adClickListner.onclick(view, getAdapterPosition(), false);
    }
}
