package com.example.carads.storage.database;

import com.example.carads.storage.database.entity.Car;


import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;

/**
 * Created by Максим on 19.11.2017.
 */

public class DatabaseManager {


    private AppBase base;


    public DatabaseManager(AppBase base) {
        this.base = base;
    }


//    public void writeDataIntoBD(List<Car> list) {
//
//        base.getCarDao().insertCars(list);
//
//
//    }

    public void writeDataIntoBD(List<Car> list) {

     base.getCarDao().insertCars(list);
    }


    public Single<List<Car>> readAllDataFromBD() {


        Single<List<Car>> list = base.getCarDao().getAllCarsDB();

        return list;

    }


    public Single<List<Car>>readMarkaFromBD(String marka) {

        Single<List<Car>> list = base.getCarDao().getMarkaCarsDB(marka);

        return list;
    }


    public Single<List<Car>>  readDateIssueFromBD(String from_date,String to_date) {

        Single<List<Car>> list = base.getCarDao().getCarsByDate(from_date,to_date);

        return list;
    }


    public Single<List<Car>>  readPriceCarsFromBD(int from_price,int to_price) {

        Single<List<Car>> list = base.getCarDao().getCarsByPrice(from_price,to_price);

        return list;
    }

    public Single<List<Car>>  readValumeCarsFromBD(Double from_valume,Double to_valume) {

        Single<List<Car>> list = base.getCarDao().getCarsByValume(from_valume,to_valume);

        return list;
    }

    public Single<List<Car>>  readPowerCarsFromBD(int from_power,int to_power) {

        Single<List<Car>> list = base.getCarDao().getCarsByPower(from_power,to_power);

        return list;
    }

    public Single<List<Car>>  readColorCarsFromBD(String select_color) {

        Single<List<Car>> list = base.getCarDao().getColorCarsDB(select_color);

        return list;
    }


    public Single<Car>  readFavoritesCarFromBD(String key) {

        Single<Car> car =  base.getCarDao().getCarFromDB(key);

        return car;
    }


    public Single<List<Car>> readMyAdFromBD(String email){

        Single<List<Car>>  cars =  base.getCarDao().getCarMyAddFromDB(email);

        return cars;
    }


//    public int deleteCarFromBD(String email){
//
//        //Single<Integer>  car =  base.getCarDao().deleteCarByParameter(email);
//
//        return base.getCarDao().deleteCarByParameter(email);
//    }


    public int deleteCarFromBD(Car car){

        int  delete =  base.getCarDao().deleteCar(car);

        return delete;
    }


    public void  updateCarFromBD(int id,String name, String image, String date_issue, String mileage, String color, int price, double  valume, int power, String owner, String phone, String mail, String address){

       base.getCarDao().updateCarDB(id,name,image,date_issue,mileage,color,price,valume,power,owner,phone,mail,address);


    }


    public void  insertCarIntoBD(Car car){

    base.getCarDao().insertCar(car);

    }


}