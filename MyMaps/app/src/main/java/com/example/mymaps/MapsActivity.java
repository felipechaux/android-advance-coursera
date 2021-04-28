package com.example.mymaps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng placeLocation;
    private float bitmapDescriptorFactory;
    private String placeTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getExtraLocation();
    }

    private void getExtraLocation() {
        try {
            Bundle params = getIntent().getExtras();
            placeLocation= params.getParcelable(getString(R.string.extra_place_location));
            bitmapDescriptorFactory = params.getFloat(getString(R.string.extra_place_mark));
            placeTitle = params.getString(getString(R.string.extra_place_title));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(placeLocation).title(placeTitle).icon(BitmapDescriptorFactory.defaultMarker(bitmapDescriptorFactory)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(placeLocation));

    }
}