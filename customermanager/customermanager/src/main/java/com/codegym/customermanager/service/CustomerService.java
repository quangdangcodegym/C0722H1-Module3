package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Customer;

import java.util.ArrayList;
import java.util.Date;

public class CustomerService {
    private ArrayList<Customer> list;
    public CustomerService() {
        //Customer(String name, Date dayOfBirth, String address, String image
        list = new ArrayList<>();
        Date date = new Date();
        list.add(new Customer(6,"Dang Van Quang",date, "28 Nguyen Tri Phuong", null , 1));
        list.add(new Customer(9,"Nguyen Thi Thanh Hoan",date,"28 Nguyen Tri Phuong", null ,2));
        list.add(new Customer(2,"Dang Phuoc Duy",date, "28 Nguyen Tri Phuong", null,1 ));

    }

    public ArrayList<Customer> getList() {
        return list;
    }

    public void setList(ArrayList<Customer> list) {
        this.list = list;
    }
}
