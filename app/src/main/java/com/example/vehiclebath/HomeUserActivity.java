package com.example.vehiclebath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclebath.Prevalent1.Prevalent;
import com.google.android.material.navigation.NavigationView;

import io.paperdb.Paper;

public class HomeUserActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView postList;
    private Toolbar mToolbar;
    private TextView navProfileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        Paper.init(this);





//        View headerView = navigationView.getHeaderView(0);
//        TextView userNameTextView = headerView.findViewById(R.id.nav_user_full_name);
//        userNameTextView.setText(Prevalent.currentOnlineUser.getName());

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeUserActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        TextView navProfileName = (TextView) navView.findViewById(R.id.nav_user_full_name);
        navProfileName.setText(Prevalent.currentOnlineUser.getName());



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                UserMenuSelector(menuItem);


                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void UserMenuSelector(MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.nav_profileSettings:
                sendUserToSettingsActivity();
                Toast.makeText(this,"View Your Profile Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_view:
                Toast.makeText(this,"View Your Bookings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_home:
                Toast.makeText(this,"Home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_loyalty:
                loyaltyActivity();
                Toast.makeText(this,"View Your Loyalty Points", Toast.LENGTH_SHORT).show();
                break;


            case R.id.nav_logout:
                logoutActivity();
                Toast.makeText(this,"Logout Successful", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void sendUserToSettingsActivity()
    {
        Paper.book().destroy();
        Intent settingsIntent = new Intent(HomeUserActivity.this, SettingsActivity1.class);
//        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(settingsIntent);
//        finish();
    }

    private void logoutActivity()
    {
        Paper.book().destroy();
        Intent settingsIntent = new Intent(HomeUserActivity.this, LoginNew.class);
        startActivity(settingsIntent);
    }

    private void loyaltyActivity()
    {
        Paper.book().destroy();
        Intent settingsIntent = new Intent(HomeUserActivity.this, ViewUserLoyalty.class);
        startActivity(settingsIntent);
    }


    }

