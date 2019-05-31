package com.example.projectofindecurso.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.projectofindecurso.Ayuda.ActivityAyuda;
import com.example.projectofindecurso.LandActivity;
import com.example.projectofindecurso.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_nuevo);
        getSupportActionBar().hide();
        //getSupportActionBar().hide();
        findViewById(R.id.helpbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ActivityAyuda.class);
                startActivity(intent);            }
        });
        findViewById(R.id.loginAcount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

       // comeIn();
    }



    void comeIn(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            startActivity(new Intent(this, LandActivity.class));
            finish();
        }
    }

    void signIn(){

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.PhoneBuilder().build()))
                        .setTheme(R.style.GreenTheme)
                        .setLogo(R.drawable.icon)
                        .build(),
                RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                comeIn();

            }
        }
    }

    public void ejecutar_ajuda(View v){
        Intent i = new Intent(this, ActivityAyuda.class);
        startActivity(i);
    }

}
