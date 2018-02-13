package com.example.carads.presenter;

import com.example.carads.model.storage.database.DatabaseManager;
import com.example.carads.ui.callbacks.GetFunc;
import com.example.carads.ui.utilities.Message;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Максим on 13.02.2018.
 */

public class FavoritesPresenter implements TransmitterDataForReqiest,TransmitterCleaning {


    @Inject
    DatabaseManager databaseManager;

    private TransmitterUnitDataFromPresenter transmitter;
    private TransmitterErrorFromPresenter mistake;

    private CompositeDisposable subscrition = new CompositeDisposable();


    public FavoritesPresenter(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void setTransmitter(TransmitterUnitDataFromPresenter transmitter) {
        this.transmitter = transmitter;
    }

    public void setMistake(TransmitterErrorFromPresenter mistake) {
        this.mistake = mistake;
    }



    @Override
    public void getParam(GetFunc<String> data) {

                     initDataFromDB(data.transferData());
    }



    private void initDataFromDB(String key){

        subscrition.add(databaseManager.readFavoritesCarFromBD(key)

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(car -> transmitter.showCar(car)
                        , (error) -> mistake.showError(Message.NOT_FOUND_ADS)));

    }


    @Override
    public void dismissalResource() {
        subscrition.clear();
    }


}
