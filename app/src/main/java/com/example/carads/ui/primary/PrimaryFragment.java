package com.example.carads.ui.primary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.carads.R;
import com.example.carads.storage.database.entity.Car;
import com.example.carads.ui.registration.LoginRegisterActivity;
import com.example.carads.ui.search.SearchableActivity;
import com.example.carads.ui.utilities.Constants;

import java.util.ArrayList;


/**
 * Created by Максим on 23.11.2017.
 */

public class PrimaryFragment extends Fragment {


    private   ArrayList<Car> cars;

    public PrimaryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_primary,container,false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponent(view);

        setAllDataFromDB();

    }


    private void initComponent(View view){

        Button showAds = (Button)view.findViewById(R.id.showAdsBtn);
        Button registr = (Button)view.findViewById(R.id.registrBtn);

        showAds.setOnClickListener(v ->showAds());
        registr.setOnClickListener(view1 -> launchLoginOrRegistration());
    }


    private void setAllDataFromDB(){

        cars = (ArrayList<Car>)getArguments().getSerializable(Constants.KEY_PRIM_FR);

    }


    private void launchLoginOrRegistration(){

        Intent registerIntent=new Intent(getContext(), LoginRegisterActivity.class);

        startActivity(registerIntent);

    }

    private void showAds() {

        Intent showAllAdsIntent=new Intent(getContext(), SearchableActivity.class);
        showAllAdsIntent.putExtra(Constants.KEY_TOTAL_CARS,cars);
        showAllAdsIntent.setType(Constants.TYPE_PRIMARY);
           startActivity(showAllAdsIntent);
    }





}
