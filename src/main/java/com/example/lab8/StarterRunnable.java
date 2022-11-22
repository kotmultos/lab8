package com.example.lab8;

import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Building;
import com.example.lab8.strategies.StrategyRunnable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StarterRunnable implements Runnable {
    private Building building;
    private LogCallback logCallback;
    public StarterRunnable(Building building, LogCallback logCallback) {
        this.building = building;
        this.logCallback = logCallback;
    }
    private Thread twr ;
    private Thread tsr;

    @Override
    public void run() {
        WatcherRunnable wr = new WatcherRunnable(building, logCallback);
        StrategyRunnable sr = new StrategyRunnable(building, logCallback);
        twr = new Thread(wr);
        tsr = new Thread(sr);
        try {
            twr.start();
            tsr.start();
            twr.join();
            tsr.join();
        } catch (InterruptedException e) {
            System.out.println("Exception occurred in method run in StarterRunnable: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void stop () {
        twr.stop();
        tsr.stop();
    }
}
