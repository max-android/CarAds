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
import android.widget.TextView;

import com.example.carads.R;
import com.example.carads.di.App;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.ui.registration.LoginRegisterActivity;
import com.example.carads.ui.search.SearchableActivity;
import com.example.carads.ui.utilities.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by Максим on 23.11.2017.
 */

public class PrimaryFragment extends Fragment {


    private   ArrayList<Car> cars;

    private FirebaseUser currentUser;

    private TextView  tvZaregistr;

    @Inject
    FirebaseAuth mAuth;

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

        updateUI(view,currentUser);

        setAllDataFromDB();

    }

    private void initComponent(View view){

        App.getAppComponent().injectPrimaryFragment(this);
        currentUser = mAuth.getCurrentUser();


        Button showAds = (Button)view.findViewById(R.id.showAdsBtn);
        Button registr = (Button)view.findViewById(R.id.registrBtn);
        tvZaregistr = (TextView)view.findViewById(R.id.tvZaregistr);


        showAds.setOnClickListener(this::setListener);
        registr.setOnClickListener(this::setListener);
        tvZaregistr.setOnClickListener(this::setListener);
    }



    private void setListener(View v){

        switch (v.getId()) {

            case R.id.showAdsBtn:

                showAds();

                break;
            case R.id.registrBtn:

                launchLoginOrRegistration();

                break;
            case R.id.tvZaregistr:

                launchLoginOrRegistration();

                break;
    }
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





    private void updateUI(View view,FirebaseUser currentUser) {

        if(currentUser!=null){

            view.findViewById(R.id.lin_info).setVisibility(View.GONE);
            view.findViewById(R.id.lin_zareg).setVisibility(View.VISIBLE);
            tvZaregistr.setText(getString(R.string.emailpassword_status_fmt,
                    currentUser.getEmail(), currentUser.isEmailVerified()));

        }else{
            view.findViewById(R.id.lin_info).setVisibility(View.VISIBLE);
            view.findViewById(R.id.lin_zareg).setVisibility(View.GONE);

        }
    }





}
