package rucafe.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class RUCafeMainController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Order currentOrder = new Order();
    private OrderList orderList = new OrderList();

    @FXML
    private ImageView iv_Coffee, iv_Donuts, iv_Sandwich;

    //Image Source: https://lifeboostcoffee.com/blogs/lifeboost/how-many-calories-are-in-a-cup-of-coffee
    private final String COFFEE_BTN_PATH = "coffeeFP.jpg";
    //Image Source: https://tornadoughalli.com/club-sandwich-recipe/
    private final String SANDWICH_BTN_PATH = "sandwichFP.jpg";
    //Image Source: https://www.duckdonuts.com/
    private final String DONUTS_BTN_PATH = "donutsFP.jpg";

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
        iv_Coffee.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(COFFEE_BTN_PATH))));
        iv_Sandwich.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SANDWICH_BTN_PATH))));
        iv_Donuts.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(DONUTS_BTN_PATH))));
    }


    @FXML
    protected void displayCoffeeView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffeeView.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            CoffeeController coffeeController = loader.getController();
            coffeeController.setPrimaryStage(primaryStage, primaryScene);
            coffeeController.setCurrentOrder(currentOrder);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayCurrentOrderView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("currentOrderView.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            CurrentOrderController currentOrderController = loader.getController();
            currentOrderController.setPrimaryStage(primaryStage, primaryScene);
            currentOrderController.setCurrentOrder(currentOrder, orderList);


        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displaySandwichView(){

        String SandwichFXMLFilePath = "sandwichView.fxml";
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(SandwichFXMLFilePath));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);

            SandwichController sc = loader.getController();
            sc.setPrimaryStage(primaryStage, primaryScene);
            sc.setCurrentOrder(currentOrder);
        }
        catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Issue loading Sandwich FXML File");
            alert.setContentText("Could not load or read from " + SandwichFXMLFilePath);
            alert.showAndWait();
        }
    }


    @FXML
    protected void displayOrderListView() {
        if (orderList.isEmpty()) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("No orders");
            warning.setContentText("No orders to show");
            warning.showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersView.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            OrderListController orderListController = loader.getController();
            orderListController.setPrimaryStage(primaryStage, primaryScene);
            orderListController.setOrderList(orderList);


        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }
}