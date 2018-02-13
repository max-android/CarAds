package com.example.carads.model.storage.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.carads.model.storage.database.entity.Car;


/**
 * Created by Максим on 13.11.2017.
 */




@Database(entities = {Car.class}, version = 1)
public abstract class AppBase extends RoomDatabase {

    public abstract CarDao getCarDao();




}
