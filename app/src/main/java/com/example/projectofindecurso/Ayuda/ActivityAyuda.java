package com.example.projectofindecurso.Ayuda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import com.example.projectofindecurso.LandActivity;
import com.example.projectofindecurso.Login.LoginActivity;
import com.example.projectofindecurso.R;
import com.example.projectofindecurso.Registry.RegistroVehiculo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            findViewById(R.id.button_before).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityAyuda.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            findViewById(R.id.button_before).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityAyuda.this, LandActivity.class);
                    startActivity(intent);
                }
            });


        }




    }



}
