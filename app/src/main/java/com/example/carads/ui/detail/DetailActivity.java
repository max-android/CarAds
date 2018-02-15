package com.example.carads.ui.detail;


import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.carads.R;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.ui.utilities.Constants;


public class DetailActivity extends AppCompatActivity {


    private BottomNavigationView navigation;
    private FragmentManager fragmentManager;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        loadDetailDataCar();

        initComponents();

        showDetailInfo(fragmentManager);
    }

    private void loadDetailDataCar(){

            Intent select = getIntent();

            switch (select.getType()){

                case Constants.TYPE_COMMONS:
                    launchAdFromSearch();
                    break;

                case Constants.TYPE_FAVORITES:
                    launchAdFromFavorites();
                    break;

                case Constants.TYPE_RANDOM:
                    launchAdFromAutoFrag();
                    break;


                default:
                    showMessage();
                    break;
        }

    }



private void launchAdFromSearch(){
    car = (Car) getIntent().getSerializableExtra(Constants.DETAIL_OF_CAR);
}
private void launchAdFromFavorites(){
    car = (Car) getIntent().getSerializableExtra(Constants.FAVOR);
}


    private void launchAdFromAutoFrag(){

        car = (Car) getIntent().getSerializableExtra(Constants.KEY_RANDOM);

    }


    private void showMessage() {

        Toast.makeText(this,Constants.REPEAT_QUERY,Toast.LENGTH_LONG).show();
    }


    private void initComponents(){
        fragmentManager = getSupportFragmentManager();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this::selectDayForForecast);

    }

    private boolean selectDayForForecast(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.show_detail:

                showDetailInfo(fragmentManager);

                return true;

            case R.id.show_save:

                saveImageIntoStorage(fragmentManager);

                return true;

            case R.id.show_map:

                showCarOnMap(fragmentManager);

                return true;
        }
        return false;
    }


    private void showDetailInfo(FragmentManager fragmentManager){

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(Constants.KEY_FRAG,car);
        detailFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frame,detailFragment).commitAllowingStateLoss();
        Menu menu = navigation.getMenu();
        menu.getItem(0).setChecked(true);
        menu.getItem(0).setCheckable(true);

    }

    private void saveImageIntoStorage(FragmentManager fragmentManager){

        SaveFragment favoriteFragment = new SaveFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(Constants.KEY_FRAG,car);
        favoriteFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frame,favoriteFragment).commitAllowingStateLoss();
    }

    private void showCarOnMap(FragmentManager fragmentManager){

        MapFragment mapFragment = new MapFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(Constants.KEY_FRAG,car);
        mapFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frame,mapFragment).commitAllowingStateLoss();
    }

}
