package com.example.mapskw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mapAPI;
    SupportMapFragment mapFragment;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.location);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAPI);
        mapFragment.getMapAsync(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query){
                String location = searchView.getQuery().toString();
                List<Address> addressesList = null;

                if (location !=null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    try {
                        addressesList = geocoder.getFromLocationName(location, 1);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address = addressesList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    mapAPI.addMarker(new MarkerOptions().position(latLng).title(location));
                    mapAPI.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng ,10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { return false; }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapAPI = googleMap;

        LatLng spbuUMM = new LatLng(-7.9240453,112.6006854);
        mapAPI.addMarker(new MarkerOptions().position(spbuUMM).title("SPBU Universitas Muhammbadiyah Malang"));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(spbuUMM));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLngZoom(spbuUMM, 12));

        LatLng spbutlogomas = new LatLng(-7.9284659,112.6030458);
        mapAPI.addMarker(new MarkerOptions().position(spbutlogomas).title("SPBU Tlogomas"));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(spbutlogomas));

        LatLng pommini1 = new LatLng(-7.9267856,112.5999357);
        mapAPI.addMarker(new MarkerOptions().position(pommini1).title("pom mini"));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(pommini1));

        LatLng pertamini = new LatLng(-7.9333155,112.6074092);
        mapAPI.addMarker(new MarkerOptions().position(pertamini).title("pertamini dua putra cell"));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(pertamini));

        LatLng pommini2 = new LatLng(-7.9219476,112.5892933);
        mapAPI.addMarker(new MarkerOptions().position(pommini2).title("ppom mini DW"));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(pommini2));


    }
}
