package com.example.carads.presenter;

import com.example.carads.model.storage.database.DatabaseManager;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.presenter.transmitters.TransmitterCleaning;
import com.example.carads.presenter.transmitters.TransmitterSimpleDataForRequest;
import com.example.carads.presenter.transmitters.TransmitterDataFromPresenter;
import com.example.carads.presenter.transmitters.TransmitterErrorFromPresenter;
import com.example.carads.presenter.transmitters.TransmitterMessageFromPresenter;
import com.example.carads.presenter.transmitters.TransmitterParamForRequest;
import com.example.carads.ui.callbacks.GetFunc;
import com.example.carads.ui.utilities.Message;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Максим on 14.02.2018.
 */

public class MyAdsPresenter implements TransmitterParamForRequest,TransmitterCleaning,TransmitterSimpleDataForRequest {


    @Inject
    DatabaseManager databaseManager;

    private TransmitterDataFromPresenter transmitter;
    private TransmitterErrorFromPresenter mistake;
    private TransmitterMessageFromPresenter message;

    private CompositeDisposable subscrition = new CompositeDisposable();


    public MyAdsPresenter (DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void setTransmitter(TransmitterDataFromPresenter transmitter) {
        this.transmitter = transmitter;
    }

    public void setMessage(TransmitterMessageFromPresenter message) {
        this.message = message;
    }

    public void setMistake(TransmitterErrorFromPresenter mistake) {
        this.mistake = mistake;
    }


    @Override
    public void getParam(GetFunc<String> data) {

          initData(data.transferData());

    }


    private void initData(String type){

        subscrition.add(databaseManager.readMyAdFromBD(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cars ->transmitter.showCars(cars) ,
                        error-> mistake.showError(Message.USER_NOT_EXIST_ADS))
        );
    }


    @Override
    public void getObj(GetFunc<Car> data) {

        updateData(data.transferData());

    }


    private void updateData(Car car){

        Completable.fromCallable(
                ()->{

                    databaseManager.deleteCarFromBD(car);

                    return null;
                }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->message.action(),
                        (error)-> mistake.showError(Message.ERROR_DELETE_AD));
    }



    @Override
    public void dismissalResource() {
        subscrition.clear();
    }



}
