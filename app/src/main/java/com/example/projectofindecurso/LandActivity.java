package com.example.projectofindecurso;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectofindecurso.Ayuda.ActivityAyuda;
import com.example.projectofindecurso.GpsActivities.GpsPositionActivity;
import com.example.projectofindecurso.GpsActivities.PointsMapsAirportActivity;
import com.example.projectofindecurso.GpsActivities.PointsMapsParkingActivity;
import com.example.projectofindecurso.GpsActivities.PointsMapsRestaurantActivity;
import com.example.projectofindecurso.Login.LoginActivity;
import com.example.projectofindecurso.Registry.RegistroVehiculo;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class LandActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    private FusedLocationProviderClient mFusedLocationClient;
    DatabaseReference mDatabase;
    String pepe = "Hola";
    private ImageView imagenUser;
    private TextView textView;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View header= navigationView.getHeaderView(0);

        textView= (TextView) header.findViewById(R.id.correoUser);
        textView.setText(firebaseUser.getEmail());
        imagenUser = (ImageView) header.findViewById(R.id.imageViewUsuario);
        Glide.with(this)
                .load(firebaseUser.getPhotoUrl())
                .into(imagenUser);



        findViewById(R.id.gpsPosicionImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandActivity.this, GpsPositionActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.gpsRestauranteImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandActivity.this, PointsMapsRestaurantActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.gpsParkingImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandActivity.this, PointsMapsParkingActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.gpsAeropuertoImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandActivity.this, PointsMapsAirportActivity.class);
                startActivity(intent);
            }
        });

    }

    private void subirLatLongFirebase () {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(LandActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Log.e("Latitude: ", location.getLatitude() + "Longitude: " + location.getLongitude());

                            Map<String, Object> latlang = new HashMap<>();
                            latlang.put("latitude", location.getLatitude());
                            latlang.put("longitude", location.getLongitude());
                            latlang.put("calle", pepe );
                        }
                    }
                });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.e("abc", "kakak");
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        }  else if (id == R.id.nav_tools) {
            Intent intent = new Intent(LandActivity.this, ActivityAyuda.class);
            startActivity(intent);

        } else if (id ==R.id.sign_out){
            AuthUI.getInstance()
                    .signOut(LandActivity.this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            startActivity(new Intent(LandActivity.this, LoginActivity.class));
                            finish();
                        }
                    });
        } else if(id == R.id.nav_alquilar_plaza){
            Intent intent = new Intent(LandActivity.this, RegistroVehiculo.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
