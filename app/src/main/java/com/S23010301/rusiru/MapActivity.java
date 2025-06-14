package com.S23010301.rusiru;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText addressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        addressInput = findViewById(R.id.addressInput);
        Button btnShowLocation = findViewById(R.id.showLocationButton);
        Button btnGoToSensor = findViewById(R.id.btnGoToSensor);

        // Initialize the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Error initializing map", Toast.LENGTH_SHORT).show();
        }

        // Handle search
        btnShowLocation.setOnClickListener(v -> {
            String address = addressInput.getText().toString().trim();

            if (mMap == null) {
                Toast.makeText(this, "Map not ready yet", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!TextUtils.isEmpty(address)) {
                locateAddress(address);
            } else {
                Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show();
            }
        });
        // Open SensorActivity
        btnGoToSensor.setOnClickListener(v -> {
            Intent intent = new Intent(MapActivity.this, SensorActivity.class);
            startActivity(intent);
        });

    }

    private void locateAddress(String address) {
        new Thread(() -> {
            Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocationName(address, 1);
                if (addresses != null && !addresses.isEmpty()) {
                    Address location = addresses.get(0);
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    runOnUiThread(() -> {
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(address));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        Toast.makeText(MapActivity.this,
                                "Lat: " + location.getLatitude() + ", Lng: " + location.getLongitude(),
                                Toast.LENGTH_SHORT).show();
                    });
                } else {
                    runOnUiThread(() ->
                            Toast.makeText(MapActivity.this, "Location not found", Toast.LENGTH_SHORT).show()
                    );
                }
            } catch (IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(MapActivity.this, "Geocoder error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        }).start();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Enable zoom controls and compass
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        // Optional: ask permission to enable my location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        // Set default location (e.g., Colombo)
        LatLng defaultLocation = new LatLng(6.9271, 79.8612); // Colombo
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10));
    }
}
