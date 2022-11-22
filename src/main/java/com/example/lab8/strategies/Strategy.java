package com.example.lab8.strategies;

import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Room;

public abstract class Strategy {
    public abstract void scan(Room room, LogCallback logCallback, String floorName);
    protected abstract void generateMovementEvent(Room room, LogCallback logCallback, String floorName);
    protected abstract void generatePressureEvent(Room room, LogCallback logCallback, String floorName);
    protected abstract void generateTemperatureEvent(Room room, LogCallback logCallback, String floorName);
}
