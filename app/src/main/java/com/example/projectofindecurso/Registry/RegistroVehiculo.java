package com.example.projectofindecurso.Registry;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectofindecurso.GpsActivities.PointsMapsParkingActivity;
import com.example.projectofindecurso.Model.Vehiculo;
import com.example.projectofindecurso.PantallaPrincipal;
import com.example.projectofindecurso.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RegistroVehiculo extends AppCompatActivity {

    private Menu menu;
    EditText callePlaza, CPplaza, precioPlaza, telfC;
    ListView listV_vehiculo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Vehiculo plazaSeleccionada;

    private List<Vehiculo> listVehiculo = new ArrayList<Vehiculo>();
    ArrayAdapter<Vehiculo> arrayAdapterVehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);

        callePlaza =  findViewById(R.id.ModeloVehiculo);
        CPplaza = findViewById(R.id.MarcaVehiculo);
        telfC = findViewById(R.id.TelfContacto);
        precioPlaza = findViewById(R.id.KilometrajeVehiculo);


        listV_vehiculo = findViewById(R.id.lv_datos_vehiculo);

        iniciarFirebase();

        listarDatos();
        listV_vehiculo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                plazaSeleccionada = (Vehiculo) parent.getItemAtPosition(position);
                callePlaza.setText(plazaSeleccionada.getCalle());
                CPplaza.setText(plazaSeleccionada.getCP());
                precioPlaza.setText(plazaSeleccionada.getPrecio());
                telfC.setText(plazaSeleccionada.getTelfContacto());
            }
        });


        findViewById(R.id.añadirUbicacion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroVehiculo.this, PointsMapsParkingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void listarDatos() {
        databaseReference.child("PlazaParking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listVehiculo.clear();
                for (DataSnapshot objSnapchot : dataSnapshot.getChildren()){
                    Vehiculo vehiculo = objSnapchot.getValue(Vehiculo.class);
                    listVehiculo.add(vehiculo);
                    arrayAdapterVehiculo = new ArrayAdapter<Vehiculo>(RegistroVehiculo.this, android.R.layout.simple_list_item_1, listVehiculo);
                    listV_vehiculo.setAdapter(arrayAdapterVehiculo);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vehiculo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String calle = callePlaza.getText().toString();
        String cp = CPplaza.getText().toString();
        String telf = telfC.getText().toString();
        String precio = precioPlaza.getText().toString();

        switch (item.getItemId()) {
            case R.id.icon_add:{
                if (calle.equals("") || cp.equals("")  || precio.equals("")) {
                    validacion();

                } else {
                    Vehiculo vehiculo = new Vehiculo();
                    vehiculo.setUid(UUID.randomUUID().toString());
                    vehiculo.setCalle(calle);
                    vehiculo.setCP(cp);
                    vehiculo.setTelfContacto(telf);
                    vehiculo.setPrecio(precio);

                    databaseReference.child("PlazaParking").child(vehiculo.getUid()).setValue(vehiculo);

                }

                Toast.makeText(this, "Plaza Disponible", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_borrar:{
                Vehiculo v = new Vehiculo();
                v.setUid(plazaSeleccionada.getUid());
                databaseReference.child("PlazaParking").child(v.getUid()).removeValue();


                Toast.makeText(this, "Plaza Eliminada", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;


            }case R.id.ic_guardar:{
                Vehiculo v = new Vehiculo();
                v.setUid(plazaSeleccionada.getUid());
                v.setCalle(callePlaza.getText().toString().trim());
                v.setCP(CPplaza.getText().toString().trim());
                v.setTelfContacto(telfC.getText().toString().trim());
                v.setPrecio(precioPlaza.getText().toString().trim());

                databaseReference.child("PlazaParking").child(v.getUid()).setValue(v);
                Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            default:break;
        }


        return true;
    }

    private void validacion() {
        String modelo = callePlaza.getText().toString();
        String marca = CPplaza.getText().toString();

        String kilometraje = precioPlaza.getText().toString();

        if (modelo.equals("")){
            callePlaza.setError("Required");
        } else if (marca.equals("")){
            CPplaza.setError("Required");

        } else if (kilometraje.equals("")){
            precioPlaza.setError("Required");
        }

    }

    private void limpiarCajas() {
        callePlaza.setText("");
        precioPlaza.setText("");
        telfC.setText("");
        CPplaza.setText("");
    }
}
