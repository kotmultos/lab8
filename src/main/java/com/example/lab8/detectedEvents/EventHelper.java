package com.example.lab8.detectedEvents;

public class EventHelper {
    public static String getDescription(DetectedEventType type) {
        return switch (type) {
            case MovementEvent -> "Виявлено рух";
            case PressureEvent -> "Перевищення тиску";
            case TemperatureEvent -> "Температура за межами норми";
            case SoundEvent -> "Виявлено шум";
            default -> "Вказано помилкове значення. Потрібна перевірка датчиків";
        };
    }
}
