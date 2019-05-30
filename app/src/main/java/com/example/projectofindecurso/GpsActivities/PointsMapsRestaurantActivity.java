package com.example.projectofindecurso.GpsActivities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.projectofindecurso.Model.CustomInfoWindowAdapterRT;
import com.example.projectofindecurso.Model.PointsMapsDates;
import com.example.projectofindecurso.Model.PointsMapsDatesNP;
import com.example.projectofindecurso.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PointsMapsRestaurantActivity extends FragmentActivity implements OnMapReadyCallback {
    private FusedLocationProviderClient mFusedLocationClient;

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    PointsMapsDates mp;
    private Marker marcador;
    double latitud = 0.0;
    double longitud = 0.0;
    private ArrayList<Marker> tmpRealTimeMarker = new ArrayList<>();
    private ArrayList<Marker> realTimeMarkers = new ArrayList<>();
    public EditText calle;
    public EditText nombrePunto;



    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        cargarMapas();
        miUbicacion();
        //Añadir marca con un longClick
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                                           @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                           @Override
                                           public void onMapLongClick(final LatLng latLng) {
//                mMap.addMarker(new MarkerOptions()
//                        .anchor(0.0f,1.1f)
//                        .position(latLng));
        AlertDialog.Builder alert = new AlertDialog.Builder(PointsMapsRestaurantActivity.this);
        view=getLayoutInflater().inflate(R.layout.dialog_callenp,null);
        calle = (EditText) view.findViewById(R.id.nombreCalle);
        nombrePunto = (EditText) view.findViewById(R.id.nombre);
        alert.setView(view);
        alert.setTitle("Añadir Direccion");
        alert.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
        final PointsMapsDatesNP pointsMapsDatesNP = new PointsMapsDatesNP();
        pointsMapsDatesNP.setLongitude(latLng.longitude);
        pointsMapsDatesNP.setLatitude(latLng.latitude);
        pointsMapsDatesNP.setCalle(calle.getText().toString());
        pointsMapsDatesNP.setNombre(nombrePunto.getText().toString());
        mDatabase.child("MarcadorRestaurante").push().setValue(pointsMapsDatesNP);
        }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) { dialog.cancel();
        }}).show();}
                                       }
        );




        //Clicar en una marca
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                MarkerOptions markerOptions = new MarkerOptions();
                PointsMapsDates pointsMapsDates = new PointsMapsDates();
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapterRT(LayoutInflater.from(getApplicationContext()),marker.getTitle(),marker.getSnippet()));
                return false;
            }
        });

    }

    private void agregarMarcador(double latitud, double longitud) {
        LatLng coordenadas = new LatLng(latitud, longitud);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) {
            marcador.remove();
        }
        marcador = mMap.addMarker(new MarkerOptions()
                        .position(coordenadas)
                        .title("Mi Posición Actual")
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenadas,10));
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            latitud = location.getLatitude();
            longitud = location.getLongitude();
            agregarMarcador(latitud, longitud);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };




    private void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void cargarMapas(){
        mDatabase.child("MarcadorRestaurante").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (Marker marker:realTimeMarkers){
                    marker.remove();
                }
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    PointsMapsDatesNP pointsMapsDatesNP=snapshot.getValue(PointsMapsDatesNP.class);
                    Double latitud= pointsMapsDatesNP.getLatitude();
                    Double longitud= pointsMapsDatesNP.getLongitude();
                    String calle= pointsMapsDatesNP.getCalle();
                    String nombre= pointsMapsDatesNP.getNombre();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(latitud,longitud));
                    markerOptions.title(calle);
                    markerOptions.snippet(nombre);

                    markerOptions.icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.restaurant_emoticono));
                    tmpRealTimeMarker.add(mMap.addMarker(markerOptions));
                }
                realTimeMarkers.clear();
                realTimeMarkers.addAll(tmpRealTimeMarker);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }



}
