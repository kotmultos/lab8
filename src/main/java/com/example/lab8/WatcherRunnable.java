package com.example.lab8;

import com.example.lab8.detectedEvents.DetectedEvent;
import com.example.lab8.detectedEvents.DetectedEventType;
import com.example.lab8.logs.Log;
import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Building;
import com.example.lab8.models.Floor;
import com.example.lab8.models.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
public class WatcherRunnable implements Runnable{
    private Building building;
    private LogCallback logCallback;

    @Override
    public void run() {
        while (true){
            try {
                Random random = new Random();
                int millis = random.nextInt(1500, 5000);

                Thread.sleep(millis);

                for(Floor floor : building.getFloorList()){
                    scanFloor(floor);
                }
            } catch (InterruptedException e) {
                System.out.println("Exception occurred in method run in WatcherRunnable with message: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    private void scanFloor(Floor floor){
        Random random = new Random();
        for(Room room : floor.getRoomList()){
            List<DetectedEvent> detectedEventList = room.getDetectedEventList();
            for (int i = 0; i < detectedEventList.size(); i++) {
                Log log = null;
                DetectedEvent currentEvent = detectedEventList.get(i);
                if(currentEvent.getType() == DetectedEventType.MovementEvent && (random.nextInt(1, 100) > 45)) {
                    log = new Log("Рух, зафіксований " + currentEvent.getTime() + ", виправлено", LocalDateTime.now());
                } else if(currentEvent.getType() == DetectedEventType.PressureEvent && (random.nextInt(1, 100) > 20)) {
                    log = new Log("Перевищення тиску, зафіксоване " + currentEvent.getTime() + ", виправлено", LocalDateTime.now());
                }  else if(currentEvent.getType() == DetectedEventType.TemperatureEvent && (random.nextInt(1, 100) > 60)) {
                    log = new Log("Перевищення температури, зафіксоване " + currentEvent.getTime() + ", виправлено", LocalDateTime.now());
                } else if(currentEvent.getType() == DetectedEventType.SoundEvent && (random.nextInt(1, 100) > 20)) {
                    log = new Log("Рівень шуму, зафіксований " + currentEvent.getTime() + ", виправлено", LocalDateTime.now());
                }

                if (log != null) {
                    detectedEventList.remove(i);
                    try {
                        logCallback.onLogCreation(log);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }
}
