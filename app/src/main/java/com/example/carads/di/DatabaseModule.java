package com.example.carads.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.carads.model.storage.database.AppBase;
import com.example.carads.model.storage.database.DatabaseManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Максим on 23.11.2017.
 */

@Module
@Singleton
public class DatabaseModule {

    private Context context;


    public DatabaseModule(Context context) {
        this.context = context;
    }


    @Provides
    @Singleton
    public AppBase provideDatabase(){

        AppBase base= Room.databaseBuilder(context,AppBase.class, "db_auto").build();

        return base;
    }

    @Provides
    @Singleton
    public DatabaseManager provideDatabaseManager(AppBase base){

        return new DatabaseManager(base);
    }


}
