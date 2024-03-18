module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens rucafe.gui to javafx.fxml;
    exports rucafe.gui;
}