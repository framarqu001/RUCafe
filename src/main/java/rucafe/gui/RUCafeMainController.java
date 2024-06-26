package rucafe.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Primary controller for the RU Cafe main view, allows users to navigate to the sandwich/coffee/sandwich order page,
 * the current order page and the list of all orders page.
 *
 * @author Ashley Berlinski, Francisco Marquez
 */
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

    /**
     * Sets the references to the primary stage/scene.
     * This allows for navigation between the views.
     * @param stage reference to the primary stage.
     * @param scene reference to the primary scene.
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
        iv_Coffee.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(COFFEE_BTN_PATH))));
        iv_Sandwich.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SANDWICH_BTN_PATH))));
        iv_Donuts.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(DONUTS_BTN_PATH))));
    }


    /**
     * Try's to load coffeeView and set the scene. Catches an IOException if the view could not be loaded.
     * Passes references to the current order and the primary stage/scene.
     */
    @FXML
    public void displayCoffeeView() {
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

    /**
     * Try's to load currentOrderView and set the scene. Catches an IOException if the view could not be loaded.
     * Passes references to the current order, orderList and the primary stage/scene.
     */
    @FXML
    public void displayCurrentOrderView() {
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

    /**
     * Try's to load sandwichView and set the scene. Catches an IOException if the view could not be loaded.
     * Passes references to the current order and the primary stage/scene.
     */
    @FXML
    public void displaySandwichView(){

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


    /**
     * Try's to load donutView and set the scene. Catches an IOException if the view could not be loaded.
     * Passes references to the current order and the primary stage/scene.
     */
    @FXML
    public void displayDonutView(){

        String DonutFXMLFilePath = "donutView.fxml";
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DonutFXMLFilePath));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);

            DonutController dc = loader.getController();
            dc.setPrimaryStage(primaryStage, primaryScene);
            dc.setCurrentOrder(currentOrder);
        }
        catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Issue loading Donut FXML File");
            alert.setContentText("Could not load or read from " + DonutFXMLFilePath);
            alert.showAndWait();
        }
    }


    /**
     * Try's to load ordersView and set the scene. Catches an IOException if the view could not be loaded.
     * Passes references to orderList and the primary stage/scene.
     * Does not allow the user to navigate to the scene if no orders have been placed.
     */
    @FXML
    public void displayOrderListView() {
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