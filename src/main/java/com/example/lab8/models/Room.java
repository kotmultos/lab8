package com.example.lab8.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class Room implements Serializable {
    private String name;
    private Integer doorsCount;
    private Integer windowsCount;
    private Double square;

    public Room(String name) {this.name = name; }

    public Room(int doorsCount, int windowsCount, double square) {
        this.doorsCount = doorsCount;
        this.windowsCount = windowsCount;
        this.square = square;
    }
}
