package com.example.lab8.detectedEvents;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class DetectedEvent implements Serializable {
    private String message;
    private LocalDateTime time;
    private DetectedEventType type;
}
