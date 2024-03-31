package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Optional;

/**
 * This controller handles all the view logic and synchronization of the CoffeeView.
 * A coffee object is tied to this controller and is used for synchronization as the user
 * navigates the gui.
 *
 * @author Francisco Marquez
 */
public class CoffeeController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Coffee coffeeOrder = new Coffee();
    private Order currentOrder;

    @FXML
    private CheckBox cb_sweetCream, cb_mocha, cb_frenchVanilla, cb_caramel, cb_IrishCream;

    @FXML
    private RadioButton rb_large, rb_medium, rb_small, rb_venti;

    @FXML
    private TextField tf_total;

    @FXML
    private ToggleGroup size;

    @FXML
    private ChoiceBox<Integer> cb_quantity;


    /**
     * Adds a coffee object to the Order.
     * Confirms with the user that they want to place the order with an alert.
     */
    @FXML
    void addToOrder() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        alert.setTitle("Order confirmation");
        alert.setHeaderText("");
        alert.setContentText("Do you want to place this order?");
        Optional<ButtonType> button = alert.showAndWait();
        if (button.get() == ButtonType.OK) {
            Coffee copy = new Coffee(coffeeOrder);
            currentOrder.addToOrder(copy);
            Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmAlert.setTitle("Order Status");
            confirmAlert.setHeaderText("");
            confirmAlert.setContentText("Coffee has been added to cart");
            confirmAlert.showAndWait();
            primaryStage.setScene(primaryScene);
        }
    }

    /**
     * Prepares the coffeeView to be shown to the user.
     * Binds text field value with coffee price string property, and sets choice box with values (1,2,3,4,5)
     * When radio buttons from the toggle group 'size' are selected. The controller's coffee Object
     * size also changes. The same is true with the choice box 'quantity'.
     */
    public void initialize() {
        rb_small.setSelected(true);
        tf_total.textProperty().bind(coffeeOrder.priceStringProperty()); // textbox binded to Coffee priceString.
        size.selectedToggleProperty().addListener((observable) -> {
            RadioButton currentButton = (RadioButton) size.getSelectedToggle();
            if (currentButton == rb_small) {
                coffeeOrder.setSize(Coffee.Size.SHORT);
            } else if (currentButton == rb_medium) {
                coffeeOrder.setSize(Coffee.Size.TALL);
            } else if (currentButton == rb_large) {
                coffeeOrder.setSize(Coffee.Size.GRANDE);
            } else if (currentButton == rb_venti) {
                coffeeOrder.setSize(Coffee.Size.VENTI);
            }
        });

        ObservableList<Integer> quantities = FXCollections.observableArrayList(1,2,3,4,5);
        cb_quantity.setItems(quantities);
        cb_quantity.setValue(1);
        cb_quantity.valueProperty().addListener((observable) -> {
            coffeeOrder.setQuantity(cb_quantity.getValue());
        });
    }

    /**
     * Adds a Caramel addIn to coffee if selected, removes if deselected.
     */
    @FXML
    void setCaramel() {
        if (cb_caramel.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.CARAMEL);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.CARAMEL);
        }
    }

    /**
     * Adds an Irish Cream addIn to coffee if selected, removes if deselected.
     */
    @FXML
    void setIrishCream() {
        if (cb_IrishCream.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.IRISH_CREAM);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.IRISH_CREAM);
        }
    }

    /**
     * Adds a Mocha addIn to coffee if selected, removes if deselected.
     */
    @FXML
    void setMocha() {
        if (cb_mocha.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.MOCHA);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.MOCHA);
        }
    }

    /**
     * Adds a Sweet Cream addIn to coffee if selected, removes if deselected.
     */
    @FXML
    void setSweetCream() {
        if (cb_sweetCream.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.SWEET_CREAM);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.SWEET_CREAM);
        }
    }

    /**
     * Adds a French Vanilla addIn to coffee if selected, removes if deselected.
     */
    @FXML
    void setFrenchVanilla() {
        if (cb_frenchVanilla.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.FRENCH_VANILLA);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.FRENCH_VANILLA);
        }
    }

    /**
     * Changes the current scene back the primary scene (mainView).
     */
    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }

    /**
     * Sets the references to the primary stage/scene.
     * @param stage reference to the primary stage.
     * @param scene reference to the primary scene.
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    /**
     * Sets the reference to the current order.
     * Allows the current order to be passed between different views.
     * @param order reference to the current order.
     */
    public void setCurrentOrder(Order order){
        currentOrder = order;
    }



}