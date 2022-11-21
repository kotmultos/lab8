package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
public class Floor implements Serializable {
    List<Room> roomList;
}
