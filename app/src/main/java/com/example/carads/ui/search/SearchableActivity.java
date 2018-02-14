package com.example.carads.ui.search;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.carads.R;
import com.example.carads.model.storage.database.AppBase;
import com.example.carads.model.storage.database.DatabaseManager;
import com.example.carads.model.storage.database.entity.AutoTransmitter;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.di.App;
import com.example.carads.presenter.SearchablePresenter;
import com.example.carads.ui.detail.DetailActivity;
import com.example.carads.ui.utilities.Constants;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SearchableActivity extends AppCompatActivity implements AvtoAdapter.CarClickListener {



    @Inject
    SearchablePresenter searchablePresenter;


    private RecyclerView carsRecycler;
    private ProgressBar progressBar;


    @Inject
    RequestManager requestManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        initComponents();

        initSelectCarsAds();

        createItemTouch();
    }


    private void initComponents(){

        App.getAppComponent().injectSearchableActivity(this);

        carsRecycler=(RecyclerView) findViewById(R.id.rvCars);
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(this);
        carsRecycler.setLayoutManager(mLayoutManager);
        carsRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //carsRecycler.addOnScrollListener(scrollListener);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
       // progressBar.setVisibility(View.VISIBLE);

       Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarListCars);
      //  setSupportActionBar(toolbar);
      //  getSupportActionBar().setTitle(R.string.empty_body);
        toolbar.setTitle(R.string.result_search);
//        toolbar.setSubtitleTextColor(ContextCompat.getColor(this,R.color.colorWhite));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this,R.drawable.ic_arrow_back_24dp));
        toolbar.setNavigationOnClickListener(exit -> onBackPressed());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCarsLocation);
        fab.setOnClickListener(floating ->{     } );
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

            case Constants.TYPE_POPULAR_MARKA:
                launchPopularBrands();
                break;
                default:
                    showMessage();
                    break;

        }

    }


    private void showMessage() {

        Toast.makeText(this,Constants.REPEAT_QUERY,Toast.LENGTH_LONG).show();
    }


    private void showAllAds(ArrayList<Car> list){

        Executors.newSingleThreadExecutor().execute(()-> {

            try {
                Thread.sleep(1000);
                runOnUiThread(()->showDataFor(list));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });


    }


    private void showDataFor(ArrayList<Car> list){

        if(list.size()==0){

            showMessage();
        }else {

            carsRecycler.setAdapter(new AvtoAdapter(list, requestManager, this));
        }

        progressBar.setVisibility(View.INVISIBLE);
    }


    private void showDataFor(ArrayList<Car> list,String marka){

        if(list.size()==0){

            showMessage();

        }else{

            ArrayList<Car> newList=new ArrayList<>();

            for(Car car:list){
                if(car.getName().toLowerCase().contains(marka.toLowerCase())){
                    newList.add(car);
                }
            }
            carsRecycler.setAdapter(new AvtoAdapter(newList,requestManager,this));

        }

        progressBar.setVisibility(View.INVISIBLE);
    }

    private void launchAllAdsAuto(){

        Intent showAllAdsIntent = getIntent();

        ArrayList<Car> totalList = (ArrayList<Car>)showAllAdsIntent.getSerializableExtra(Constants.KEY_TOTAL_CARS);

        showAllAds(totalList);
    }

    private void launchCarsFromSearch(){

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


    private void launchPopularBrands(){

        Intent intent = getIntent();

        String marka=intent.getStringExtra(Constants.KEY_AUTO_FR_POPULAR);

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

        searchablePresenter.getObj(()-> new AutoTransmitter(Constants.MARKA_TRANSMITTER,query));
        searchablePresenter.setTransmitter(list -> showDataFor(new ArrayList<Car>(list),query));
        searchablePresenter.setMistake(error -> showError());

    }


    private void doMySearchByColor(String query) {

        searchablePresenter.getObj(()-> new AutoTransmitter(Constants.COLOR_TRANSMITTER,query));

        initSettersPresenter();

    }


    private void doMySearchByPrice(String from_price,String to_price) {


        searchablePresenter.getObj(()-> new AutoTransmitter(Constants.PRICE_TRANSMITTER,from_price,to_price));

        initSettersPresenter();

    }


    private void doMySearchByDate(String from_date,String to_date) {


        searchablePresenter.getObj(()-> new AutoTransmitter(Constants.DATE_TRANSMITTER,from_date,to_date));

        initSettersPresenter();


    }


    private void doMySearchByValue(String from_value,String to_value){

        searchablePresenter.getObj(()-> new AutoTransmitter(Constants.VALUE_TRANSMITTER,from_value,to_value));

        initSettersPresenter();

    }

    private void doMySearchByPower(String from_power,String to_power){


        searchablePresenter.getObj(()-> new AutoTransmitter(Constants.POWER_TRANSMITTER,from_power,to_power));

        initSettersPresenter();

    }


    private void initSettersPresenter(){

        searchablePresenter.setTransmitter(list -> showDataFor(new ArrayList<Car>(list)));
        searchablePresenter.setMistake(error -> showError());

    }



          private void showError(){
              Toast.makeText(this,Constants.INVALID_QUERY,Toast.LENGTH_LONG).show();

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
    public void onCarClick(Car car,View view) {

        showDetailDataCar(car,view);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        searchablePresenter.dismissalResource();

    }


    private  void showDetailDataCar(Car car,View view){
        ImageView imageView = (ImageView) view;
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,imageView,Constants.TRANSITION_IMAGE);
        Intent showDetail = new Intent(this, DetailActivity.class);
        showDetail.putExtra(Constants.DETAIL_OF_CAR,car);
        showDetail.setType(Constants.TYPE_COMMONS);
        startActivity(showDetail,options.toBundle());
    }


    private void createItemTouch(){
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(carsRecycler);
    }

    private ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
             AvtoAdapter adapter = (AvtoAdapter)carsRecycler.getAdapter();
            adapter.adOnMove(fromPosition,toPosition);
            return true;
        }
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            AvtoAdapter adapter = (AvtoAdapter)carsRecycler.getAdapter();
            adapter.adOnSwiped(viewHolder);
        }
    };


}
