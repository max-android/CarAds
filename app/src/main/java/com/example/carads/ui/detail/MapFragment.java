package com.example.carads.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carads.R;
import com.example.carads.storage.database.entity.Car;
import com.example.carads.ui.utilities.Constants;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Максим on 15.01.2018.
 */

public class MapFragment extends SupportMapFragment  {


   // private GoogleMap mMap;

    private Car car;

    public MapFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_map,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initialData();
      // this.getMapAsync(googleMap ->  showCarOnMap(googleMap));
       this.getMapAsync(this::showCarOnMap);

    }

//    @Override
//    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
//        super.getMapAsync(onMapReadyCallback);
//
//        onMapReadyCallback.onMapReady();
//
//
//
//    }


    private void initialData(){

        car = (Car)getArguments().getSerializable(Constants.KEY_FRAG);

    }


private void showCarOnMap(GoogleMap googleMap){

    LatLng sydney = new LatLng(-34, 151);
    googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

}




}
