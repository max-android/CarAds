package com.example.carads.di;

import com.example.carads.model.service.CarsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Максим on 23.11.2017.
 */





@Module
@Singleton
public class ServiceModule {



    @Provides
    @Singleton
    public CarsService provideCarService(){

        CarsService carsService = new CarsService();


        return carsService;
    }





}
