package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@AllArgsConstructor
@Data
public class Room implements Serializable {
    private String name;
    private int doorsCount;
    private int windowsCount;
    private double square;

    public Room(String name) {this.name = name; }

    public Room(int doorsCount, int windowsCount, double square) {
        this.doorsCount = doorsCount;
        this.windowsCount = windowsCount;
        this.square = square;
    }
}
