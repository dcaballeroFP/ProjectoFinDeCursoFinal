package com.example.projectofindecurso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class RegistroUsuarioNuevo extends AppCompatActivity {

    private EditText nombreview;
    private EditText apellidoview;
    private EditText emailview;
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


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            nombreview.setText(firebaseUser.getDisplayName());
            apellidoview.setText(firebaseUser.getDisplayName());
            emailview.setText(firebaseUser.getEmail());
            Log.e("Error", nombreview.getText().toString());

            for (int i = 0; i <nombreview.length() ; i++) {
                char c = nombreview.getText().toString().charAt(i);
                if(c== ' '){
                    nombreview.setText(nombreview.getText().toString().substring(0,contador));
                    apellidoview.setText(nombreview.getText().toString().substring(contador));

                    break;
                }else {
                    contador++;
                }

            }

        }


    }
    public void loguearCheckbox(View v) {
        if (!seleccionDireccion.isChecked()) {
            String s = "ERROR: " + "campo obligatorio";
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }
    }


}
