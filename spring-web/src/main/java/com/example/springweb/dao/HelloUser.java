package com.example.springweb.dao;

import java.io.Serializable;

public class HelloUser implements Serializable {
    private String id;
    private String name;
    private String password;

    public HelloUser(){
        id = null;
        name = null;
        password = null;
    }
    public HelloUser(String id,String name,String password){
        this.id=id;
        this.name=name;
        this.password=password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + password;
    }
}
