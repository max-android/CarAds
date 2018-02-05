package com.example.carads.storage.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Максим on 21.11.2017.
 */


//@Entity(tableName = "car")
@Entity(tableName = "car")
public class Car implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;


    private String name;
    private String image;
    private String date_issue;
    private String mileage;
    private String color;
    private int price;
    private Double valume;
    private int power;
    private String owner;
    private String phone;
    private String mail;
    private String address;


    public Car(String name, String image, String date_issue, String mileage, String color, int price, Double  valume, int power, String owner, String phone, String mail, String address) {
        this.name = name;
        this.image = image;
        this.date_issue = date_issue;
        this.mileage = mileage;
        this.color = color;
        this.price = price;
        this.valume = valume;
        this.power = power;
        this.owner = owner;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDate_issue() {
        return date_issue;
    }

    public String getMileage() {
        return mileage;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public Double getValume() {
        return valume;
    }

    public int getPower() {
        return power;
    }

    public String getOwner() {
        return owner;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate_issue(String date_issue) {
        this.date_issue = date_issue;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setValume(Double valume) {
        this.valume = valume;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
