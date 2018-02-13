package com.example.carads.di;

import com.example.carads.model.service.CarsService;
import com.example.carads.model.storage.database.DatabaseManager;
import com.example.carads.presenter.FavoritesPresenter;
import com.example.carads.presenter.PrimaryPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Максим on 13.02.2018.
 */



@Module
@Singleton
public class PresenterModule {


    @Provides
    @Singleton
    public PrimaryPresenter provideprimaryPresenter(CarsService carsService, DatabaseManager databaseManager){

        return  new PrimaryPresenter(carsService,databaseManager);
    }

    @Provides
    @Singleton
    public FavoritesPresenter provideFavoritesPresenter(DatabaseManager databaseManager){

        return new FavoritesPresenter(databaseManager);
    }


}
