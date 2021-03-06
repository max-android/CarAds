package com.example.carads.model.storage.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.carads.model.storage.database.entity.Car;


import java.util.List;

import io.reactivex.Single;

/**
 * Created by Максим on 13.11.2017.
 */


@Dao
public interface CarDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCars(List<Car> cars);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertCar(Car car);


    @Query("SELECT * FROM car")
    Single<List<Car>> getAllCarsDB();


    @Query("SELECT * FROM car")
    List<Car> getAllCarsForSize();

    @Query("SELECT COUNT(*) FROM car")
    Single<Integer> getSizeDB();


    @Delete
    int deleteCar(Car car);


    @Query("SELECT * FROM car WHERE date_issue BETWEEN :from_date AND :to_date ORDER BY date_issue ASC")
    Single<List<Car>> getCarsByDate(String from_date,String to_date);

    @Query("SELECT * FROM car WHERE price BETWEEN :from_price AND :to_price ORDER BY price ASC")
    Single<List<Car>> getCarsByPrice(int from_price,int to_price);

    @Query("SELECT * FROM car WHERE valume BETWEEN :from_valume AND :to_valume ORDER BY valume ASC")
    Single<List<Car>> getCarsByValume(double from_valume,double to_valume);

    @Query("SELECT * FROM car WHERE power BETWEEN :from_power AND :to_power ORDER BY power ASC")
    Single<List<Car>> getCarsByPower(int from_power,int to_power);

    @Query("SELECT * FROM car WHERE color = :select_color")
    Single<List<Car>> getColorCarsDB(String select_color);

    @Query("SELECT * FROM car WHERE name = :marka")
    Single<List<Car>> getMarkaCarsDB(String marka);



    @Query("SELECT * FROM car WHERE owner = :key")
    Single<Car> getCarFromDB(String key);


    @Query("SELECT * FROM car WHERE mail = :email")
    Single<List<Car>> getCarMyAddFromDB(String email);


@Query("UPDATE car SET name = :new_name,"+
        "image = :new_image," +
        "date_issue = :new_date_issue," +
        "mileage = :new_mileage," +
        "color = :new_color," +
        "price = :new_price," +
        "valume = :new_valume," +
        "power = :new_power," +
        "owner = :new_owner," +
        "phone = :new_phone," +
        "mail = :new_mail," +
        "address = :new_address," +
        "latitude= :new_latitude," +
        "longitude= :new_longitude WHERE id = :new_id")
           void updateCarDB(
                        int new_id,
                        String new_name,
                        String new_image,
                        String new_date_issue,
                        String new_mileage,
                        String new_color,
                        int new_price,
                        double new_valume,
                        int new_power,
                        String new_owner,
                        String new_phone,
                        String new_mail,
                        String new_address,
                        double new_latitude,
                        double new_longitude
    );

}
