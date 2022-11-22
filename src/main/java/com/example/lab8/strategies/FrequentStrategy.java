package com.example.lab8.strategies;

import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Room;

public class FrequentStrategy extends Strategy {
    @Override
    public void scan(Room room, LogCallback logCallback, String floorName) {
        generateMovementEvent(room, logCallback, floorName);
        generatePressureEvent(room, logCallback, floorName);
        generateTemperatureEvent(room, logCallback, floorName);
    }

    @Override
    protected void generateMovementEvent(Room room, LogCallback logCallback, String floorName) {

    }

    @Override
    protected void generatePressureEvent(Room room, LogCallback logCallback, String floorName) {

    }

    @Override
    protected void generateTemperatureEvent(Room room, LogCallback logCallback, String floorName) {

    }
}