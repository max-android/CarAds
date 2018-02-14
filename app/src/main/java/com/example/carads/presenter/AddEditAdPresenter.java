package com.example.carads.presenter;

import android.widget.Toast;

import com.example.carads.R;
import com.example.carads.model.storage.database.DatabaseManager;
import com.example.carads.model.storage.database.entity.AutoTransmitter;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.presenter.transmitters.TransmitterCleaning;
import com.example.carads.presenter.transmitters.TransmitterDataForDelete;
import com.example.carads.presenter.transmitters.TransmitterDataForUpdIns;
import com.example.carads.presenter.transmitters.TransmitterErrorFromPresenter;
import com.example.carads.presenter.transmitters.TransmitterMessageFromPresenter;
import com.example.carads.ui.callbacks.GetFunc;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.ui.utilities.Message;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Максим on 14.02.2018.
 */

public class AddEditAdPresenter implements TransmitterCleaning,TransmitterDataForUpdIns {


    @Inject
    DatabaseManager databaseManager;

    private TransmitterErrorFromPresenter mistake;
    private TransmitterMessageFromPresenter message;

    private CompositeDisposable subscrition = new CompositeDisposable();


    public AddEditAdPresenter(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void setMistake(TransmitterErrorFromPresenter mistake) {
        this.mistake = mistake;
    }

    public void setMessage(TransmitterMessageFromPresenter message) {
        this.message = message;
    }

    @Override
    public void getObj(GetFunc<AutoTransmitter> data) {

        AutoTransmitter auto = data.transferData();
        String type = auto.getType();

        switch (type){

            case Constants.UPDATE_TRANSMITTER:

                updateData(auto);

                break;

            case Constants.INSERT_TRANSMITTER:

                insertData(auto);

                break;
        }
    }


    private void updateData(AutoTransmitter auto){

        Completable.fromCallable(
                ()->{

                    databaseManager.updateCarFromBD(auto.getId(),auto.getCar().getName(),
                    auto.getCar().getImage(),
                    auto.getCar().getDate_issue(),
                    auto.getCar().getMileage(),
                    auto.getCar().getColor(),
                    auto.getCar().getPrice(),
                    auto.getCar().getValume(),
                    auto.getCar().getPower(),
                    auto.getCar().getOwner(),
                    auto.getCar().getPhone(),
                    auto.getCar().getMail(),
                    auto.getCar().getAddress(),
                    auto.getCar().getLatitude(),
                    auto.getCar().getLongitude()
                    );

                    return null;
                }

        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->message.action(),
                        error-> mistake.showError(Message.ERROR_UPDATE_AD));
    }


    private void insertData(AutoTransmitter auto){

        Completable.fromCallable(
                ()->{

                    databaseManager.insertCarIntoBD(new Car(auto.getCar().getName(),
                            auto.getCar().getImage(),
                            auto.getCar().getDate_issue(),
                            auto.getCar().getMileage(),
                            auto.getCar().getColor(),
                            auto.getCar().getPrice(),
                            auto.getCar().getValume(),
                            auto.getCar().getPower(),
                            auto.getCar().getOwner(),
                            auto.getCar().getPhone(),
                            auto.getCar().getMail(),
                            auto.getCar().getAddress(),
                            auto.getCar().getLatitude(),
                            auto.getCar().getLongitude()
                            ));
                    return null;
                }

        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->message.action(),
                        (error)-> mistake.showError(Message.ERROR_INSET_AD));



    }


    @Override
    public void dismissalResource() {
        subscrition.clear();
    }



}
