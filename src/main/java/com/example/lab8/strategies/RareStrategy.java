package com.example.lab8.strategies;

import com.example.lab8.detectedEvents.DetectedEvent;
import com.example.lab8.detectedEvents.DetectedEventType;
import com.example.lab8.detectedEvents.EventHelper;
import com.example.lab8.logs.Log;
import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Room;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class RareStrategy extends Strategy{
    @Override
    public void scan(Room room, LogCallback logCallback, String floorName) {
        generateMovementEvent(room, logCallback, floorName);
        generatePressureEvent(room, logCallback, floorName);
        generateTemperatureEvent(room, logCallback, floorName);
        generateSoundEvent(room, logCallback, floorName);
    }

    @Override
    protected void generateMovementEvent(Room room, LogCallback logCallback, String floorName) {
        Random random = new Random();
        int number = random.nextInt(1, 1000);
        if(number >= 1 && number <= 100){
            DetectedEvent detectedEvent = new DetectedEvent(floorName + " " + room.getName() + " : " +
                    EventHelper.getDescription(DetectedEventType.MovementEvent),
                    LocalDateTime.now(), DetectedEventType.MovementEvent);
            room.getDetectedEventList().add(detectedEvent);
            try {
                logCallback.onLogCreation(new Log(detectedEvent.getMessage(), LocalDateTime.now()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void generatePressureEvent(Room room, LogCallback logCallback, String floorName) {
        Random random = new Random();
        int number = random.nextInt(1, 1000);
        if(number >= 1 && number <= 100){
            DetectedEvent detectedEvent = new DetectedEvent(floorName + " " + room.getName() + " : " +
                    EventHelper.getDescription(DetectedEventType.PressureEvent),
                    LocalDateTime.now(), DetectedEventType.PressureEvent);
            room.getDetectedEventList().add(detectedEvent);
            try {
                logCallback.onLogCreation(new Log(detectedEvent.getMessage(), LocalDateTime.now()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void generateTemperatureEvent(Room room, LogCallback logCallback, String floorName) {
        Random random = new Random();
        int number = random.nextInt(1, 1000);
        if(number >= 1 && number <= 100){
            DetectedEvent detectedEvent = new DetectedEvent(floorName + " " + room.getName() + " : " +
                    EventHelper.getDescription(DetectedEventType.TemperatureEvent),
                    LocalDateTime.now(), DetectedEventType.TemperatureEvent);
            room.getDetectedEventList().add(detectedEvent);
            try {
                logCallback.onLogCreation(new Log(detectedEvent.getMessage(), LocalDateTime.now()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void generateSoundEvent(Room room, LogCallback logCallback, String floorName) {
        Random random = new Random();
        int number = random.nextInt(1, 1000);
        if(number >= 1 && number <= 100){
            DetectedEvent detectedEvent = new DetectedEvent(floorName + " " + room.getName() + " : " +
                    EventHelper.getDescription(DetectedEventType.SoundEvent),
                    LocalDateTime.now(), DetectedEventType.SoundEvent);
            room.getDetectedEventList().add(detectedEvent);
            try {
                logCallback.onLogCreation(new Log(detectedEvent.getMessage(), LocalDateTime.now()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
