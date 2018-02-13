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
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.RequestManager;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.presenter.PrimaryPresenter;
import com.example.carads.ui.filter.FilterActivity;
import com.example.carads.ui.myads.AddEditAdActivity;
import com.example.carads.ui.myads.MyAdsActivity;
import com.example.carads.ui.registration.LoginRegisterActivity;
import com.example.carads.ui.favorites.FavoritesActivity;
import com.example.carads.ui.search.SearchableActivity;
import com.example.carads.ui.setting.SettingsActivity;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.R;
import com.example.carads.model.storage.database.AppBase;
import com.example.carads.di.App;
import com.example.carads.ui.utilities.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import javax.inject.Inject;

public class CarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Inject
    RequestManager requestManager;

    @Inject
    AppBase base;

    @Inject
    FirebaseAuth firebaseAuth;

    @Inject
    PrimaryPresenter primaryPresenter;

    private FirebaseUser currentUser;
    private View headerView;
    private TextView  tvHeaderStatusReg;
    private DrawerLayout drawer;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        initComponentsOnCreate();

    }

    @Override
    protected void onStart() {

        initComponentsOnStart();

        updateNavigationHeader(currentUser);

        super.onStart();
    }

 private void initComponentsOnCreate(){

     Toolbar toolbar=(Toolbar)findViewById(R.id.mainToolBar);
     setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

     ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
             this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
     drawer.addDrawerListener(toggle);
     toggle.syncState();

     NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
     navigationView.setNavigationItemSelectedListener(this);

      headerView =  navigationView.getHeaderView(0);

     headerView.setOnClickListener(v -> launchLoginOrRegistration());

     SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
     searchView = (SearchView) findViewById(R.id.searchView);
     searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
     searchView.setQueryHint(Constants.SEARCH_HINT);
    // searchView.setIconifiedByDefault(false);
    // searchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
     //searchView.setMaxWidth(getResources().getDimension(R.dimen.));
     //searchView.clearFocus();

     searchView.setOnQueryTextListener(queryTextListener);

   //  currentUser=firebaseAuth.getCurrentUser();
 }


    private void initComponentsOnStart(){
        App.getAppComponent().injectMainActivity(this);

        primaryPresenter.getParam(()-> Message.ALL_DATA);
        primaryPresenter.setTransmitter(list->launchDataIntoFrag(new ArrayList<Car>(list)));
        primaryPresenter.setMistake(this::showMessage );

        currentUser=firebaseAuth.getCurrentUser();

        tvHeaderStatusReg = (TextView) headerView.findViewById(R.id.tvHeaderStatusReg);

    }


 private void updateNavigationHeader(FirebaseUser currentUser){

if(currentUser!=null){

    tvHeaderStatusReg.setText(getString(R.string.emailpassword_status_fmt,
            currentUser.getEmail(),currentUser.isEmailVerified()));

}else{

    tvHeaderStatusReg.setText(getString(R.string.register));

}

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


private void launchLoginOrRegistration(){

        Intent registerIntent=new Intent(this, LoginRegisterActivity.class);

             startActivity(registerIntent);

    navigationBackPressed();
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

                launchFavorites();

                break;

            case R.id.nav_my_ads:

                if (currentUser != null) {
                    launchMyAds();
                }else{
                     showMessage(getString(R.string.not_acces_my_ads));
                }

                break;

            case R.id.nav_add:
                if (currentUser != null) {
                launchAddAd();
                }else{
                    showMessage(getString(R.string.not_ad_my_ads));
                }
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

    private void launchAddAd(){

        Intent myAd = new Intent(this, AddEditAdActivity.class);
        myAd.setType(Constants.TYPE_ADD_AD);
        startActivity(myAd);

    }

    private void launchSettings(){

        Intent mySettings = new Intent(this, SettingsActivity.class);
        startActivity(mySettings);

    }

    private void launchFavorites(){

        Intent myFavoriteSearch = new Intent(this, FavoritesActivity.class);
        startActivity(myFavoriteSearch);

    }

    private void showMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

   primaryPresenter.dismissalResource();

    }
}


