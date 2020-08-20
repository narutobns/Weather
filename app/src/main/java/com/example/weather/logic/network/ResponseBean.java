package com.example.weather.logic.network;

public class ResponseBean {
    private int id;
    private String name;

    public ResponseBean(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
