package com.example.projectofindecurso.Ayuda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.projectofindecurso.R;

public class ActivityAyudaAparcamiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_aparcamiento);
        getSupportActionBar().hide();
        findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAyudaAparcamiento.this, ActivityAyudaLocalizacion.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button_before).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAyudaAparcamiento.this, ActivityAyudaRegistro.class);
                startActivity(intent);
            }
        });
    }
}
