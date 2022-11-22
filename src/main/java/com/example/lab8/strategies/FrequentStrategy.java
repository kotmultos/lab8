package com.example.lab8.strategies;

import com.example.lab8.detectedEvents.DetectedEvent;
import com.example.lab8.detectedEvents.DetectedEventType;
import com.example.lab8.logs.Log;
import com.example.lab8.logs.LogCallback;
import com.example.lab8.models.Room;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class FrequentStrategy extends Strategy {
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
        if(number >= 1 && number <= 400){
            DetectedEvent detectedEvent = new DetectedEvent(floorName + " " + room.getName() + " : Виявлено рух",
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
        if(number >= 1 && number <= 400){
            DetectedEvent detectedEvent = new DetectedEvent(floorName + " " + room.getName() + " : Перевищення тиску",
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
        if(number >= 1 && number <= 400){
            DetectedEvent detectedEvent = new DetectedEvent(floorName + " " + room.getName() + " : Температура за межами норми",
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
        if(number >= 1 && number <= 400){
            DetectedEvent detectedEvent = new DetectedEvent(floorName + " " + room.getName() + " : Виявлено шум",
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