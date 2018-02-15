package com.example.carads.presenter;

import com.example.carads.model.storage.database.DatabaseManager;
import com.example.carads.model.storage.database.entity.AutoTransmitter;
import com.example.carads.presenter.transmitters.TransmitterCleaning;
import com.example.carads.presenter.transmitters.TransmitterComplexDataForRequest;
import com.example.carads.presenter.transmitters.TransmitterDataFromPresenter;
import com.example.carads.presenter.transmitters.TransmitterErrorFromPresenter;
import com.example.carads.ui.callbacks.GetFunc;
import com.example.carads.ui.utilities.Constants;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Максим on 14.02.2018.
 */

public class SearchablePresenter implements TransmitterComplexDataForRequest,TransmitterCleaning{


    @Inject
    DatabaseManager databaseManager;


    private TransmitterDataFromPresenter transmitter;
    private TransmitterErrorFromPresenter mistake;

    private CompositeDisposable subscrition = new CompositeDisposable();

    public SearchablePresenter (DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void setTransmitter(TransmitterDataFromPresenter transmitter) {
        this.transmitter = transmitter;
    }

    public void setMistake(TransmitterErrorFromPresenter mistake) {
        this.mistake = mistake;
    }



    @Override
    public void getObj(GetFunc<AutoTransmitter> data) {

        AutoTransmitter auto = data.transferData();


        switch (auto.getType()){

            case  Constants.PRICE_TRANSMITTER:

                searchByPrice(auto.getQuery_one(),auto.getQuery_two());

                break;

            case  Constants.DATE_TRANSMITTER:

                searchByDate(auto.getQuery_one(),auto.getQuery_two());

                break;

            case  Constants.POWER_TRANSMITTER:

                searchByPower(auto.getQuery_one(),auto.getQuery_two());

                break;

            case  Constants.VALUE_TRANSMITTER:

                searchByValue(auto.getQuery_one(),auto.getQuery_two());

                break;

            case  Constants.COLOR_TRANSMITTER:

                searchByColor(auto.getQuery_one());

                break;

            case  Constants.MARKA_TRANSMITTER:

                searchByMarka();

                break;

        }

    }



    private void searchByPrice(String from_price,String to_price){

        subscrition.add(databaseManager.readPriceCarsFromBD(Integer.valueOf(from_price),Integer.valueOf(to_price))

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list -> transmitter.showCars(list)
                        ,error -> mistake.showError(Constants.INVALID_QUERY)));
    }


    private void searchByDate(String from_date,String to_date){

        subscrition.add(databaseManager.readDateIssueFromBD(from_date,to_date)

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list -> transmitter.showCars(list)
                        ,error -> mistake.showError(Constants.INVALID_QUERY)));

    }


    private void searchByPower(String from_power,String to_power){


        subscrition.add(databaseManager.readPowerCarsFromBD(Integer.valueOf(from_power),Integer.valueOf(to_power))

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list -> transmitter.showCars(list)
                        ,error -> mistake.showError(Constants.INVALID_QUERY)));


    }


    private void searchByValue(String from_value,String to_value){


        subscrition.add(databaseManager.readValumeCarsFromBD(Double.valueOf(from_value),Double.valueOf(to_value))

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list -> transmitter.showCars(list)
                        ,error -> mistake.showError(Constants.INVALID_QUERY)));
    }


    private void searchByColor(String query){

        subscrition.add(databaseManager.readColorCarsFromBD(query)

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list -> transmitter.showCars(list)
                        ,error -> mistake.showError(Constants.INVALID_QUERY)));

    }


    private void searchByMarka(){

        subscrition.add(databaseManager.readAllDataFromBD()

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe (list -> transmitter.showCars(list)
                        ,error -> mistake.showError(Constants.INVALID_QUERY)));
    }



    @Override
    public void dismissalResource() {

        subscrition.clear();

    }


}
