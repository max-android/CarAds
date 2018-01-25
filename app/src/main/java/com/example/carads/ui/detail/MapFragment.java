package com.example.carads.ui.detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carads.R;
import com.example.carads.storage.database.entity.Car;
import com.example.carads.ui.callbacks.SetFunc;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.ui.utilities.Picture;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Максим on 15.01.2018.
 */

public class MapFragment extends Fragment  {


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

        initMap();

    }


    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this::showCarOnMap);
    }


    private void initialData(){

        car = (Car)getArguments().getSerializable(Constants.KEY_FRAG);

    }


private void showCarOnMap(GoogleMap googleMap){

    new Picture().bitmapFromUrl2(car.getImage(), result ->{

        LatLng sydney = new LatLng(59.938806, 30.314278);
        googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title(car.getName())
                .icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(result,250,250,true)))
                .snippet(car.getPhone()));

    } );


}

private void show(){}


}
