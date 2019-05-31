package com.example.projectofindecurso.Ayuda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.projectofindecurso.LandActivity;
import com.example.projectofindecurso.Login.LoginActivity;
import com.example.projectofindecurso.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityAyudaAeropuerto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_aeropuerto);
        getSupportActionBar().hide();


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
        findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAyudaAeropuerto.this, LoginActivity.class);
                startActivity(intent);
            }
        });}else {
            findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityAyudaAeropuerto.this, LandActivity.class);
                    startActivity(intent);
                }
            });

        }
        findViewById(R.id.button_before).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAyudaAeropuerto.this, ActivityAyudaRestaurante.class);
                startActivity(intent);
            }
        });
    }
}
