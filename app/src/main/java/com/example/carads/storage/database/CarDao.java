package com.example.carads.storage.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.carads.storage.database.entity.Car;


import java.util.List;

import io.reactivex.Single;

/**
 * Created by Максим on 13.11.2017.
 */


@Dao
public interface CarDao {


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertCar(List<Car> cars);


   // void insertFilms(List<Film> cars);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCars(List<Car> cars);




//    @Query("SELECT * FROM films")
//    public List<Film> getAllFilmsDB();


//    @Query("SELECT * FROM films")
//    Maybe<List<Film>> getAllFilmsDB();



    @Query("SELECT * FROM car")
    Single<List<Car>> getAllCarsDB();


    @Delete
    void deleteCars(List<Car> cars);


    @Update
    void updateCars(List<Car> cars);


    @Query("SELECT * FROM car WHERE date_issue BETWEEN :from_date AND :to_date ORDER BY date_issue ASC")
    Single<List<Car>> getCarsByDate(String from_date,String to_date);

    @Query("SELECT * FROM car WHERE price BETWEEN :from_price AND :to_price ORDER BY price ASC")
    Single<List<Car>> getCarsByPrice(int from_price,int to_price);

    @Query("SELECT * FROM car WHERE valume BETWEEN :from_valume AND :to_valume ORDER BY valume ASC")
    Single<List<Car>> getCarsByValume(Double from_valume,Double to_valume);

    @Query("SELECT * FROM car WHERE power BETWEEN :from_power AND :to_power ORDER BY power ASC")
    Single<List<Car>> getCarsByPower(int from_power,int to_power);

    @Query("SELECT * FROM car WHERE color = :select_color")
    Single<List<Car>> getColorCarsDB(String select_color);

    @Query("SELECT * FROM car WHERE name = :marka")
    Single<List<Car>> getMarkaCarsDB(String marka);





    @Query("SELECT * FROM car WHERE owner = :key")
    Single<Car> getCarFromDB(String key);


}
