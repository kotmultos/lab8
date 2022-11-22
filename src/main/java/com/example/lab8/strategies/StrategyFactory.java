package com.example.lab8.strategies;

import com.example.lab8.models.Room;

public class StrategyFactory {
    public Strategy getStrategy(Room room) {
        var sum = room.getWindowsCount() * room.getDoorsCount() + room.getSquare();

        if (sum <= 10) return new RareStrategy();
        else if (sum > 10 && sum <= 30) return new DefaultStrategy();
        else return new FrequentStrategy();
    }
}
