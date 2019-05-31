package com.example.projectofindecurso.Ayuda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.projectofindecurso.R;

public class ActivityAyudaAeropuerto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_aeropuerto);
        getSupportActionBar().hide();

        findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAyudaAeropuerto.this, ActivityAyudaAparcamiento.class);
                startActivity(intent);
            }
        });
    }
}
