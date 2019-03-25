package com.example.projectofindecurso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class RegistroUsuarioNuevo extends AppCompatActivity {

    private CheckBox seleccionDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario_nuevo);
        seleccionDireccion = (CheckBox) findViewById(R.id.seleccion_direccion_envio);
    }
    public void loguearCheckbox(View v) {
        if (!seleccionDireccion.isChecked()) {
            String s = "ERROR: " + "campo obligatorio";
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }
    }
}
