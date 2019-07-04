package com.example.p08_ps;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;
    LatLng north = new LatLng(1.424450,103.829849);
    LatLng central = new LatLng(1.301600,103.839912);
    LatLng east = new LatLng(1.349860,103.934189);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng singapore = new LatLng(1.3521,103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,11));

                LatLng north = new LatLng(1.424450,103.829849);
                LatLng central = new LatLng(1.301600,103.839912);
                LatLng east = new LatLng(1.349860,103.934189);

                Marker northMarker = map.addMarker(new MarkerOptions().position(north).title("HQ - North").snippet("Block 333, Admiralty Ave 3, 765654 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
                Marker centralMarker = map.addMarker(new MarkerOptions().position(central).title("Central").snippet("Block 3A, Orchard Ave 3, 134542").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
                Marker eastMarker = map.addMarker(new MarkerOptions().position(east).title("East").snippet("Block 555, Tampines Ave 3, 287788").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(MainActivity.this,marker.getTitle(),Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(north,15));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(central,15));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(east,15));
            }
        });
    }
}
