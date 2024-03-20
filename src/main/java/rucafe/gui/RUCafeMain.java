package rucafe.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RUCafeMain extends Application {
    @Override
    public void start (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RUCafeMain.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        RUCafeMainController mainController = fxmlLoader.getController();
        mainController.setPrimaryStage(stage, scene);
        stage.setTitle("RUCafe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) {
        launch();
    }
}