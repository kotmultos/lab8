package com.example.lab8;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import models.Building;

import java.net.URL;
import java.time.LocalDate;
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
    private TreeTableView BuildinfStructureTableView;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        building = new Building();

        System.out.println("initialization");
    }

    public void onAddFloorClick() {
        System.out.println("clicked");
    }
}