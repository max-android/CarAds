package com.example.carads.model.storage.database.entity;

/**
 * Created by Максим on 14.02.2018.
 */

public class AutoTransmitter {

    private int id;
    private String type;
    private Car car;


    public AutoTransmitter(int id, String type, Car car) {
        this.id = id;
        this.type = type;
        this.car = car;
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Car getCar() {
        return car;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
