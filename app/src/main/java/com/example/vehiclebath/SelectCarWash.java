package com.example.vehiclebath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectCarWash extends AppCompatActivity {

    private ListView listViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car_wash);

        initView();
        loadData();
    }

    private void initView() {
        listViewProduct = findViewById(R.id.listViewProduct);
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listViewProduct_onItemClick(adapterView, view, i, l);
            }
        });
    }

    private void loadData() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("p01", "Name 1", 4, "Description for Product 1", R.drawable.logo1));
        products.add(new Product("p02", "Name 2", 8, "Description for Product 2", R.drawable.logo1));
        products.add(new Product("p03", "Name 3", 9, "Description for Product 3", R.drawable.logo1));
        products.add(new Product("p04", "Name 4", 11, "Description for Product 4", R.drawable.logo1));
        products.add(new Product("p05", "Name 5", 5, "Description for Product 5", R.drawable.logo1));
        products.add(new Product("p06", "Name 6", 21, "Description for Product 6", R.drawable.logo1));
        SelectCarWashAdapter.ProductListAdapter CardViewAdapter = new SelectCarWashAdapter.ProductListAdapter(getApplicationContext(), products);
        listViewProduct.setAdapter(CardViewAdapter);
    }

    private void listViewProduct_onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Product product = (Product) adapterView.getItemAtPosition(i);
        Toast.makeText(getApplicationContext(), product.getName(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(SelectCarWash.this,viewCarWash.class);
        startActivity(intent);
    }



}