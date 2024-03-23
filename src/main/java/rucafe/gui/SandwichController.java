package rucafe.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class SandwichController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Order currentOrder;
    private RUCafeMainController mainController;

    @FXML
    private ImageView iv_Sandwich;

    @FXML
    private CheckBox chk_cheese, chk_lettuce, chk_onions, chk_tomatoes;

    @FXML
    private ComboBox<Sandwich.Bread> cmb_Bread;

    @FXML
    private ComboBox<Sandwich.Protein> cmb_Protein;

    @FXML
    private TextField tf_price;

    //Image source: https://www.southernliving.com/ham-sandwich-7971753
    private final String SANDWICH_PATH = "sandwichPic.jpg";
    private Sandwich sandwich;

    private void setSandwich() {
        if(sandwich == null)
            sandwich = new Sandwich();
    }

    private void manageAddOns(CheckBox box, Sandwich.AddOn addOn) {
        if(box.isSelected())
            sandwich.addAddOn(addOn);
        else
            sandwich.removeAddOn(addOn);
    }

    private void alertIncompleteSandwich() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Unfinished Order");
        alert.setHeaderText("Bread/Protein Not Selected");
        alert.setContentText("Please choose your protein/bread to complete your order.");
        alert.showAndWait();
    }

    private void successAddSandwich() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Added to Order");
        alert.setHeaderText("Sandwich added to order");
        alert.setContentText("Your Sandwich (" + sandwich + ") was successfully added to your order!");
        alert.showAndWait();
    }

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;

        int height = (int) iv_Sandwich.getFitHeight();
        int width = (int) iv_Sandwich.getFitWidth();

        Image img_Sandwich = new Image(Objects.requireNonNull(getClass().getResourceAsStream(SANDWICH_PATH)),
                width, height, false, false);
        iv_Sandwich.setImage(img_Sandwich);

        cmb_Bread.setItems(Sandwich.getBreadList());
        cmb_Protein.setItems(Sandwich.getProteinList());
        setSandwich();

        tf_price.textProperty().bind(sandwich.priceStringProperty());
    }

    public void setCurrentOrder(Order order){
        currentOrder = order;
    }

    @FXML
    protected void setProtein() {
        sandwich.setProtein(cmb_Protein.getValue());
    }

    @FXML
    protected void setBread() {
        sandwich.setBread(cmb_Bread.getValue());
    }

    @FXML
    protected void addCheese() {
        manageAddOns(chk_cheese, Sandwich.AddOn.CHEESE);
    }

    @FXML
    protected void addLettuce() {
        manageAddOns(chk_lettuce, Sandwich.AddOn.LETTUCE);
    }

    @FXML
    protected void addTomatoes() {
        manageAddOns(chk_tomatoes, Sandwich.AddOn.TOMATOES);
    }

    @FXML
    protected void addOnions() {
        manageAddOns(chk_onions, Sandwich.AddOn.ONIONS);
    }

    @FXML
    protected void clear() {
        sandwich = new Sandwich();
        tf_price.textProperty().bind(sandwich.priceStringProperty());

        cmb_Bread.valueProperty().set(null);
        cmb_Protein.valueProperty().set(null);

        chk_cheese.setSelected(false);
        chk_lettuce.setSelected(false);
        chk_onions.setSelected(false);
        chk_tomatoes.setSelected(false);
    }

    @FXML
    void addToOrder() {
        if(sandwich.isIncomplete()) {
            alertIncompleteSandwich();
            return;
        }

        Sandwich copy = new Sandwich(sandwich);
        currentOrder.addToOrder(copy);
        successAddSandwich();

        sandwich = new Sandwich();
        tf_price.textProperty().bind(sandwich.priceStringProperty());
        clear();
    }

    @FXML
    protected void displayMain() {
        primaryStage.setScene(primaryScene);
    }

}
