package com.example.carads.presenter.transmitters;

import com.example.carads.model.storage.database.entity.AutoTransmitter;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.ui.callbacks.GetFunc;

/**
 * Created by Максим on 14.02.2018.
 */

public interface TransmitterDataForUpdIns {


    void getObj(GetFunc<AutoTransmitter> data);

}
