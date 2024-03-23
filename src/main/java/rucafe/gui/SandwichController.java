package rucafe.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    private Coffee coffeeOrder = new Coffee();
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
        sandwich.addAddOn(Sandwich.AddOn.CHEESE);
    }

    @FXML
    protected void addLettuce() {
        sandwich.addAddOn(Sandwich.AddOn.LETTUCE);
    }

    @FXML
    protected void addTomatoes() {
        sandwich.addAddOn(Sandwich.AddOn.TOMATOES);
    }

    @FXML
    protected void addOnions() {
        sandwich.addAddOn(Sandwich.AddOn.ONIONS);
    }

    @FXML
    protected void clear() {
        sandwich = new Sandwich();
        cmb_Bread.valueProperty().set(null);
        cmb_Protein.valueProperty().set(null);

        chk_cheese.setSelected(false);
        chk_lettuce.setSelected(false);
        chk_onions.setSelected(false);
        chk_tomatoes.setSelected(false);
    }

    @FXML
    protected void displayMain() {
        primaryStage.setScene(primaryScene);
    }

}
