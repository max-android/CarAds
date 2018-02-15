package com.example.carads.ui.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.RequestManager;
import com.example.carads.R;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.di.App;
import com.example.carads.model.storage.favorites.MyFavorites;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.ui.utilities.Notification;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Максим on 15.01.2018.
 */

public class DetailFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {


    @Inject
    MyFavorites myFavorites;

    @Inject
    RequestManager requestManager;



    private MenuItem item;
    private Car car;
    private Notification notification;
    private  AppBarLayout appBarLayout;


    public DetailFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialData();

        initComponentsWithData(view);

    }


    private void initialData(){

       car = (Car)getArguments().getSerializable(Constants.KEY_FRAG);

    }



    private void initComponentsWithData(View view){

        App.getAppComponent().injectDetailFragment(this);


        Toolbar toolbar = (Toolbar)view.findViewById(R.id.tbDetail);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        appBarLayout=(AppBarLayout)view.findViewById(R.id.appBar);


        notification = new Notification(view.findViewById(R.id.detailCoord),getContext());

        TextView tvNameCar=(TextView)view.findViewById(R.id.tvCarName);
        TextView tvDateIssue=(TextView)view.findViewById(R.id.tvDateIssue);

        TextView tvCarMileage=(TextView)view.findViewById(R.id.tvCarMileage);

        TextView tvСarValue=(TextView)view.findViewById(R.id.tvСarValue);

        TextView tvСarPower=(TextView)view.findViewById(R.id.tvСarPower);

        TextView tvСarColor=(TextView)view.findViewById(R.id.tvСarColor);
        TextView tvСarPrice=(TextView)view.findViewById(R.id.tvСarPrice);

        tvNameCar.setText(car.getName());
        tvDateIssue.setText(car.getDate_issue()+" "+getString(R.string.year));
        tvCarMileage.setText(car.getMileage());
        tvСarValue.setText(String.valueOf(car.getValume())+" "+getString(R.string.litr));
        tvСarPower.setText(String.valueOf(car.getPower())+" "+getString(R.string.l_s));
        tvСarColor.setText(car.getColor());
        tvСarPrice.setText(String.valueOf(car.getPrice())+" "+getString(R.string.rubles));

        FloatingActionButton detailFAB=(FloatingActionButton)view.findViewById(R.id.detailFAB);

        detailFAB.setOnClickListener(view1 -> saveAdIntoFavorites());

        Button btnCall=(Button)view.findViewById(R.id.btnCall);
        Button btnWrite=(Button)view.findViewById(R.id.btnWrite);
        btnCall.setOnClickListener(this::launchCallActivity);
        btnWrite.setOnClickListener(this::launchMailActivity);

        ImageView imageView=(ImageView)view.findViewById(R.id.toolbarImage);
        requestManager.load(car.getImage()).into(imageView);
    }


    private void launchCallActivity(View v){

        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:"+car.getPhone()));

        if(call.resolveActivity(getContext().getPackageManager())!=null){

            startActivity(call);
        }else {
            showMessage();}

    }

    private void launchMailActivity(View v){

        Intent mail = new Intent(Intent.ACTION_SENDTO) ;
        mail.setData(Uri.parse("mailto:"+ car.getMail()));

        if(mail.resolveActivity(getContext().getPackageManager())!=null){

            startActivity(mail);
        }else {
            showMessage();}

    }


    private void showMessage(){

        Toast.makeText(getContext(),getString(R.string.check_connection),Toast.LENGTH_LONG).show();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

           inflater.inflate(R.menu.menu_share,menu);
        item =  menu.findItem(R.id.favorite);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        appBarLayout.addOnOffsetChangedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.favorite:
                saveAdIntoFavorites();
                break;

            case R.id.share:
                share();
                break;

            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAdIntoFavorites() {

        if(myFavorites.getPreferences().contains(car.getOwner())){

            notification.showMessage(getContext().getString(R.string.error_save));

        }else{
            myFavorites.setCar(car.getOwner(),car.getName());

            notification.showMessage(getContext().getString(R.string.successful_save));


            Map<String, ?> favorites = myFavorites.getKeysFavorites();

            for(Map.Entry<String, ?> entry : favorites.entrySet()){
                Log.d("TAG",entry.getKey()+"------"+entry.getValue());
            }
        }
    }


    private void share(){

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_message)+" "
                +car.getImage()
                +" "
                +car.getName()
                +" "
                +car.getPrice()
                +" "
                +car.getColor()
                +" "
                +car.getPower()
                +" "
                +car.getValume()
                +" "
                +car.getPhone()
        );
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent,Constants.SHARE_SEARCH));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appBarLayout.removeOnOffsetChangedListener(this);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

if(Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()){
            item.setVisible(true);

        }else{
            item.setVisible(false);
        }

    }
}
