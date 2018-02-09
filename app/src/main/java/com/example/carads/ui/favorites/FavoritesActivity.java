package com.example.carads.ui.favorites;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.example.carads.storage.database.AppBase;
import com.example.carads.storage.database.DatabaseManager;
import com.example.carads.storage.database.entity.Car;
import com.example.carads.storage.favorites.MyFavorites;
import com.example.carads.ui.detail.DetailActivity;
import com.example.carads.ui.search.AvtoAdapter;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.ui.utilities.Dialog;
import com.example.carads.ui.utilities.Notification;
import com.example.carads.ui.utilities.Picture;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FavoritesActivity extends AppCompatActivity implements AvtoAdapter.CarClickListener {


    @Inject
    MyFavorites myFavorites;

    @Inject
    AppBase base;

    @Inject
    RequestManager requestManager;


    private TextView tvInfo;
    private RecyclerView recyclerFavorites;

    private List<Car> carsFavorites;
    private AvtoAdapter avtoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_search);

        initComponents();
        launchListFavorites();

    }


    private void initComponents(){

        App.getAppComponent().injectFavoritesActivity(this);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbarFavorites);

        toolbar.setSubtitle(getString(R.string.favorite));

        tvInfo=(TextView)findViewById(R.id.tvInfoFavorites);

        recyclerFavorites=(RecyclerView)findViewById(R.id.recyclerFavorites);
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(this);
        recyclerFavorites.setLayoutManager(mLayoutManager);
        recyclerFavorites.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        carsFavorites = new ArrayList<>();

    }



    //поиск объявлений: по ключам SharedPref мы делаем запрос в бд и получаем объекты Car и кладем в список
    private void launchListFavorites() {

        Map<String, ?> favorites = myFavorites.getKeysFavorites();

            if (!favorites.isEmpty()){

            showList();

            DatabaseManager databaseManager = new DatabaseManager(base);

            CompositeDisposable subscription = new CompositeDisposable();

        for (Map.Entry<String, ?> entry : favorites.entrySet()) {

            subscription.add(databaseManager.readFavoritesCarFromBD(entry.getKey())

                    .subscribeOn(Schedulers.io())

                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(car -> showCars(car)
                            , (error) -> Toast.makeText(this, getString(R.string.not_found_ads), Toast.LENGTH_LONG).show()));
        }

    }else{showTextInfo();}

    }


    private void showList(){
        recyclerFavorites.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        tvInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
    }

    private void showTextInfo(){

        recyclerFavorites.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));

        tvInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }



    private void showCars(Car car){

        carsFavorites.add(car);

        if(avtoAdapter!=null){

            avtoAdapter.notifyDataSetChanged();

        }else{

            avtoAdapter=new AvtoAdapter(carsFavorites,requestManager,this);
            recyclerFavorites.setAdapter(avtoAdapter);

        }
    }


    @Override
    public void onCarClick(Car car) {


new Thread(()->{

    try {
        Drawable drawable  = new Picture().drawableFromUrl(car.getImage());
       // Drawable drawable  = drawableFromUrl(car.getImage());
        runOnUiThread(()->showDialog(drawable,car));

    } catch (Exception e) {
        e.printStackTrace();

        showMessage(getString(R.string.dialog_image_error));
    }

}).start();

    }


              //вызов диалога и три действия на выбор
    private void showDialog(Drawable drawable,Car car){

        Dialog dialog=new Dialog();
        dialog.showDialog(this,drawable,()-> deleteEntryIntoFavorites(car),()->showDetailInfo(car),R.string.select_ad,R.string.question,R.string.detail);

    }



    private void showDetailInfo(Car car) {

Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(Constants.FAVOR,car);
        detailIntent.setType(Constants.TYPE_FAVORITES);
             startActivity(detailIntent);
    }

    private void deleteEntryIntoFavorites(Car car) {

        myFavorites.deleteCar(car.getOwner());

        carsFavorites.remove(car);

        avtoAdapter.notifyDataSetChanged();

        showMessage(getString(R.string.delete_entry));
    }


    private void showMessage(String message){
        Notification notice=new Notification(findViewById(R.id.lin_favorite),this);
        notice.showMessage(message);
    }



                //загрузка картинки в диалоговое окно
    public Drawable drawableFromUrl(String url)throws Exception{

            HttpURLConnection connection=(HttpURLConnection) createURL(url).openConnection();
            connection.connect();

            int response = connection.getResponseCode();

        Drawable drawable=null;

            if(response==HttpURLConnection.HTTP_OK){
                InputStream input = connection.getInputStream();
                drawable = Drawable.createFromStream(input,Constants.DIALOG_IMAGE);
                input.close();
            }

            connection.disconnect();
            return drawable;
    }


    private URL createURL(String uri){

        URL url= null;
        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    //2 способ
//    public void  drawableFromUrl2(String url, SetFunc<Drawable> setFunc)  {
//
//        Handler handler=new Handler();
//
//        new Thread(()-> {
//
//            HttpURLConnection connection=null;
//            try {
//                connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.connect();
//            InputStream input = null;
//
//                input = connection.getInputStream();
//                Bitmap bitmap = BitmapFactory.decodeStream(input);
//
//                handler.post(()->{setFunc.transferResult(new BitmapDrawable(bitmap));} );
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                connection.disconnect();
//            }
//
//
//        } ).start();
//    }




}
