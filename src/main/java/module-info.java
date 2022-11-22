module com.example.lab8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires lombok;

    opens com.example.lab8 to javafx.fxml;
    exports com.example.lab8;

    opens com.example.lab8.controllers;
    exports com.example.lab8.controllers;

    opens com.example.lab8.models;
    exports com.example.lab8.models;

    opens com.example.lab8.logs;
    exports com.example.lab8.logs;

    opens com.example.lab8.detectedEvents;
    exports com.example.lab8.detectedEvents;

    opens com.example.lab8.strategies;
    exports com.example.lab8.strategies;
}