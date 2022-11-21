package com.example.lab8.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import models.Building;
import models.Floor;
import models.Room;

import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SecurityApplicationController implements Initializable {
   @FXML
   private MenuItem LoadMenuItem;
   @FXML
   private MenuItem SaveMenuItem;
   @FXML
   private MenuItem ClearMenuitem;
   @FXML
   private MenuItem ExitMenuItem;
   @FXML
   private MenuItem AboutMenuItem;
   @FXML
   private Button AddFloorButton;
    @FXML
    private Button AddRoomButton;
    @FXML
    private Button CreateSituationButton;
    @FXML
    private TreeTableView BuildingStructureTableView;
    @FXML
    private TreeTableView LogTableView;
    @FXML
    private TreeTableColumn StructureTableColumn;
    @FXML
    private TreeTableColumn DoorsTableColumn;
    @FXML
    private TreeTableColumn WindowsTableColumn;
    @FXML
    private TreeTableColumn SquareTableColumn;
    @FXML
    private TreeTableColumn TimeTableColumn;
    @FXML
    private TreeTableColumn DescriptionTableColumn;
    @FXML
    private TextField RoomDoorsInput;
    @FXML
    private TextField RoomsWindowsInput;
    @FXML
    private TextField RoomSquareInput;
    @FXML
    private ComboBox FloorComboBox;
    @FXML
    private ComboBox RoomComboBox;
    @FXML
    private RadioButton TemperatureRadioButton;
    @FXML
    private RadioButton PressureRadioButton;
    @FXML
    private RadioButton MovementRadioButton;
    @FXML
    private Label CurrentTimeLabel;

    private Building building;

    private LocalDateTime currentTime;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        building = new Building();
        Room room = new Room(2, 4, 5);
        Floor floor = new Floor();
        floor.addRoom(room);
        building.addFloor(floor);
        currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        CurrentTimeLabel.setText(currentTime.format(formatter));
        System.out.println("initialization");

//        try {
//            StructureTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("Структура"));
//            BuildingStructureTableView.setRoot(new TreeItem<>(new Room("hello")));
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

    }

    @FXML
    protected void onAddFloorButtonClicked () {
        try {
            building.addFloor(new Floor());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(building.toString());
//
//        if (BuildingStructureTableView.getRoot() != null) {
//
//        } else {
//            BuildingStructureTableView.add()
//        }


    }
}