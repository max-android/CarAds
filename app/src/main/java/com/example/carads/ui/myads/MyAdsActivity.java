package com.example.carads.ui.myads;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.carads.R;
import com.example.carads.di.App;
import com.example.carads.model.storage.database.AppBase;
import com.example.carads.model.storage.database.DatabaseManager;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.ui.search.AvtoAdapter;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.ui.utilities.Dialog;
import com.example.carads.ui.utilities.Notification;
import com.example.carads.ui.utilities.Picture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MyAdsActivity extends AppCompatActivity implements AvtoAdapter.CarClickListener  {

    @Inject
    AppBase base;

    @Inject
    FirebaseAuth firebaseAuth;

    @Inject
    RequestManager requestManager;


    private TextView tvMyMail;
    private TextView tvInfo;

    private FirebaseUser user;
    private CompositeDisposable subscription;

    private RecyclerView recyclerMyAdds;
    private AvtoAdapter avtoAdapter;
    private List<Car> carsMyAdds;
    private  DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads);

        initComponents();

       // showUser(user);

       // showUserAds();
    }


    @Override
    protected void onStart() {

        App.getAppComponent().injectMyAdsActivity(this);
        user = firebaseAuth.getCurrentUser();

        showUser(user);

        carsMyAdds=new ArrayList<>();
        databaseManager = new DatabaseManager(base);

        subscription = new CompositeDisposable();

        showUserAds();

        super.onStart();

    }


    private void initComponents() {

       // App.getAppComponent().injectMyAdsActivity(this);

        tvMyMail = (TextView)findViewById(R.id.tvMyMail);
        tvInfo =  (TextView)findViewById(R.id.tvInfoMyAds);
        recyclerMyAdds=(RecyclerView)findViewById(R.id.rvMyAdds);
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(this);
        recyclerMyAdds.setLayoutManager(mLayoutManager);
        recyclerMyAdds.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbarMyAds);
        toolbar.setTitle(R.string.my_adds_label);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this,R.drawable.ic_arrow_back_24dp));
        toolbar.setNavigationOnClickListener(exit -> onBackPressed());
      //  user = firebaseAuth.getCurrentUser();

//        databaseManager = new DatabaseManager(base);
//
//        subscription = new CompositeDisposable();

    }


    private void showList(){
        recyclerMyAdds.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        tvInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
    }

    private void showTextInfo(){

        recyclerMyAdds.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));

        tvInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }



    private void showUser(FirebaseUser user){
        if(user!=null){

            tvMyMail.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(),user.isEmailVerified()));
        }
    }




    private void showUserAds(){

        subscription.add(databaseManager.readMyAdFromBD(user.getEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cars ->launchDataInto(cars) ,
                        (error)-> Toast.makeText(this,getString(R.string.user_not_exist_ads),Toast.LENGTH_LONG).show())
        );


    }

    private void launchDataInto(List<Car> cars) {

        if(cars.isEmpty()){
            showTextInfo();

        }else{

            showList();
            carsMyAdds.addAll(cars);

            avtoAdapter=new AvtoAdapter(carsMyAdds,requestManager,this);
            recyclerMyAdds.setAdapter(avtoAdapter);

        }
    }


    @Override
    public void onCarClick(Car car) {

        Executor executor = Executors.newFixedThreadPool(1);

        executor.execute(()->{
            try {
            Drawable drawable  = new Picture().drawableFromUrl(car.getImage());
            runOnUiThread(()->showDialog(drawable,car));
        } catch (Exception e) {
            e.printStackTrace();

            showMessage(getString(R.string.dialog_image_error));
                 }
        });

    }


    private void showDialog(Drawable drawable,Car car){

        Dialog dialog=new Dialog();
        dialog.showDialog(this,drawable,()-> deleteEntryIntoMyAdds(car),()-> editMyAdd(car),R.string.select_ad,R.string.dialog_title_my_ads,R.string.edit_my_ads);
    }


    private void deleteEntryIntoMyAdds(Car car) {

        carsMyAdds.remove(car);

        avtoAdapter.notifyDataSetChanged();


        Completable.fromCallable(
                ()->{

                   databaseManager.deleteCarFromBD(car);
                    return null;
                }

        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->launchStatusDelete(),
                        (error)-> Toast.makeText(this,getString(R.string.error_delete_ad),Toast.LENGTH_LONG).show());


//       Executor executor= Executors.newFixedThreadPool(1);
//        executor.execute(()->{
//
//            int integer = databaseManager.deleteCarFromBD(car);
//
//            runOnUiThread(()->launchStatusDelete(integer));
//
//        });
    }


    private void editMyAdd(Car car){

        //запуск активити для добавления - там изменяем

        launchEditActivity(car);

    }


    private void launchStatusDelete(){

        showMessage(getString(R.string.delete_ad));

    }

    private void showMessage(String message){
        Notification notice=new Notification(findViewById(R.id.lin_my_ads),this);
        notice.showMessage(message);
    }


    private void launchEditActivity(Car car){

        Intent intent=new Intent(this,AddEditAdActivity.class);

        intent.putExtra(Constants.EDIT,car);

        intent.setType(Constants.TYPE_EDIT_AD);
        startActivity(intent);
    }


    @Override
    protected void onStop() {
        subscription.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  subscription.clear();
    }
}
