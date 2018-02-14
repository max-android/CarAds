package com.example.carads.presenter.transmitters;

import com.example.carads.model.storage.database.entity.Car;

import java.util.List;

/**
 * Created by Максим on 13.02.2018.
 */

public interface TransmitterDataFromPresenter {


    void showCars(List<Car> result);


}
