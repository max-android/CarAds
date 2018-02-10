package com.example.carads.ui.utilities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.carads.R;
import com.example.carads.ui.callbacks.FuncVoid;


public class LocationPermission {

    public  void requestPermission(Context context, FuncVoid funcVoid){

//        ACCESS_COARSE_LOCATION - использование приблизительного определения местонахождения
// при помощи вышек сотовой связи или точек доступа Wi-Fi
//        ACCESS_FINE_LOCATION - точное определение местонахождения при помощи GPS


        //проверка - есть ли разрешение для определ местоположения,если нет,то объяснить почему необходимо
        // разрешение на повторный запрос
        if (ActivityCompat.checkSelfPermission
                ( context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {


             //случай отсутствия разрешения
            //объяснение и запрос на предоставление
            if (ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity)context,
                    Manifest.permission.ACCESS_FINE_LOCATION)&&ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity)context,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                //случай,если первый раз отказали,то дается объяснение при повторном запросе

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage(R.string.permission_location_explanation);

                builder.setPositiveButton(android.R.string.ok,(dialog,which)->{request(context);});

                builder.setCancelable(false);
                builder.create().show();

            }
            else {
                //запрос на предоставление
                request(context);
            }
        }

        else {
            //случай наличия разрешения
            funcVoid.action();
        }

    }

              //Запрос разрешения
    private void request(Context context){
        ActivityCompat.requestPermissions((AppCompatActivity)context,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                Constants.LOCATION_PERMISSION_REQUEST_CODE);
    }



}
