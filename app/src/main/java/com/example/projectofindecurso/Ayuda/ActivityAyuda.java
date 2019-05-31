package com.example.projectofindecurso.Ayuda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import com.example.projectofindecurso.R;
import com.example.projectofindecurso.Registry.RegistroVehiculo;

public class ActivityAyuda extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        getSupportActionBar().hide();

        findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAyuda.this, ActivityAyudaRegistro.class);
                startActivity(intent);
            }
        });

    }



}
