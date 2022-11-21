package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class Building implements Serializable {
    List<Floor> floorList;
}
