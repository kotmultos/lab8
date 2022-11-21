package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@AllArgsConstructor
@Data
public class Room implements Serializable {
    private int doorsCount;
    private int windowsCount;
    private double square;
}
