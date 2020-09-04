package com.tokproject.setrip.activity;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tokproject.setrip.R;

import java.util.ArrayList;

public class MapsLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private FloatingActionButton fabMyLocation;

    private static final String TAG = MapsLocationActivity.class.getSimpleName();

    ArrayList<LatLng> arrayList = new ArrayList<>();
    LatLng pantai_krui = new LatLng(-5.191577, 103.930405);
    LatLng taman_nasional_waykambas  = new LatLng(-4.927572, 105.776933);
    LatLng air_terjun_waylalaan = new LatLng(-5.485158, 104.689216);
    LatLng kalianda_resort = new LatLng(-5.673996, 105.533563);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_location);

        fabMyLocation = findViewById(R.id.myLocation);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
        arrayList.add(pantai_krui);
        arrayList.add(taman_nasional_waykambas);
        arrayList.add(air_terjun_waylalaan);
        arrayList.add(kalianda_resort);

        fabMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLastLocation();
            }
        });

    }

    private void fetchLastLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    currentLocation = location;
                    Log.d(TAG, "Lokasi kamu ada di:" + currentLocation.getLatitude() + " " + currentLocation.getLongitude());
                    SupportMapFragment supportMapFragment = (SupportMapFragment)
                            getSupportFragmentManager().findFragmentById(R.id.google_maps);

                    supportMapFragment.getMapAsync(MapsLocationActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("You standing here")
                .snippet("Kamu sedang berada disini");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        googleMap.addMarker(markerOptions);

        for (int i = 0; i < arrayList.size(); i++) {
            String x = null;
            if (i == 0) {
                x = "Objek wisata pantai Krui,\nPesisir Barat, Lampung";
            } else if (i == 1) {
                x = "Taman nasional way kambas";
            } else if (i == 2) {
                x = "Air terjun way lalaan";
            } else if (i == 3) {
                x = "Kalianda Resort";
            }

            googleMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(x));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }
    }

}