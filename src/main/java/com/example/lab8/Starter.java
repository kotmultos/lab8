package com.example.lab8;

import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Building;

public class Starter {
    private Thread t;
    StarterRunnable task;
    public void start(Building building, LogCallback logCallback) throws InterruptedException {
        task = new StarterRunnable(building, logCallback);
        t = new Thread(task);
        t.start();
    }

    public void stop() {
        t.stop();
        task.stop();
    }
}
