package com.example.lab8.strategies;

import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Room;

public abstract class Strategy {
    public abstract void Scan(Room room, LogCallback logCallback, String floorName);
}
