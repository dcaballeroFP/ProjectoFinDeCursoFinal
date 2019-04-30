package com.example.projectofindecurso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PantallaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);



        findViewById(R.id.gpsPosicionImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PantallaPrincipal.this, GpsPosition.class);
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
