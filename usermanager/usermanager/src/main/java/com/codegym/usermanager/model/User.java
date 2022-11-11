package com.codegym.usermanager.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class User {
    protected int id;
    protected String name;
    protected String email;
    protected int idCountry;

    public User() {}

    public User(String name, String email, int idCountry) {
        super();
        this.name = name;
        this.email = email;
        this.idCountry = idCountry;
    }

    public User(int id, String name, String email, int idCountry) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.idCountry = idCountry;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty(message = "Tên không được để trống")
    @Length(max = 15, message = "Tên tối đa 15 kí tự")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Email(regexp = "^[A-Za-z0-9._]+@[a-z]+\\.[a-z]{2,3}$", message = "Email không hợp le")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getIdCountry() {
        return idCountry;
    }
    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }
}