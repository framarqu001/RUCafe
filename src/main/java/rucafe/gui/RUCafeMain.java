package rucafe.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Launcher/Main class for the RU Cafe Program, loads/sets up root resources/window for program to function.
 *
 * @author Francisco Marquez
 */
public class RUCafeMain extends Application {

    /**
     * Start method for RUCafe program, loads the main page as well as creates the window and its child components
     * needed in order for the software to run.
     * @param stage the main window of the program
     * @throws IOException if there is an issue reading/locating any resource files.
     */
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

    /**
     * Main method/launcher for program
     * @param args the command line arguments.
     */
    public static void main (String[] args) {
        launch();
    }
}