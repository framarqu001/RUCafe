package rucafe.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CoffeeController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Coffee coffeeOrder = new Coffee();
    private Order currentOrder;
    private Image small = new Image("file:src/main/resources/rucafe/gui/small.png");
    private Image medium = new Image("file:src/main/resources/rucafe/gui/medium.png");
    private Image large = new Image("file:src/main/resources/rucafe/gui/large.png");


    @FXML
    private CheckBox cb_sweetCream, cb_mocha, cb_frenchVanilla, cb_caramel, cb_IrishCream;

    @FXML
    private RadioButton rb_large, rb_medium, rb_small, rb_venti;

    @FXML
    private TextField tf_total;

    @FXML
    private ToggleGroup size;

    @FXML
    void addToOrder() {
        System.out.println("hey");
        Coffee copy = new Coffee(coffeeOrder);
        currentOrder.addToOrder(copy);
        coffeeOrder.reset();
        //create stuff to reset the gui. add quantity

    }

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
    }

    @FXML
    void setCaramel() {
        if (cb_caramel.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.CARAMEL);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.CARAMEL);
        }
    }

    @FXML
    void setIrishCream() {
        if (cb_IrishCream.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.IRISH_CREAM);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.IRISH_CREAM);
        }
    }

    @FXML
    void setMocha() {
        if (cb_mocha.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.MOCHA);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.MOCHA);
        }
    }

    @FXML
    void setSweetCream() {
        if (cb_sweetCream.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.SWEET_CREAM);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.SWEET_CREAM);
        }
    }

    @FXML
    void setFrenchVanilla() {
        if (cb_frenchVanilla.isSelected()) {
            coffeeOrder.addAddIn(Coffee.AddIns.FRENCH_VANILLA);
        } else {
            coffeeOrder.removeAddIn(Coffee.AddIns.FRENCH_VANILLA);
        }
    }

    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }


    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    public void setCurrentOrder(Order order){
        currentOrder = order;
    }



}