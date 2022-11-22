package com.example.lab8.strategies;

import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Building;
import com.example.lab8.models.Floor;
import com.example.lab8.models.Room;
import lombok.Data;

import java.util.Random;

@Data
public class StrategyRunnable implements Runnable{
    private LogCallback logCallback;
    private Building building;
    @Override
    public void run() {
        while (true){
            try {
                Random random = new Random();
                int millis = random.nextInt(1500, 5000);

                Thread.sleep(millis);

                for (Floor floor: building.getFloorList()) {
                    for (Room room: floor.getRoomList()) {
                        Strategy strategy = StrategyFactory.getStrategy(room);
                        strategy.scan(room, logCallback, floor.getName());
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Exception occurred in method run in StrategyRunnable: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
