package com.example.projectofindecurso.Ayuda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.projectofindecurso.R;

public class ActivityAyuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }

    public void ejecutar_ayuda_aeropuerto(View v){
        Intent i = new Intent(this, ActivityAyudaAeropuerto.class);
        startActivity(i);
    }

    public void ejecutar_ayuda_aparcamiento(View v){
        Intent i = new Intent(this, ActivityAyudaAparcamiento.class);
        startActivity(i);
    }

    public void ejecutar_ayuda_localizacion(View v){
        Intent i = new Intent(this, ActivityAyudaLocalizacion.class);
        startActivity(i);
    }

    public void ejecutar_ayuda_registro(View v){
        Intent i = new Intent(this, ActivityAyudaRegistro.class);
        startActivity(i);
    }

    public void ejecutar_ayuda_restaurante(View v){
        Intent i = new Intent(this, ActivityAyudaRestaurante.class);
        startActivity(i);
    }


}
