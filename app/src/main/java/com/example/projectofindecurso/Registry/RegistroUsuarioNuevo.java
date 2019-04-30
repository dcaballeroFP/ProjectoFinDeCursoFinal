package com.example.projectofindecurso.Registry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectofindecurso.R;
import com.example.projectofindecurso.PantallaPrincipal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroUsuarioNuevo extends AppCompatActivity {

    private EditText nombreview;
    private EditText apellidoview;
    private EditText emailview;
    private EditText passwordview;
    int contador;

    private CheckBox seleccionDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario_nuevo);
        seleccionDireccion = (CheckBox) findViewById(R.id.seleccion_direccion_envio);

        nombreview=findViewById(R.id.NombreRegistro);
        apellidoview=findViewById(R.id.ApellidoRegistro);
        emailview=findViewById(R.id.EmailRegistro);
        passwordview=findViewById(R.id.ContraseñaRegistro);



        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            nombreview.setText(firebaseUser.getDisplayName());
            apellidoview.setText(firebaseUser.getDisplayName());
            emailview.setText(firebaseUser.getEmail());
            Log.e("Error", nombreview.getText().toString());

            for (int i = 0; i <nombreview.length() ; i++) {
                char c = nombreview.getText().toString().charAt(i);
                if(c==' '){
                    nombreview.setText(nombreview.getText().toString().substring(0,contador));
                    apellidoview.setText(apellidoview.getText().toString().substring(nombreview.getText().length()+1));
                }
                else {
                        contador++;
                    }
                }

            }

        }

    public void registerComprobations(View v) {
        if (!seleccionDireccion.isChecked()) {
            String s = "ERROR: " + "Terminos y condiciones: campo obligatorio";
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }else if(nombreview.getText().toString().equals("")){
            String s = "ERROR: " + "Nombre: campo obligatorio";
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }else if(emailview.getText().toString().equals("")){
            String s = "ERROR: " + "Email: campo obligatorio";
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }else if(passwordview.getText().toString().equals("")){
            String s = "ERROR: " + "Contraseña: campo obligatorio";
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }else {
            findViewById(R.id.botonRegistrar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RegistroUsuarioNuevo.this, PantallaPrincipal.class);
                    startActivity(intent);
                }
            });
        }

        }
    }
