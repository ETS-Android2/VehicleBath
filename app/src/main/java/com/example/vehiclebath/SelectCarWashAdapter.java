package com.example.vehiclebath;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

public class SelectCarWashAdapter {

    public static class ProductListAdapter extends ArrayAdapter<Product> {

        private Context context;
        private List<Product> products;


        public ProductListAdapter(Context context, List<Product> products) {
            super(context, R.layout.list_layout, products);

            this.context = context;
            this.products = products;
        }

        @NonNull
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
                viewHolder.textViewName = view.findViewById(R.id.textViewName);
                viewHolder.textViewPrice = view.findViewById(R.id.textViewPrice);
                viewHolder.textViewDescription = view.findViewById(R.id.textViewDescription);
                viewHolder.imageViewPhoto = view.findViewById(R.id.imageViewPhoto);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Product product = products.get(position);
            viewHolder.textViewName.setText(product.getName());
            viewHolder.textViewDescription.setText(product.getDescription());
            viewHolder.textViewPrice.setText(context.getText(R.string.price) + String.valueOf(product.getPrice()));
            viewHolder.imageViewPhoto.setImageResource(product.getPhoto());
            return view;
        }

        private static class ViewHolder {
            public static TextView textViewName;
            public static TextView textViewDescription;
            public static TextView textViewPrice;
            public static ImageView imageViewPhoto;
        }

    }
}
