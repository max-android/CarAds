package com.example.carads.ui.myads;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carads.R;
import com.example.carads.di.App;
import com.example.carads.model.storage.database.AppBase;
import com.example.carads.model.storage.database.DatabaseManager;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.ui.utilities.LocationPermission;
import com.example.carads.ui.utilities.NetInspector;
import com.example.carads.ui.utilities.Notification;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AddEditAdActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {


    @Inject
    AppBase base;

    @Inject
    FirebaseAuth firebaseAuth;

    @Inject
    LocationPermission locationPermission;


  private DatabaseManager databaseManager;


  private TextView tvMyAuth;
    private FirebaseUser user;

    private EditText etName;
    private EditText etImage;
    private EditText  etDate;
    private EditText   etMileage;
    private EditText  etColor;
    private EditText  etPrice;
    private EditText  etValume;
    private EditText  etPower;
    private EditText  etOwner;
    private EditText  etPhone;
    private EditText  etMail;
    private EditText  etAddress;
    private TextView tvLatitude;
    private TextView tvLongitude;

    private boolean edit=true;

    private   Executor executor;
    private double latitude;
    private double longitude;

    private  CompositeDisposable subscription;

    private GoogleApiClient googleApiClient;
    private Location mLocation;
    private LocationManager locationManager;
//    private LocationRequest mLocationRequest;
    private Toolbar toolbar;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ad);


        initComponents();
        initComponentsForlocating();
        showUser(user);
        initData();
    }


    private void initComponents(){

        App.getAppComponent().injectAddEditAdActivity(this);

        user = firebaseAuth.getCurrentUser();
         toolbar = (Toolbar) findViewById(R.id.tbAddEditAd);

        //setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this,R.drawable.ic_arrow_back_24dp));
        toolbar.setNavigationOnClickListener(exit -> onBackPressed());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddEditAd);

        tvMyAuth=(TextView)findViewById(R.id.tvMyAuth);

        fab.setOnClickListener(view -> saveData());

                etName=((TextInputLayout)findViewById(R.id.til_name)).getEditText();
        etImage=((TextInputLayout)findViewById(R.id.til_image)).getEditText();
        etDate=((TextInputLayout)findViewById(R.id.til_date)).getEditText();
        etMileage=((TextInputLayout)findViewById(R.id.til_mileage)).getEditText();
        etColor=((TextInputLayout)findViewById(R.id.til_color)).getEditText();
        etPrice=((TextInputLayout)findViewById(R.id.til_price)).getEditText();
        etValume=((TextInputLayout)findViewById(R.id.til_valume)).getEditText();
        etPower=((TextInputLayout)findViewById(R.id.til_power)).getEditText();
        etOwner=((TextInputLayout)findViewById(R.id.til_owner)).getEditText();
        etPhone=((TextInputLayout)findViewById(R.id.til_phone)).getEditText();
        etMail=((TextInputLayout)findViewById(R.id.til_mail)).getEditText();
        etAddress=((TextInputLayout)findViewById(R.id.til_address)).getEditText();

        tvLatitude=(TextView) findViewById(R.id.tvLatitude);
        tvLongitude=(TextView) findViewById(R.id. tvLongitude);

        //spinnerCities =(Spinner) findViewById(R.id.spinnerCities);

        databaseManager=new DatabaseManager(base);
        executor= Executors.newFixedThreadPool(1);
    }


    private void initComponentsForlocating(){

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }



    private void showUser(FirebaseUser user){
        if(user!=null){

            tvMyAuth.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(),user.isEmailVerified()));
        }
    }


private  void initData(){

    Intent select = getIntent();

    switch (select.getType()){

        case Constants.TYPE_ADD_AD:

            instruct();

            break;

        case Constants.TYPE_EDIT_AD:
            editAd();
            break;

        default:
            showMessage(R.string.repeat_query);
            break;
    }
}


private void editAd(){

    toolbar.setTitle(R.string.add_edit_label);

    car = (Car)getIntent().getSerializableExtra(Constants.EDIT);

    etName.setText(car.getName());
    etImage.setText(car.getImage());
    etDate.setText(car.getDate_issue());
    etMileage.setText(car.getMileage());
    etColor.setText(car.getColor());
    etPrice.setText(String.valueOf(car.getPrice()));
    etValume.setText(String.valueOf(car.getValume()));
    etPower.setText(String.valueOf(car.getPower()));
    etOwner.setText(car.getOwner());
    etPhone.setText(car.getPhone());
    etMail.setText(car.getMail());
    etAddress.setText(car.getAddress());
}

    
    private void instruct(){
        edit=false;

        toolbar.setTitle(R.string.add_ads);

        showMessage(R.string.not_filled);
    }




private void saveData(){

    if(validateForm()){

        if(edit){updateDataBase();
        }else{

         if(NetInspector.isOnline(this)){ insertAdIntoDataBase();}
            else{showMessage(R.string.snack_no_network);  }
           }

    }else{
        showMessage(R.string.not_filled);
    }
}



    private void updateDataBase() {


        Completable.fromCallable(
                ()->{

                    databaseManager.updateCarFromBD(car.getId(),etName.getText().toString(),
                            etImage.getText().toString(),
                            etDate.getText().toString(),
                            etMileage.getText().toString(),
                            etColor.getText().toString(),
                            Integer.parseInt(etPrice.getText().toString()),
                            Double.valueOf(etValume.getText().toString()),
                            Integer.parseInt(etPower.getText().toString()),
                            etOwner.getText().toString(),
                            etPhone.getText().toString(),
                            etMail.getText().toString(),
                            etAddress.getText().toString(),
                            latitude,longitude
                    );
                    return null;
                }

        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->launchStatusUpdate(),
                        (error)-> Toast.makeText(this,getString(R.string.error_update_ad),Toast.LENGTH_LONG).show());

    }

    private void launchStatusUpdate(){

        showMessage(R.string.update_ad);

        onBackPressed();
    }


    private void insertAdIntoDataBase() {


        Completable.fromCallable(
                ()->{

                    databaseManager.insertCarIntoBD(new Car(etName.getText().toString(),
                            etImage.getText().toString(),
                            etDate.getText().toString(),
                            etMileage.getText().toString(),
                            etColor.getText().toString(),
                            Integer.parseInt(etPrice.getText().toString()),
                            Double.valueOf(etValume.getText().toString()),
                            Integer.parseInt(etPower.getText().toString()),
                            etOwner.getText().toString(),
                            etPhone.getText().toString(),
                            etMail.getText().toString(),
                            etAddress.getText().toString(),
                            latitude,longitude
                    ));
                    return null;
                }

                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->launchStatusInsert(),
                        (error)-> Toast.makeText(this,getString(R.string.error_insert_ad),Toast.LENGTH_LONG).show());

    }


    private void launchStatusInsert() {

        showMessage(R.string.insert_ad);

        onBackPressed();
    }

    private void showMessage(int message){

    Notification notification=new Notification(findViewById(R.id.coordAddEditAd),this);

    notification.showMessage(getString(message));
}


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Constants.LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED&& grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    //срабатывает только один раз при получении разрешения

                    showMessage(R.string.permission_location_granted);


                } else {

                    //простой вариант вариант без обязательного получения разрешения к хранилищу+убрать onActivityResult
                    //в данном случае пользователь постоянно будет получать такое сообщение и будет пользоваться приложением
                    //с ограниченными возможностями (без записи) или польхователь сам может найти настройки для доступа
                    //к хранилищу  для приложения
                    showMessage(R.string.permission_location_denied);
                    onBackPressed();
                }
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationPermission.requestPermission(this,()->receiveLocation());

    }


    @SuppressLint("MissingPermission")
    private void receiveLocation() {

        mLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (mLocation != null) {
             latitude = mLocation.getLatitude();
             longitude = mLocation.getLongitude();

            Log.d("КООРДИНАТЫ",String.valueOf(latitude));
            Log.d("КООРДИНАТЫ",String.valueOf(longitude));

             tvLatitude.setText(String.valueOf(latitude));
             tvLongitude.setText(String.valueOf(longitude));

        }else{

            showMessage(R.string.turn_on_location);

        }

    }





    @Override
    public void onConnectionSuspended(int i) {
        Log.i("ConnectionSuspended", "Connection Suspended");
        googleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("ConnectionFailed", "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {

    }


    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }


    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }






    private boolean validateForm() {

        boolean valid = true;

        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etName.setError(null);
        }


        if (TextUtils.isEmpty(etImage.getText().toString())) {
            etImage.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etImage.setError(null);
        }



        if (TextUtils.isEmpty(etDate.getText().toString())) {
            etDate.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etDate.setError(null);
        }


        if (TextUtils.isEmpty(etMileage.getText().toString())) {
            etMileage.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etMileage.setError(null);
        }

        if (TextUtils.isEmpty(etColor.getText().toString())) {
            etColor.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etColor.setError(null);
        }

        if (TextUtils.isEmpty(etPrice.getText().toString())) {
            etPrice.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etPrice.setError(null);
        }

        if (TextUtils.isEmpty( etValume.getText().toString())) {
            etValume.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etValume.setError(null);
        }

        if (TextUtils.isEmpty(etPower.getText().toString())) {
            etPower.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etPower.setError(null);
        }


        if (TextUtils.isEmpty(etOwner.getText().toString())) {
            etOwner.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etOwner.setError(null);
        }


        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            etPhone.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etPhone.setError(null);
        }

        if (TextUtils.isEmpty(etMail.getText().toString())) {
            etMail.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etMail.setError(null);
        }

        if (TextUtils.isEmpty(etAddress.getText().toString())) {
            etAddress.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etAddress.setError(null);
        }



        return valid;
    }



}
