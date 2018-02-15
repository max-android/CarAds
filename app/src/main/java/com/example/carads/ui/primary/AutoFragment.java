package com.example.carads.ui.primary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.bumptech.glide.RequestManager;
import com.example.carads.R;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.di.App;
import com.example.carads.ui.detail.DetailActivity;
import com.example.carads.ui.search.AvtoAdapter;
import com.example.carads.ui.search.SearchableActivity;
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

        App.getAppComponent().injectAvtoFragment(this);
        carsRecycler=(RecyclerView)view.findViewById(R.id.rvShuffleCars);
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        carsRecycler.setLayoutManager(mLayoutManager);
        carsRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));


        ImageButton imageAudi =(ImageButton)view.findViewById(R.id.imageAudi);
        ImageButton imageBmw =(ImageButton)view.findViewById(R.id.imageBmw);
        ImageButton imageMerc =(ImageButton)view.findViewById(R.id.imageMerc);
        ImageButton imagePorc =(ImageButton)view.findViewById(R.id.imagePorc);
        ImageButton imageInf =(ImageButton)view.findViewById(R.id.imageInf);

        imageAudi.setOnClickListener(this::setListener);
        imageBmw.setOnClickListener(this::setListener);
        imageMerc.setOnClickListener(this::setListener);
        imagePorc.setOnClickListener(this::setListener);
        imageInf.setOnClickListener(this::setListener);
    }


    private void setListener(View view){

        switch (view.getId()){

           case  R.id.imageAudi:
               startActivity(new Intent(getContext(), SearchableActivity.class).putExtra(Constants.KEY_AUTO_FR_POPULAR,getContext().getString(R.string.audi)).setType(Constants.TYPE_POPULAR_MARKA));
            break;

            case  R.id.imageBmw:
                startActivity(new Intent(getContext(), SearchableActivity.class).putExtra(Constants.KEY_AUTO_FR_POPULAR,getContext().getString(R.string.bmw)).setType(Constants.TYPE_POPULAR_MARKA));
                break;

            case  R.id.imageMerc:
                startActivity(new Intent(getContext(), SearchableActivity.class).putExtra(Constants.KEY_AUTO_FR_POPULAR,getContext().getString(R.string.mers)).setType(Constants.TYPE_POPULAR_MARKA));
                break;

            case  R.id.imagePorc:
                startActivity(new Intent(getContext(), SearchableActivity.class).putExtra(Constants.KEY_AUTO_FR_POPULAR,getContext().getString(R.string.porche)).setType(Constants.TYPE_POPULAR_MARKA));
                break;

            case  R.id.imageInf:
                startActivity(new Intent(getContext(), SearchableActivity.class).putExtra(Constants.KEY_AUTO_FR_POPULAR,getContext().getString(R.string.infinity)).setType(Constants.TYPE_POPULAR_MARKA));
                break;
        }
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
    public void onCarClick(Car car,View view) {

        launchDetailCar(car,view);

    }


    private void launchDetailCar(Car car,View view){

        ImageView imageView = (ImageView) view;
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),imageView,Constants.TRANSITION_IMAGE);

        Intent intent = new Intent(getContext(), DetailActivity.class);

        intent.putExtra(Constants.KEY_RANDOM,car);

        intent.setType(Constants.TYPE_RANDOM);

           startActivity(intent,options.toBundle());
    }



}
