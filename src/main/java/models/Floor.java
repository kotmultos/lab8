package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class Floor implements Serializable {
    public Floor () {
        roomList = new ArrayList<Room>();
    }

    public void addRoom(@NonNull Room room) {
        roomList.add(room);
    }
    List<Room> roomList;
}
