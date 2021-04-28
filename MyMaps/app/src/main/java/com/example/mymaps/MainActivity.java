package com.example.mymaps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnPlaceOne;
    private ImageButton btnPlaceTwo;
    private ImageButton btnPlaceThree;
    private ImageButton btnPlaceFour;

    private LatLng placeLocation =null;
    private float bitmapMark;
    private String placeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlaceOne = findViewById(R.id.btnPlaceOne);
        btnPlaceTwo = findViewById(R.id.btnPlaceTwo);
        btnPlaceThree = findViewById(R.id.btnPlaceThree);
        btnPlaceFour = findViewById(R.id.btnPlaceFour);

        btnPlaceOne.setOnClickListener(view -> placeOne());
        btnPlaceTwo.setOnClickListener(view -> placeTwo());
        btnPlaceThree.setOnClickListener(view -> placeThree());
        btnPlaceFour.setOnClickListener(view -> placeFour());

    }

    public void placeOne(){
        placeLocation= new LatLng(4.587707530725164, -74.20799320992955);
        bitmapMark= BitmapDescriptorFactory.HUE_AZURE;
        placeTitle =getString(R.string.place_one);
        goToMap();
    }

    public void placeTwo(){
        placeLocation= new LatLng(4.582726806314654, -74.20043174315653);
        bitmapMark= BitmapDescriptorFactory.HUE_ROSE;
        placeTitle =getString(R.string.place_two);
        goToMap();
    }
    public void placeThree(){
        placeLocation= new LatLng(4.582483265204653, -74.19682221210678);
        bitmapMark= BitmapDescriptorFactory.HUE_BLUE;
        placeTitle =getString(R.string.place_three);
        goToMap();
    }
    public void placeFour(){
        placeLocation= new LatLng(4.643940446266336, -74.25957074097825);
        bitmapMark= BitmapDescriptorFactory.HUE_ORANGE;
        placeTitle =getString(R.string.place_four);
        goToMap();
    }

    public void goToMap(){
        Intent intentMaps = new Intent(this,MapsActivity.class);
        intentMaps.putExtra(getString(R.string.extra_place_location),placeLocation);
        intentMaps.putExtra(getString(R.string.extra_place_mark),bitmapMark);
        intentMaps.putExtra(getString(R.string.extra_place_title),placeTitle);
        startActivity(intentMaps);
    }
}