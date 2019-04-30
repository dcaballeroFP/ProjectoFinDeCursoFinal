package com.example.projectofindecurso.Loging;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.projectofindecurso.PantallaPrincipal;
import com.example.projectofindecurso.R;
import com.example.projectofindecurso.Registry.UsuarioNuevo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.createAcount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsuarioNuevo.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.loginAcount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PantallaPrincipal.class);
                startActivity(intent);
            }
        });
    }
}
