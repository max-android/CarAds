package com.example.carads.model.storage.database.entity;

/**
 * Created by Максим on 14.02.2018.
 */

public class AutoTransmitter {

    private int id;
    private String type;
    private Car car;


    private String query_one;
    private String query_two;



    public AutoTransmitter(int id, String type, Car car) {
        this.id = id;
        this.type = type;
        this.car = car;
    }

  public AutoTransmitter(String type,String query_one,String query_two){

        this.type=type;
       this.query_one=query_one;
       this.query_two=query_two;
  }

    public AutoTransmitter(String type, String query_one){
        this.type=type;
        this.query_one=query_one;
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

    public String getQuery_one() {
        return query_one;
    }

    public String getQuery_two() {
        return query_two;
    }

    public void setQuery_one(String query_one) {
        this.query_one = query_one;
    }

    public void setQuery_two(String query_two) {
        this.query_two = query_two;
    }
}
