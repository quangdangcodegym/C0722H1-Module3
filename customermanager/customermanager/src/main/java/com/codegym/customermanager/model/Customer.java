package com.codegym.customermanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Customer {
    private int id;
    private String name;
    private Date dayOfBirth;
    private String address;
    private String image;

    private int gender;

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer(int id, String name, Date dayOfBirth, String address, String image, int gender) {
        this.id = id;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.address = address;
        this.image = image;
        this.gender = gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Customer(String name, Date dayOfBirth, String address, String image, int gender) {
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.address = address;
        this.image = image;
        this.gender = gender;
    }

    public Customer(String name, Date dayOfBirth, String address, String image) {
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.address = address;
        this.image = image;
    }

    public Customer(String name, Date dayOfBirth, String address) {
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
