package com.example.projectofindecurso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.projectofindecurso.GpsActivities.GpsAirport;
import com.example.projectofindecurso.GpsActivities.GpsParking;
import com.example.projectofindecurso.GpsActivities.GpsRestaurant;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.projectofindecurso.GpsActivities.MapsActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class PantallaPrincipal extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    private FusedLocationProviderClient mFusedLocationClient;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        subirLatLongFirebase();
    }
        private void subirLatLongFirebase () {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(PantallaPrincipal.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                return;
            }
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Log.e("Latitud: ", location.getLatitude() + "Longitud: " + location.getLongitude());

                                Map<String, Object> latlang = new HashMap<>();
                                latlang.put("latitud", location.getLatitude());
                                latlang.put("longitud", location.getLongitude());

                                mDatabase.child("usuarios").push().setValue(latlang);
                            }
                        }
                    });


            findViewById(R.id.gpsPosicionImage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PantallaPrincipal.this, MapsActivity.class);
                    startActivity(intent);
                }
            });

            findViewById(R.id.gpsRestauranteImage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PantallaPrincipal.this, GpsRestaurant.class);
                    startActivity(intent);
                }
            });

            findViewById(R.id.gpsParkingImage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PantallaPrincipal.this, GpsParking.class);
                    startActivity(intent);
                }
            });

            findViewById(R.id.gpsAeropuertoImage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PantallaPrincipal.this, GpsAirport.class);
                    startActivity(intent);
                }
            });
        }
    }

