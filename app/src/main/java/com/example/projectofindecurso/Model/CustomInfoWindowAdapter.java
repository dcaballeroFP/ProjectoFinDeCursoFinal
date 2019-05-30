package com.example.projectofindecurso.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.projectofindecurso.GpsActivities.PointsMapsParkingActivity;
import com.example.projectofindecurso.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private GoogleMap mMap;
    PointsMapsParkingActivity pointsMapsParkingActivity;
    private static final String TAG = "CustomInfoWindowAdapter";
    private LayoutInflater inflater;
    private String calleInformacion;
    private String snippet;

    public CustomInfoWindowAdapter(LayoutInflater inflater, String calleInformacion, String snippet){
        this.inflater = inflater;
        this.calleInformacion= calleInformacion;
        this.snippet=snippet;

    }

    @Override
    public View getInfoContents(final Marker m) {
        //Carga layout personalizado.
        View v = inflater.inflate(R.layout.infowindow_layout, null);
        ((TextView)v.findViewById(R.id.info_window_placas)).setText(calleInformacion);
        ((TextView)v.findViewById(R.id.info_window_nombre)).setText(snippet);
        ((TextView)v.findViewById(R.id.info_window_estado)).setText("Estado: Activo");
        return v;
    }

    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }

}
