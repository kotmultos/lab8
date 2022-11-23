package com.example.lab8.controllers;

import com.example.lab8.Starter;
import com.example.lab8.StarterRunnable;
import com.example.lab8.detectedEvents.DetectedEvent;
import com.example.lab8.detectedEvents.DetectedEventType;
import com.example.lab8.detectedEvents.EventHelper;
import com.example.lab8.logs.Log;
import com.example.lab8.logs.LogCallback;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.example.lab8.models.*;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
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
    private TreeTableColumn<Object, Object> StructureTableColumn;
    @FXML
    private TreeTableColumn<Object, Object> DoorsTableColumn;
    @FXML
    private TreeTableColumn<Object, Object> WindowsTableColumn;
    @FXML
    private TreeTableColumn<Object, Object> SquareTableColumn;
    @FXML
    private TableView LogTableView;
    @FXML
    private TableColumn TimeTableColumn;
    @FXML
    private TableColumn DescriptionTableColumn;

    @FXML
    private TextField RoomDoorsInput;
    @FXML
    private TextField RoomsWindowsInput;
    @FXML
    private TextField RoomSquareInput;
    @FXML
    private ComboBox<Floor> FloorComboBox;
    @FXML
    private ComboBox<Room> RoomComboBox;
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

    private ObservableList<Log> logsList = FXCollections.observableArrayList();

    private boolean isSimulationStarted;

    private Starter starter;

    private DetectedEventType chosenEventType = DetectedEventType.TemperatureEvent;

    private LogCallback callback;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        building = new Building();
        Room room = new Room("Кімната 1", 2, 4, 5.5);
        Floor floor = new Floor("Поверх 1");
        floor.addRoom(room);
        building.addFloor(floor);

        TimeTableColumn.setCellValueFactory(new PropertyValueFactory<Log, LocalDateTime>("time"));
        DescriptionTableColumn.setCellValueFactory(new PropertyValueFactory<Log, String>("message"));

        LogTableView.setItems(logsList);

        isSimulationStarted = false;

        StructureTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        DoorsTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("doorsCount"));
        WindowsTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("windowsCount"));
        SquareTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("square"));
        BuildingStructureTableView.setRoot(new TreeItem(new Room("Будівля")));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            CurrentTimeLabel.setText(currentTime.format(formatter));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

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

        RoomComboBox.setConverter(new StringConverter<Room>() {
            @Override
            public String toString(Room object) {
                return object.getName();
            }

            @Override
            public Room fromString(String string) {
                return null;
            }
        });

        displayBuilding();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("logs.txt");
            callback = new LogCallback() {
                @Override
                public synchronized void onLogCreation(Log log) {
                    try {
                        fileOutputStream.write((log.toString()).getBytes());
                    } catch (IOException e){
                        System.out.println("IoException in SecurityApplicationController.onStartSimulationButtonClick(): " + e.getMessage());
                    } catch (Exception e){
                        System.out.println("Exception in SecurityApplicationController.onStartSimulationButtonClick(): " + e.getMessage());
                    }

                    logsList.add(0, log);
                }
            };
        } catch (Exception e) {
            System.out.println("Exception in initialize(): " + e.getMessage());
        }
    }

    private void setFloorsToComboBox() {
        FloorComboBox.setItems(FXCollections.observableArrayList(building.getFloorList()));
    }

    protected void displayBuilding () {
        TreeItem<Room> root = BuildingStructureTableView.getRoot();
        root.getChildren().clear();
        root.setExpanded(true);
        for (Floor floor: building.getFloorList()) {
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

            if(doors > 0 && doors < 15 && windows > 0 && windows < 20 && square >= 0.01) {
                if (((Room) ((TreeItem) BuildingStructureTableView.getSelectionModel().getSelectedItem()).getValue()).getName().contains("Поверх")) {
                    var selectedItem = (TreeItem) (BuildingStructureTableView.getSelectionModel().getSelectedItem());

                    var index = selectedItem.getParent().getChildren().indexOf(selectedItem);
                    System.out.println(index);

                    Floor selectedFloor = building.getFloorList().get(index);
                    Room newRoom = new Room("Кімната " + (selectedFloor.getRoomList().size() + 1), doors, windows, square);
                    selectedFloor.addRoom(newRoom);

                    displayBuilding();
                }
            } else {
                System.out.println("Введено недопустимі значення для інформації про кімнату");
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
        if (!isSimulationStarted) {
            TreeItem<Room> root = BuildingStructureTableView.getRoot();
            root.getChildren().clear();
            root.setExpanded(true);
            LogTableView.getItems().clear();
            building = new Building();

            FloorComboBox.getItems().clear();
            RoomComboBox.getItems().clear();
        }
    }

    public void onExitMenuItemAction(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) window.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void onLoadMenuItemAction(ActionEvent actionEvent) {
        if(!isSimulationStarted){
            try {
                FileInputStream fileInputStream = new FileInputStream("recentBuildingData.ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                Building newBuilding = (Building) objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();
                FloorComboBox.getItems().clear();
                RoomComboBox.getItems().clear();

                building = newBuilding;

                LogTableView.getItems().clear();

                displayBuilding();
            } catch (FileNotFoundException e) {
                System.out.println("Cannot find file: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Building not found: " + e.getMessage());
            }
        } else {
            System.out.println("Під ча симуляції не можна змінити конфігурацію системи.");
        }
    }

    public synchronized void onSaveMenuItemAction(ActionEvent actionEvent) {
        if (building.getFloorList().size() != 0) {
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

    public void onFloorComboBoxItemSelected(ActionEvent actionEvent) {
        Floor selectedFloor = FloorComboBox.getSelectionModel().getSelectedItem();
        if (selectedFloor != null) {
            RoomComboBox.setItems(FXCollections.observableArrayList(selectedFloor.getRoomList()));
        } else {
            RoomComboBox.getItems().clear();
        }
    }

    public void onStartSimulationButtonClick(MouseEvent mouseEvent) throws FileNotFoundException {
        if (!isSimulationStarted) {
            isSimulationStarted = true;

            starter = new Starter();
            try {
                starter.start(building, callback);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception in SecurityApplicationController.onStartSimulationButtonClick(): " + e.getMessage());
            }
        }
    }

    public void onStopSimulationButtonClick(MouseEvent mouseEvent) {
        if(isSimulationStarted) {
            starter.stop();
            isSimulationStarted = false;
        }
    }

    public void onTempeatureRadioButtonAction(ActionEvent actionEvent) {
        chosenEventType = DetectedEventType.TemperatureEvent;
    }

    public void onPressureRadioButtonAction(ActionEvent actionEvent) {
        chosenEventType = DetectedEventType.PressureEvent;
    }

    public void onSoundRadioButtonAction(ActionEvent actionEvent) {
        chosenEventType = DetectedEventType.SoundEvent;
    }

    public void onMovementRadioButtonAction(ActionEvent actionEvent) {
        chosenEventType = DetectedEventType.MovementEvent;
    }

    public void onCreateSituationButtonClicked(MouseEvent mouseEvent) {
        try {
            Floor selectedFloor = FloorComboBox.getSelectionModel().getSelectedItem();

            Room room = RoomComboBox.getSelectionModel().getSelectedItem();

            DetectedEvent detectedEvent = new DetectedEvent(selectedFloor.getName() + " " + room.getName() + " : "
                    + EventHelper.getDescription(chosenEventType), LocalDateTime.now(), chosenEventType);
            room.getDetectedEventList().add(detectedEvent);
            try {
                callback.onLogCreation(new Log(detectedEvent.getMessage(), LocalDateTime.now()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        catch (Exception e) {
            System.out.println("Exception in onCreateSituationButtonClicked():" + e.getMessage());
        }
    }
}