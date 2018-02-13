package com.example.carads.presenter;

import android.util.Log;

import com.example.carads.model.service.CarsService;
import com.example.carads.model.storage.database.DatabaseManager;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.ui.callbacks.GetFunc;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.ui.utilities.Message;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Максим on 13.02.2018.
 */

public class PrimaryPresenter implements TransmitterDataForReqiest,TransmitterCleaning {


    @Inject
    DatabaseManager databaseManager;

    @Inject
    CarsService service;

    private TransmitterDataFromPresenter transmitter;
    private TransmitterErrorFromPresenter mistake;

    private CompositeDisposable subscrition = new CompositeDisposable();


    public PrimaryPresenter(CarsService service,DatabaseManager databaseManager) {
        this.service=service;
        this.databaseManager = databaseManager;
    }

    public void setTransmitter(TransmitterDataFromPresenter transmitter) {
        this.transmitter = transmitter;
    }

    public void setMistake(TransmitterErrorFromPresenter mistake) {
        this.mistake = mistake;
    }

    @Override
    public void getParam(GetFunc<String> data) {

        checkAvailabilityDB();

    }




    private void checkAvailabilityDB(){

        subscrition.add(databaseManager.readSizeFromBD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> initData(integer)
                        ,error -> mistake.showError(Message.WRITE_DB_ERROR)));
    }


    private void initData(int size){
        if(size==0){insertDataIntoDB();}else{initDataFromDB();}
    }


    private void initDataFromDB(){

        subscrition.add(databaseManager.readAllDataFromBD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> transmitter.showCars(list)
                        ,error -> mistake.showError(Message.WRITE_DB_ERROR)));
    }

    private void insertDataIntoDB(){

        Completable.fromCallable(
                ()->{
                    databaseManager.writeDataIntoBD(service.getCars());
                    return null;
                }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()-> Log.d("successfully", Message.WRITE_DB_SUCCESSFULLY),
                        error-> mistake.showError(Message.WRITE_DB_ERROR));

        transmitter.showCars(service.getCars());
    }


    @Override
    public void dismissalResource() {
        subscrition.clear();
    }
}
