package com.example.lab8;

import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Building;

public class Starter {
    public void start(Building building, LogCallback logCallback) throws InterruptedException {
        StarterRunnable task = new StarterRunnable(building, logCallback);
        Thread t = new Thread(task);
        t.start();
    }
}
