package com.example.lab8.detectedEvents;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Data
public class DetectedEvent implements Serializable {
    private String message;
    private LocalDateTime time;
    private DetectedEventType type;

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return time.format(formatter);
    }
    public LocalDateTime getTimeLocalDateTime() {
        return time;
    }

    @Override
    public String toString() {
        return message;
    }
}
