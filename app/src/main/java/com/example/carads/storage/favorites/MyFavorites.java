package com.example.carads.storage.favorites;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.carads.ui.utilities.Constants;

import java.util.Map;



/**
 * Created by Максим on 16.01.2018.
 */


public class MyFavorites {


private SharedPreferences preferences;
private SharedPreferences.Editor editor;

    private Context context;

    public MyFavorites(Context context) {
        preferences = context.getSharedPreferences(Constants.FAVORITES,Context.MODE_PRIVATE);
    }


    public void setCar(String key,String marka){

        createSpEditor();
        editor.putString(key,marka);
        editor.apply();
    }


    public  Map<String,?> getKeysFavorites(){

        Map<String,?> prefsMap = preferences.getAll();

           return prefsMap;
    }


    public SharedPreferences getPreferences() {
        return preferences;
    }



    public void deleteCar(String key){

        createSpEditor();
        editor.remove(key);
        editor.apply();

    }


    private void createSpEditor(){

        editor = preferences.edit();

    }


}
