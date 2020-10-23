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
    //obyek wisata
    LatLng pantai_krui = new LatLng(-5.191577, 103.930405);
    LatLng pantai_pasir_putih = new LatLng(-5.530225, 105.357553);
    LatLng pantai_tanjung_setia = new LatLng(-5.312019, 103.993404);
    LatLng pantai_dewi_mandapa = new LatLng(-5.572358, 105.243645);
    LatLng pantai_gigi_hiu = new LatLng(-5.753071, 105.058151);


    //tempat tempat menarik
    LatLng teluk_kiluan = new LatLng(-5.771721, 105.109548);
    LatLng gunung_anak_krakatau = new LatLng(-6.102638, 105.422757);
    LatLng danau_ranau = new LatLng(-4.867449, 104.016376);
    LatLng menara_siger = new LatLng(-5.865427, 105.749731);
    LatLng curup_gangsa = new LatLng(-4.641013, 104.411942);
    LatLng air_terjun_waylalaan = new LatLng(-5.485158, 104.689216);
    LatLng air_terjun_putrimalu = new LatLng(-4.859958, 104.386075);
    LatLng air_terjun_ciupang = new LatLng(-5.587101, 105.022210);

    //konservasi alam dan budaya
    LatLng taman_nasional_waykambas  = new LatLng(-4.927572, 105.776933);
    LatLng taman_bukit_barisan_selatan = new LatLng(-5.448461, 104.351547);
    LatLng taman_batu_granit = new LatLng(-5.381319, 105.447006);
    LatLng taman_purbakala_pugung_raharjo = new LatLng(-5.300480, 105.571666);
    LatLng lembah_hijau = new LatLng(-5.415908, 105.230692);

    //resort dan penginapan
    LatLng kalianda_resort = new LatLng(-5.673996, 105.533563);
    LatLng kahai_beach_resort = new LatLng(-5.836807, 105.667617);
    LatLng revive_resort = new LatLng(-5.109035, 103.879951);
    LatLng sumatera_surf_resort = new LatLng(-5.310740, 103.993150);
    LatLng grand_elty_krakatoa = new LatLng(-5.673657, 105.533262);
    LatLng bumi_kedaton_resort = new LatLng(-5.436768, 105.222919);


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
        arrayList.add(revive_resort);
        arrayList.add(pantai_gigi_hiu);
        arrayList.add(pantai_dewi_mandapa);
        arrayList.add(pantai_pasir_putih);
        arrayList.add(pantai_tanjung_setia);
        arrayList.add(teluk_kiluan);
        arrayList.add(gunung_anak_krakatau);
        arrayList.add(danau_ranau);
        arrayList.add(menara_siger);
        arrayList.add(curup_gangsa);
        arrayList.add(air_terjun_ciupang);
        arrayList.add(air_terjun_putrimalu);
        arrayList.add(taman_batu_granit);
        arrayList.add(taman_bukit_barisan_selatan);
        arrayList.add(taman_purbakala_pugung_raharjo);
        arrayList.add(lembah_hijau);
        arrayList.add(kahai_beach_resort);
        arrayList.add(bumi_kedaton_resort);
        arrayList.add(sumatera_surf_resort);
        arrayList.add(grand_elty_krakatoa);

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
            String snippet = null;
            if (i == 0) {
                x = "Objek wisata pantai Krui";
                snippet = "Jl. Kuala Stabas, Pesisir Bar., Lampung 34874";
            } else if (i == 1) {
                x = "Taman nasional way kambas";
                snippet = "Kawasan hutan lindung pantai & rawa dataran rendah dengan satwa liar yang meliputi badak, gajah, & harimau.\nKabupaten Lampung Timur, Lampung";
            } else if (i == 2) {
                x = "Air terjun way lalaan";
                snippet = "Kampung Baru, Kota Agung Timur, Kp. Baru, Kota Agung Tim., Kabupaten Tanggamus, Lampung 35384";
            } else if (i == 3) {
                x = "Revive Resort Lampung Krui King/Hotel";
                snippet = "Tembakak Way Sindi, Kec. Karya Penggawa, Kabupaten Lampung Barat, Lampung";
            }else if (i == 4) {
                x = "Pekon Susuk, Kelumbayan, Kabupaten Tanggamus, Lampung";
                snippet = "";
            } else if (i == 5) {
                x = "Pantai Dewi Mandapa Pesawaran";
                snippet = "Gebang, Padang Cermin, Kabupaten Pesawaran, Lampung 35451";
            } else if (i == 6) {
                x = "Pantai Pasir Putih Lampung";
                snippet = "Rangai Tri Tunggal, Kec. Katibung, Kabupaten Lampung Selatan, Lampung 35452";
            }else if (i == 7) {
                x = "Pantai Tanjung Setia";
                snippet = "Jl. Bengkunat - Krui, Tj. Setia, Kec. Pesisir Sel., Pesisir Bar., Lampung 34875";
            } else if (i == 8) {
                x = "Teluk Kiluan";
                snippet = "Jl. Tlk. Kiluan, Kiluan Negeri, Kelumbayan, Kabupaten Tanggamus, Lampung 35379";
            } else if (i == 9) {
                x = "Gunung anak krakatau";
                snippet = "Pulau Anak Krakatau, Jl. Z.A. Pagar alam No.1B Rajabasa, Pulau Anak Krakatau, Pulau, Kabupaten Lampung Selatan, Lampung";
            }else if (i == 10) {
                x = "Danau Ranau";
                snippet = "Dikarenakan wisata alam yang menyenangkan untuk semua kalangan pengunjung";
            } else if (i == 11) {
                x = "Menara Siger";
                snippet = "Jalan Lintas Sumatra, Bakauheni, Kec. Bakauheni, Kabupaten Lampung Selatan, Lampung 35592";
            } else if (i == 12) {
                x = "Curup Gangsa";
                snippet = "Simpang Tiga, Rebang Tangkas, Kabupaten Way Kanan, Lampung 34767";
            }else if (i == 13) {
                x = "Air terjun Ciupang";
                snippet = "Hutan, Kec. Hutan, Kabupaten Pesawaran, Lampung 35451";
            }else if (i == 14) {
                x = "Air terjun Putri Malu";
                snippet = "Hutan Register 24, Banjit, Hutan Register 24, Banjit, Kabupaten Way Kanan, Lampung 34766";
            } else if (i == 15) {
                x = "Taman Batu Granit";
                snippet = "Purwodadi Dalam, Tj. Sari, Kabupaten Lampung Selatan, Lampung 35361";
            } else if (i == 16) {
                x = "Taman Bukit Barisan Selatan";
                snippet = "Lampung";
            }else if (i == 17) {
                x = "Taman Purbakala Pugung Raharjo";
                snippet = "Pugung Raharjo, Sekampung Udik, Kabupaten Lampung Timur, Lampung 34384";
            } else if (i == 18) {
                x = "Taman wisata dan taman satwa lembah hijau";
                snippet = "Jl. Raden Imba Kusuma Ratu Jl. Sukajadi No.21, Sukadana Ham, Kec. Tj. Karang Bar., Kota Bandar Lampung, Lampung 35000";
            } else if (i == 19) {
                x = "Kahai Beach Resort";
                snippet = "Unnamed Road, Batu Balak, Rajabasa, South Lampung Regency, Lampung 35552";
            }else if (i == 20) {
                x = "Bumi Kedaton Resort";
                snippet = "JL. WA Rahman, No. 1-2-3, Batu Putu Teluk Betung Utara, Batu Putuk, Bandarlampung, Kota Bandar Lampung, Lampung 35239";
            } else if (i == 21) {
                x = "Sumatera Surf Resort";
                snippet = "Jl. Tanjung Setia, Kecamatan Pesisir Selatan, Tanjung Setia, South Pesisir, West Pesisir Regency, Lampung 34875";
            } else if (i == 22) {
                x = "Grand Elity Krakatoa";
                snippet = "Kawasan Krakatoa Nirwana Resort, Jl. Trans Sumatra, Desa No.Km. 45, Merak Belantung, Kec. Kalianda, Kabupaten Lampung Selatan, Lampung 35511";
            }

            googleMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(x).snippet(snippet));
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