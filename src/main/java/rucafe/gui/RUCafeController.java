package rucafe.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RUCafeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick () {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}