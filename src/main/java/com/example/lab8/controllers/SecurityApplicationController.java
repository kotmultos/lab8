package com.example.lab8.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private TreeTableView<Room> BuildingStructureTableView;
    @FXML
    private TreeTableView LogTableView;
    @FXML
    private TreeTableColumn<Object, Object> StructureTableColumn;
    @FXML
    private TreeTableColumn<Object, Object> DoorsTableColumn;
    @FXML
    private TreeTableColumn<Object, Object> WindowsTableColumn;
    @FXML
    private TreeTableColumn<Object, Object> SquareTableColumn;
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
        Room room = new Room("Кімната 1", 2, 4, 5.5);
        Floor floor = new Floor("Поверх 1");
        floor.addRoom(room);
        building.addFloor(floor);
        currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        CurrentTimeLabel.setText(currentTime.format(formatter));
        System.out.println("initialization");


        StructureTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        DoorsTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("doorsCount"));
        WindowsTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("windowsCount"));
        SquareTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("square"));
        BuildingStructureTableView.setRoot(new TreeItem(new Room("Будівля")));
        displayBuilding();
    }

    protected void displayBuilding () {
        System.out.println(building.toString());
        TreeItem<Room> root = BuildingStructureTableView.getRoot();
        root.getChildren().clear();
        root.setExpanded(true);
        for (Floor floor: building.getFloorList()) {
//            var item = root.getChildren();
            System.out.println(floor.toString());
            TreeItem newItem = new TreeItem<>(new Room(floor.getName()));

            for (Room room: floor.getRoomList()) {
                newItem.getChildren().add(new TreeItem<>(room));
//                System.out.println(room.toString());
            }
            newItem.setExpanded(true);
//            root.getChildren().add(new TreeItem<>(new Room(floor.getName())));
            root.getChildren().add(newItem);
        }

        BuildingStructureTableView.setRoot(root);

    }

    @FXML
    protected void onAddFloorButtonClicked () {
        try {
            building.addFloor(new Floor("Поверх " + (building.getFloorList().size() + 1)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        displayBuilding();

    }
    @FXML
    public void onAddRoomButtonClick () {
        try {
            Integer doors = Integer.valueOf(RoomDoorsInput.getText());
            Integer windows = Integer.valueOf(RoomsWindowsInput.getText());
            Double square = Double.valueOf(RoomSquareInput.getText());

            if (((Room) ((TreeItem) BuildingStructureTableView.getSelectionModel().getSelectedItem()).getValue()).getName().contains("Поверх")) {
                var selectedItem = (TreeItem) (BuildingStructureTableView.getSelectionModel().getSelectedItem());

                var index = selectedItem.getParent().getChildren().indexOf(selectedItem);
                System.out.println(index);

                Floor selectedFloor = building.getFloorList().get(index);
                Room newRoom = new Room("Кімната " + (selectedFloor.getRoomList().size() + 1), doors, windows, square);
                selectedFloor.addRoom(newRoom);

                System.out.println(doors);
                System.out.println(windows);
                System.out.println(square);

                displayBuilding();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            RoomDoorsInput.setText("");
            RoomsWindowsInput.setText("");
            RoomSquareInput.setText("");
        }
    }

    public void onClearMenuItemAction(ActionEvent actionEvent) {
        TreeItem<Room> root = BuildingStructureTableView.getRoot();
        root.getChildren().clear();
        root.setExpanded(true);
        building = new Building();
    }
}