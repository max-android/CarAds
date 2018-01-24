package com.example.carads.ui.primary;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.support.design.widget.TabLayout;

import android.view.View;
import android.widget.Toast;
import com.bumptech.glide.RequestManager;
import com.example.carads.storage.database.entity.Car;
import com.example.carads.ui.filter.FilterActivity;
import com.example.carads.ui.myads.AdvertisementActivity;
import com.example.carads.ui.myads.MyAdsActivity;
import com.example.carads.ui.registration.LoginRegisterActivity;
import com.example.carads.ui.search.FavoritesActivity;
import com.example.carads.ui.search.SearchableActivity;
import com.example.carads.ui.setting.SettingsActivity;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.service.CarsService;
import com.example.carads.R;
import com.example.carads.storage.database.AppBase;
import com.example.carads.storage.database.DatabaseManager;
import com.example.carads.di.App;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private  List<Car> carList;
    private DrawerLayout drawer;
    private SearchView searchView;
    private  CompositeDisposable subscription;
    @Inject
    CarsService service;

    @Inject
    RequestManager requestManager;

    @Inject
    AppBase base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        App.getAppComponent().injectMainActivity(this);

        initComponents();

        initData();

    }

 private void initComponents(){

     Toolbar toolbar=(Toolbar)findViewById(R.id.mainToolBar);
     setSupportActionBar(toolbar);
     //getSupportActionBar().setTitle(R.string.example2);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

     ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
             this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
     drawer.addDrawerListener(toggle);
     toggle.syncState();


     NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
     navigationView.setNavigationItemSelectedListener(this);

    // View view = navigationView.inflateHeaderView(R.layout.nav_header_main);

     View view =  navigationView.getHeaderView(0);

     view.setOnClickListener(v -> launchLoginOrRegistration());

     // Toast.makeText(CarActivity.this,"HeaderView",Toast.LENGTH_SHORT).show();


     SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
     searchView = (SearchView) findViewById(R.id.searchView);
     searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
     searchView.setQueryHint(Constants.SEARCH_HINT);
    // searchView.setIconifiedByDefault(false);
    // searchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
     //searchView.setMaxWidth(getResources().getDimension(R.dimen.));
     //searchView.clearFocus();

     searchView.setOnQueryTextListener(queryTextListener);

 }

private SearchView.OnQueryTextListener queryTextListener=new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {

        launchSearchByMarka(query);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    private void launchSearchByMarka(String query){

        Intent intent=new Intent(CarActivity.this,SearchableActivity.class);

        intent.putExtra(Constants.KEY_SEARCH_ACT,query);
        intent.setType(Constants.TYPE_PRIMARY_SEARCH);

        startActivity(intent);

    }
};


    private  void initData(){

    //Flowable.fromIterable(service.getCars());

    DatabaseManager databaseManager = new DatabaseManager(base);

     subscription = new CompositeDisposable();

//    if(NetInspector.isOnline(this)){
//
      //обновление данных типо с сервера
//        carList=service.getCars();
//
//       // запись данных в бд
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        databaseManager.writeDataIntoBD(carList);
//
//                    }
//                }).start();
//
//        launchDataIntoFrag(new ArrayList<Car>(carList));

//    }else{

        subscription.add(databaseManager.readAllDataFromBD()

                //.doOnSuccess(list ->Collections.shuffle(list))
                .doOnSuccess(list ->showLog(list))
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list ->  launchDataIntoFrag(new ArrayList<Car>(list))
                        ,(error) -> Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()));
   // }

}



private void launchLoginOrRegistration(){

        Intent registerIntent=new Intent(this, LoginRegisterActivity.class);

             startActivity(registerIntent);

    navigationBackPressed();
}


    private void showLog(List<Car> list){


}


    private void launchDataIntoFrag(ArrayList<Car> cars) {

        PrimaryFragment primaryFragment=new PrimaryFragment();
        Bundle bundlePrimary=new Bundle();
        bundlePrimary.putSerializable(Constants.KEY_PRIM_FR,cars);
        primaryFragment.setArguments(bundlePrimary);

        AutoFragment autoFragment=new AutoFragment();
        Bundle bundleAuto=new Bundle();
        bundleAuto.putSerializable(Constants.KEY_AUTO_FR,cars);
        autoFragment.setArguments(bundleAuto);

        joinViewPagerWithFrag(primaryFragment,autoFragment);

    }


    private void joinViewPagerWithFrag(PrimaryFragment primaryFragment,AutoFragment autoFragment){

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tab);

        ViewPager viewPager=(ViewPager)findViewById(R.id.viewPager);

        viewPagerAdapter.addFragment(primaryFragment,Constants.TITUL_AUTO_FR);

        viewPagerAdapter.addFragment(autoFragment,Constants.TITUL_PRIMARY_FR);

        viewPager.setAdapter(viewPagerAdapter);

          tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public void onBackPressed() {

        navigationBackPressed();
    }



private void navigationBackPressed(){

    if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
    } else {
        super.onBackPressed();
    }

}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.car_menu, menu);

        //под вопросом ипользования
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){

            case R.id.action_filter:

                launchFilter();

                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }


    private void launchFilter(){

        Intent  filter = new Intent(CarActivity.this, FilterActivity.class);

        startActivity(filter);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.nav_search:

                Toast.makeText(this,Constants.NAVI_MESSAGE_BY_SEARCH,Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_favor:

                launchFavoriteSearch();

                break;

            case R.id.nav_my_ads:

                launchMyAds();

                break;

            case R.id.nav_add:

                launchDoAdvertisement();

                break;

            case R.id.nav_setting:

                launchSettings();

                break;

            default:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void launchMyAds(){

        Intent myAdaIntent=new Intent(this, MyAdsActivity.class);
        startActivity(myAdaIntent);

    }

    private void launchDoAdvertisement(){

        Intent myAdvertisement = new Intent(this, AdvertisementActivity.class);
        startActivity(myAdvertisement);

    }

    private void launchSettings(){

        Intent mySettings = new Intent(this, SettingsActivity.class);
        startActivity(mySettings);

    }

    private void launchFavoriteSearch(){

        Intent myFavoriteSearch = new Intent(this, FavoritesActivity.class);
        startActivity(myFavoriteSearch);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        subscription.clear();
    }
}


