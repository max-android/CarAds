package com.example.carads.ui.search;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.carads.R;
import com.example.carads.storage.database.AppBase;
import com.example.carads.storage.database.DatabaseManager;
import com.example.carads.storage.database.entity.Car;
import com.example.carads.di.App;
import com.example.carads.ui.detail.DetailActivity;
import com.example.carads.ui.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchableActivity extends AppCompatActivity implements AvtoAdapter.CarClickListener {

    private RecyclerView carsRecycler;
    private ProgressBar progressBar;
    private DatabaseManager searchIntoDB;
    private CompositeDisposable subscrition;

    @Inject
    RequestManager requestManager;

    @Inject
    AppBase base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        initComponents();

        initSelectCarsAds();

    }


    private void initComponents(){

        App.getAppComponent().injectSearchableActivity(this);

        carsRecycler=(RecyclerView) findViewById(R.id.rvCars);
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(this);
        carsRecycler.setLayoutManager(mLayoutManager);
        carsRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //carsRecycler.addOnScrollListener(scrollListener);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        searchIntoDB=new DatabaseManager(base);
        subscrition = new CompositeDisposable();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void initSelectCarsAds(){

        Intent select = getIntent();


        switch (select.getType()){

            case Constants.TYPE_PRIMARY:
                launchAllAdsAuto();
                break;

            case Constants.TYPE_PRIMARY_SEARCH:
                launchCarsFromSearch();
                break;

            case Constants.TYPE_SEARCH_MARKA:
               launchSelectAutoByMarka();
                break;
            case Constants.TYPE_SEARCH_DATE:
           launchSelectAutoByDate();
                break;
            case Constants.TYPE_SEARCH_PRICE:
            launchSelectAutoByPrice();
                break;
            case Constants.TYPE_SEARCH_COLOR:
             launchSelectAutoByColor();
                break;
            case Constants.TYPE_SEARCH_VALUE:
             launchSelectAutoByValue();
                break;
            case Constants.TYPE_SEARCH_POWER:
            launchSelectAutoByPower();
                break;
                default:
                    showMessage();
                    break;

        }

    }


    private void showMessage() {

        Toast.makeText(this,Constants.REPEAT_QUERY,Toast.LENGTH_LONG).show();
    }


    private void showDataFor(ArrayList<Car> list){

        carsRecycler.setAdapter(new AvtoAdapter(list,requestManager,this));
        progressBar.setVisibility(View.INVISIBLE);
    }


    private void showDataFor(ArrayList<Car> list,String marka){

        ArrayList<Car> newList=new ArrayList<>();

        for(Car car:list){
            if(car.getName().toLowerCase().contains(marka.toLowerCase())){
                newList.add(car);
            }
        }
        carsRecycler.setAdapter(new AvtoAdapter(newList,requestManager,this));
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void launchAllAdsAuto(){

        Intent showAllAdsIntent = getIntent();

        ArrayList<Car> totalList = (ArrayList<Car>)showAllAdsIntent.getSerializableExtra(Constants.KEY_TOTAL_CARS);

        showDataFor(totalList);


    }

    private void launchCarsFromSearch(){

//        Intent intent = getIntent();
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            doMySearchByMarka(query);
//        }

        Intent intent = getIntent();

        String query =  intent.getStringExtra(Constants.KEY_SEARCH_ACT);

        doMySearchByMarka(query);

    }


    private void launchSelectAutoByMarka() {

        Intent intent = getIntent();

        String marka=intent.getStringExtra(Constants.FILTER_MARKA);

        Log.d("MARKA-2-2-2",marka);

        doMySearchByMarka(marka);
    }

    private void launchSelectAutoByDate(){

        Intent intent = getIntent();

        String[] query = intent.getStringArrayExtra(Constants.FILTER_DATE);

        String from_date=query[0];
        String to_date=query[1];

        Log.d("DATE-SEARCHABLE",from_date+"-------"+to_date);

        doMySearchByDate(from_date,to_date);

    }

    private void launchSelectAutoByPrice(){

        Intent intent = getIntent();

        String[] query = intent.getStringArrayExtra(Constants.FILTER_PRICE);

        String from_price=query[0];
        String to_price=query[1];

        Log.d("PRICE-SEARCHABLE",from_price+"-------"+to_price);


        doMySearchByPrice(from_price,to_price);

    }

    private void launchSelectAutoByColor(){

        Intent intent = getIntent();

        String color =  intent.getStringExtra(Constants.FILTER_COLOR);

        doMySearchByColor(color);
    }

    private void launchSelectAutoByValue(){

        Intent intent = getIntent();

        String[] query = intent.getStringArrayExtra(Constants.FILTER_VALUE);

        String from_value=query[0];
        String to_value=query[1];

        Log.d("VALUE-SEARCHABLE",from_value+"-------"+to_value);

        doMySearchByValue(from_value,to_value);

    }

    private void launchSelectAutoByPower(){

        Intent intent = getIntent();

        String[] query = intent.getStringArrayExtra(Constants.FILTER_POWER);

        String from_power=query[0];
        String to_power=query[1];

        Log.d("POWER-SEARCHABLE",from_power+"-------"+to_power);

        doMySearchByPower(from_power,to_power);

    }



    private void doMySearchByMarka(String query) {

        subscrition.add(searchIntoDB.readAllDataFromBD()

                .doOnSuccess(list -> showHint(list))

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list ->  showDataFor(new ArrayList<Car>(list),query)
                        ,(error) -> Toast.makeText(this,Constants.INVALID_QUERY,Toast.LENGTH_LONG).show()));

//        subscrition.add(searchIntoDB.readMarkaFromBD(query)
//
//                .doOnSuccess(list -> showHint(list))
//
//                .subscribeOn(Schedulers.io())
//
//                .observeOn(AndroidSchedulers.mainThread())
//
//                .subscribe (list ->  showDataFor(new ArrayList<Car>(list))
//                        ,(error) -> Toast.makeText(this,Constants.INVALID_QUERY,Toast.LENGTH_LONG).show()));

    }

    private void doMySearchByDate(String from_date,String to_date) {

        subscrition.add(searchIntoDB.readDateIssueFromBD(from_date,to_date)

                .doOnSuccess(list -> showHint(list))

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list ->  showDataFor(new ArrayList<Car>(list))
                        ,(error) -> Toast.makeText(this,Constants.INVALID_QUERY,Toast.LENGTH_LONG).show()));

    }

    private void doMySearchByPrice(String from_price,String to_price) {

        subscrition.add(searchIntoDB.readPriceCarsFromBD(Integer.valueOf(from_price),Integer.valueOf(to_price))

                .doOnSuccess(list -> showHint(list))

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list ->  showDataFor(new ArrayList<Car>(list))
                        ,(error) -> Toast.makeText(this,Constants.INVALID_QUERY,Toast.LENGTH_LONG).show()));

    }

    private void doMySearchByColor(String query) {

        subscrition.add(searchIntoDB.readColorCarsFromBD(query)

                .doOnSuccess(list -> showHint(list))

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list ->  showDataFor(new ArrayList<Car>(list))
                        ,(error) -> Toast.makeText(this,Constants.INVALID_QUERY,Toast.LENGTH_LONG).show()));

    }

    private void doMySearchByValue(String from_value,String to_value){

        subscrition.add(searchIntoDB.readValumeCarsFromBD(Double.valueOf(from_value),Double.valueOf(to_value))

                .doOnSuccess(list -> showHint(list))

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list ->  showDataFor(new ArrayList<Car>(list))
                        ,(error) -> Toast.makeText(this,Constants.INVALID_QUERY,Toast.LENGTH_LONG).show()));

    }

    private void doMySearchByPower(String from_power,String to_power){

        subscrition.add(searchIntoDB.readPowerCarsFromBD(Integer.valueOf(from_power),Integer.valueOf(to_power))

                .doOnSuccess(list -> showHint(list))

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list ->  showDataFor(new ArrayList<Car>(list))
                        ,(error) -> Toast.makeText(this,Constants.INVALID_QUERY,Toast.LENGTH_LONG).show()));
    }


    private void showHint(List<Car> list) {

        if(list.size()==0){

            Toast.makeText(this,Constants.INVALID_QUERY,Toast.LENGTH_LONG).show();
        }

        progressBar.setVisibility(View.INVISIBLE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){

            case R.id.action_save_search:

             //сохранить фильтр запроса по ключевым словам
                // нужно выбрать актуальн запрос и далее сохранить в Preferene данный запрос
                //для каждого launchSelect реализовать ссылку на слушатель,кот будет доб данные
                //он будет передавать данные сюда для сохранения в Preferene
                //данные будут складироваться в FavoritesActivity,кот будет вызываться
                //при нажатии с навигации и запускаться определенный тип для SearchableActivity

                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCarClick(Car car) {

        showDetailDataCar(car);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscrition.clear();
    }


    private  void showDetailDataCar(Car car){
        Intent showDetail = new Intent(this, DetailActivity.class);
        showDetail.putExtra(Constants.DETAIL_OF_CAR,car);
        showDetail.setType(Constants.TYPE_COMMONS);
        startActivity(showDetail);
    }

}
