package com.example.carads.ui.filter;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.carads.R;
import com.example.carads.ui.dialog.ColorCarDialog;
import com.example.carads.ui.dialog.DateIssueCarDialog;
import com.example.carads.ui.dialog.MarkaDialog;
import com.example.carads.ui.dialog.PowerCarDialog;
import com.example.carads.ui.dialog.PriceCarDialog;
import com.example.carads.ui.dialog.ValueCarDialog;
import com.example.carads.ui.search.SearchableActivity;
import com.example.carads.ui.utilities.Constants;

public class FilterActivity extends AppCompatActivity  implements MarkaDialog.MarkaDialogListener,ColorCarDialog.ColorCarDialogListener,DateIssueCarDialog.DateIssueCarDialogListener,PowerCarDialog.PowerCarDialogListener,ValueCarDialog.ValueCarDialogListener,PriceCarDialog.PriceCarDialogListener {

private RadioButton rbMarka;
private RadioButton rbDate;
private RadioButton rbCost;
private RadioButton rbColor;
private RadioButton rbValue;
private RadioButton rvPower;
//private FragmentManager manager;

    private  Intent search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        initComponent();

        choiceFilter();

    }


    private void initComponent(){

         rbMarka=(RadioButton)findViewById(R.id.rbMarka);
         rbDate=(RadioButton)findViewById(R.id.rbDate);
         rbCost=(RadioButton)findViewById(R.id.rbCost);
         rbColor=(RadioButton)findViewById(R.id.rbColor);
         rbValue=(RadioButton)findViewById(R.id.rbValue);
         rvPower=(RadioButton)findViewById(R.id.rvPower);

    }



    private void choiceFilter() {

        View.OnClickListener listener=(v -> {

            switch (v.getId()) {

                case R.id.rbMarka:

                    showSelectMarkaCar();

                    break;

                case R.id.rbDate:
                    showChosenCarsByDate();
                    break;

                case R.id.rbCost:

                    showChosenCarsByPrice();

                    break;
                case R.id.rbColor:
                    showChosenCarsByColor();
                    break;
                case R.id.rbValue:
                    showChosenCarsByValue();
                    break;
                case R.id.rvPower:
                    showChosenCarsByPower();
                    break;

            }
        });

        rbMarka.setOnClickListener(listener);
        rbDate.setOnClickListener(listener);
        rbCost.setOnClickListener(listener);
        rbColor.setOnClickListener(listener);
        rbValue.setOnClickListener(listener);
        rvPower.setOnClickListener(listener);
    }


    private void showSelectMarkaCar(){

        FragmentManager  manager =  getSupportFragmentManager();

        MarkaDialog markaDialog=new MarkaDialog();

        markaDialog.show(manager, Constants.TAG_MARKA_DIALOG);

    }

    private void showChosenCarsByDate(){

        FragmentManager  manager =  getSupportFragmentManager();

        DateIssueCarDialog dateIssueCarDialog=new DateIssueCarDialog();

        dateIssueCarDialog.setCancelable(false);

        dateIssueCarDialog.show(manager, Constants.TAG_DATE_DIALOG);

    }

    private void showChosenCarsByPrice(){

        FragmentManager  manager =  getSupportFragmentManager();

        PriceCarDialog priceCarDialog=new  PriceCarDialog();

        priceCarDialog.setCancelable(false);

        priceCarDialog.show(manager, Constants.TAG_PRICE_DIALOG);

    }
    private void showChosenCarsByColor(){

        FragmentManager  manager =  getSupportFragmentManager();
        ColorCarDialog colorCarDialog=new ColorCarDialog();
        colorCarDialog.setCancelable(false);
        colorCarDialog.show(manager, Constants.TAG_COLOR_DIALOG);

    }
    private void showChosenCarsByValue(){

      FragmentManager  manager =  getSupportFragmentManager();

        ValueCarDialog valueCarDialog=new ValueCarDialog();

        valueCarDialog.setCancelable(false);

        valueCarDialog.show(manager, Constants.TAG_VALUE_DIALOG);

    }


    private void showChosenCarsByPower(){

      FragmentManager  manager =  getSupportFragmentManager();

        PowerCarDialog powerCarDialog=new PowerCarDialog();

        powerCarDialog.setCancelable(false);

        powerCarDialog.show(manager, Constants.TAG_POWER_DIALOG);
    }


    @Override
    public void onMarkaSet(String marka) {

        Log.d("MARKA",marka);

        search = new Intent(FilterActivity.this, SearchableActivity.class);

        search.putExtra(Constants.FILTER_MARKA,marka);
        search.setType(Constants.TYPE_SEARCH_MARKA);
        startActivity(search);

    }


    @Override
    public void onValueSet(String from_value, String to_value) {

        Log.d("Value",from_value+"-------"+to_value);

        search = new Intent(FilterActivity.this, SearchableActivity.class);

        String [] values=new String[2];
        values[0]=from_value;
        values[1]=to_value;

        search.putExtra(Constants.FILTER_VALUE,values);
        search.setType(Constants.TYPE_SEARCH_VALUE);
        startActivity(search);

    }

    @Override
    public void onPowerSet(String from_power, String to_power) {
        Log.d("POWER",from_power+"-------"+to_power);

        search = new Intent(FilterActivity.this, SearchableActivity.class);

        String [] powers = new String[2];
        powers[0]=from_power;
        powers[1]=to_power;

        search.putExtra(Constants.FILTER_POWER,powers);
        search.setType(Constants.TYPE_SEARCH_POWER);
        startActivity(search);

    }

    @Override
    public void onPriceSet(String from_price, String to_price) {

        Log.d("POWER",from_price+"-------"+to_price);

        search = new Intent(FilterActivity.this, SearchableActivity.class);

        String [] prices = new String[2];
        prices[0]=from_price;
        prices[1]=to_price;

        search.putExtra(Constants.FILTER_PRICE,prices);
        search.setType(Constants.TYPE_SEARCH_PRICE);
        startActivity(search);

    }

    @Override
    public void onColorSet(String color) {

        Log.d("COLOR",color);

        search = new Intent(FilterActivity.this, SearchableActivity.class);

        search.putExtra(Constants.FILTER_COLOR,color);
        search.setType(Constants.TYPE_SEARCH_COLOR);
        startActivity(search);
    }

    @Override
    public void onDateIssueSet(String from_date, String to_date) {

        Log.d("DATE",from_date+"-------"+to_date);

        search = new Intent(FilterActivity.this, SearchableActivity.class);

        String [] date=new String[2];
        date[0]=from_date;
        date[1]=to_date;

        search.putExtra(Constants.FILTER_DATE,date);
        search.setType(Constants.TYPE_SEARCH_DATE);
        startActivity(search);
    }
}
