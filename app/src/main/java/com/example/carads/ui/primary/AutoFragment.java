package com.example.carads.ui.primary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.example.carads.R;
import com.example.carads.storage.database.entity.Car;
import com.example.carads.di.App;
import com.example.carads.ui.search.AvtoAdapter;
import com.example.carads.ui.utilities.Constants;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;


/**
 * Created by Максим on 23.11.2017.
 */

public class AutoFragment extends Fragment implements AvtoAdapter.CarClickListener {


    private RecyclerView carsRecycler;

    @Inject
    RequestManager requestManager;

    private ArrayList<Car> cars;

    public AutoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_auto,container,false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponents(view);

        setDataIntoList();
    }



    private void initComponents(View view){

        carsRecycler=(RecyclerView)view.findViewById(R.id.rvShuffleCars);
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        carsRecycler.setLayoutManager(mLayoutManager);
        carsRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));

        App.getAppComponent().injectAvtoFragment(this);

    }

    private void setDataIntoList(){

        cars = (ArrayList<Car>)getArguments().getSerializable(Constants.KEY_AUTO_FR);

        Collections.shuffle(cars);

        ArrayList<Car> newList=new ArrayList<>();

        for (int i=0;i<20;i++){

            newList.add(cars.get(i));

        }

        carsRecycler.setAdapter(new AvtoAdapter(newList,requestManager,this));
    }



    @Override
    public void onCarClick(Car car) {




    }
}
