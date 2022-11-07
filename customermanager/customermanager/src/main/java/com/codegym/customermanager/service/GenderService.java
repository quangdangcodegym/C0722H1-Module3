package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Gender;

import java.util.ArrayList;

public class GenderService {
    private ArrayList<Gender> list;

    public GenderService() {
        list = new ArrayList<>();
        list.add(new Gender(1, "Nam"));
        list.add(new Gender(2, "Nu"));
    }

    public ArrayList<Gender> getList() {
        return list;
    }

    public void setList(ArrayList<Gender> list) {
        this.list = list;
    }
}
