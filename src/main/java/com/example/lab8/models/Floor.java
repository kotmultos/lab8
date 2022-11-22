package com.example.lab8.models;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class Floor implements Serializable {
    private  String name;
    public String getName() {return name;}
    public Floor (String name) {
        this.name = name;
        roomList = new ArrayList<Room>();
    }

    public void addRoom(@NonNull Room room) {
        roomList.add(room);
    }
    List<Room> roomList;
}
