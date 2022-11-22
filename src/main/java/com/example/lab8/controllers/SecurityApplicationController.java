package com.example.lab8.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.example.lab8.models.*;
import javafx.util.StringConverter;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class SecurityApplicationController implements Initializable {

    @FXML
    private VBox window;
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
    private ComboBox<Floor> FloorComboBox;
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

//
        FloorComboBox.setConverter(new StringConverter<Floor>() {
            @Override
            public String toString(Floor object) {
                return object.getName();
            }

            @Override
            public Floor fromString(String string) {
                return null;
            }
        });
        displayBuilding();
    }

    private void setFloorsToComboBox() {
//        for (Floor floor: building.getFloorList()) {
//            FloorComboBox.setItems((ObservableList<Floor>) building.getFloorList());
            FloorComboBox.setItems(FXCollections.observableArrayList(building.getFloorList()));
//        }
    }



    protected void displayBuilding () {
        System.out.println(building.toString());
        TreeItem<Room> root = BuildingStructureTableView.getRoot();
        root.getChildren().clear();
        root.setExpanded(true);
        for (Floor floor: building.getFloorList()) {
            System.out.println(floor.toString());
            TreeItem newItem = new TreeItem<>(new Room(floor.getName()));

            for (Room room: floor.getRoomList()) {
                newItem.getChildren().add(new TreeItem<>(room));
            }
            newItem.setExpanded(true);
            root.getChildren().add(newItem);
        }

        BuildingStructureTableView.setRoot(root);
        setFloorsToComboBox();
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

    public void onExitMenuItemAction(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) window.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void onLoadMenuItemAction(ActionEvent actionEvent) {

        try {
            FileInputStream fileInputStream = new FileInputStream("recentBuildingData.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Building newBuilding = (Building) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            System.out.println(newBuilding);
            building = newBuilding;
            displayBuilding();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Building not found: " + e.getMessage());
        }
    }

    public synchronized void onSaveMenuItemAction(ActionEvent actionEvent) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("recentBuildingData.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(building);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}