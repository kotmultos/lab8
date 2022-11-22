package com.example.lab8.models;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class Building implements Serializable {

    public Building() {
        floorList = new ArrayList<Floor>();
    }
    List<Floor> floorList;
    public void addFloor(@NonNull Floor floor) {
        floorList.add(floor);
    }
}
