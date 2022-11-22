package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class Floor implements Serializable {
    private  String name;
    public Floor (String name) {
        this.name = name;
        roomList = new ArrayList<Room>();
    }

    public void addRoom(@NonNull Room room) {
        roomList.add(room);
    }
    List<Room> roomList;
}
