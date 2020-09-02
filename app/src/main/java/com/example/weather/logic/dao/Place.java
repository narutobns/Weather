package com.example.weather.logic.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Place {
    @PrimaryKey
    public int pid;

    @ColumnInfo(name = "place_name")
    public String placeName;

    @ColumnInfo(name = "address")
    public String address;
}
