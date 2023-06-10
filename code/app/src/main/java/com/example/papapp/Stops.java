package com.example.papapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Stops extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private ImageButton zoomInButton;
    private ImageButton zoomOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        // Find the zoom buttons by their IDs
        zoomInButton = findViewById(R.id.zoomInButton);
        zoomOutButton = findViewById(R.id.zoomOutButton);

        // Set click listeners for the zoom buttons
        zoomInButton.setOnClickListener(v -> zoomIn());
        zoomOutButton.setOnClickListener(v -> zoomOut());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapActivity);
        mapFragment.getMapAsync(this);
    }

    // Zoom in by increasing the camera zoom level
    private void zoomIn() {
        if (mMap != null) {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
    }

    // Zoom out by decreasing the camera zoom level
    private void zoomOut() {
        if (mMap != null) {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (isLocationPermissionGranted()) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestLocationPermission();
        }
        LatLng BusStop1 = new LatLng(38.286028960247656, 21.78573918256433);
        mMap.addMarker(new MarkerOptions().position(BusStop1).title("Coffee Island Bus stop"));

        LatLng BusStop2 = new LatLng(38.285593785215404, 21.786229410141456);
        mMap.addMarker(new MarkerOptions().position(BusStop2).title("Administration Building - Starting Bus stop"));

        LatLng BusStop3 = new LatLng(38.286214229887875, 21.786109327378323);
        mMap.addMarker(new MarkerOptions().position(BusStop3).title("Internal 612 Bus stop"));

        LatLng BusStop4 = new LatLng(38.28791110939085, 21.78664040471753);
        mMap.addMarker(new MarkerOptions().position(BusStop4).title("Polytechnic Bus stop"));

        LatLng BusStop5 = new LatLng(38.291685702025624, 21.787026482668598);
        mMap.addMarker(new MarkerOptions().position(BusStop5).title("Physics School Bus stop"));

        LatLng BusStop6 = new LatLng(38.29438500894547, 21.791916708812114);
        mMap.addMarker(new MarkerOptions().position(BusStop6).title("Medicine School Bus stop"));

        LatLng TrainStop1 = new LatLng(38.28593238733144, 21.786064539157575);
        mMap.addMarker(new MarkerOptions().position(TrainStop1).title("Initial Train stop"));

        LatLng TrainStop2 = new LatLng(38.28784669921117, 21.78666439408605);
        mMap.addMarker(new MarkerOptions().position(TrainStop2).title("Polytechnic Train stop"));


        // Move the camera to a specific location and set zoom level
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BusStop2, 15.0f)); // Adjust the zoom level here
    }

    private boolean isLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                // Permission denied, handle accordingly or disable location-related functionality
            }
        }
    }
}