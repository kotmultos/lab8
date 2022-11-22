package com.example.lab8.models;

import com.example.lab8.detectedEvents.DetectedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Room implements Serializable {
    private String name;
    private Integer doorsCount;
    private Integer windowsCount;
    private Double square;

    public Room(String name) {this.name = name; }

    public Room(String name, int doorsCount, int windowsCount, double square) {
        this.name = name;
        this.doorsCount = doorsCount;
        this.windowsCount = windowsCount;
        this.square = square;
        movementSensorsCount = doorsCount + windowsCount;
        pressureSensorsCount = 2 * (doorsCount + windowsCount);
        temperatureSensorsCount = (int)square / 30 + 1;
        soundSensorsCount = 4;
        detectedEventList = new ArrayList<>();
    }

    private Integer pressureSensorsCount;
    private Integer temperatureSensorsCount;
    private Integer movementSensorsCount;
    private Integer soundSensorsCount;

    private List<DetectedEvent> detectedEventList;
}
