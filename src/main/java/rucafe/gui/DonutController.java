package rucafe.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class DonutController {

    private Stage primaryStage;
    private Scene primaryScene;
    private Order currentOrder;
    private Donut currentDonuts;
    private DonutBox donuts;

    private final int MIN_QUANTITY = 1;
    private final int MAX_QUANTITY = 99;

    @FXML
    private RadioButton cbx_cake, cbx_yeast;

    @FXML
    private ComboBox<Donut.Flavor> cmb_flavor;

    @FXML
    private TableView<Donut> tv_donuts;

    @FXML
    private Spinner<Integer> sp_quantity;

    @FXML
    private TableColumn<Donut, String> tc_donut;

    @FXML
    private TableColumn<Integer, Integer> tc_quantity;

    @FXML
    private ImageView iv_donut;


    @FXML
    private TextField tf_price;


    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;

        setDonutImage();
        setFlavor();

        sp_quantity.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                MIN_QUANTITY,
                MAX_QUANTITY));

        currentDonuts = new Donut(getType(), sp_quantity.getValue());
        donuts = new DonutBox();

        tf_price.textProperty().bind(donuts.priceStringProperty());
    }

    private void setDonutImage() {

        String yeastPath = "yeastDonut.jpg"; // Source: https://www.biggerbolderbaking.com/no-yeast-homemade-donuts/
        String cakePath = "cakeDonut.jpg"; // Source: https://bakemark.com/en/product/cake-donut-mixes-bases/
        String donutHolePath = "donutHoles.jpg"; //Source: https://beyondflourblog.com/gluten-free-doughnut-holes-recipe/
        Image img_Donut;
        int height = (int) iv_donut.getFitHeight();
        int width = (int) iv_donut.getFitWidth();

        if(cbx_yeast.isSelected()) {
            img_Donut = new Image(Objects.requireNonNull(getClass().getResourceAsStream(yeastPath)),
                    width, height, false, false);
        }
        else if(cbx_cake.isSelected()) {
            img_Donut = new Image(Objects.requireNonNull(getClass().getResourceAsStream(cakePath)),
                    width, height, false, false);
        }
        else{
            img_Donut = new Image(Objects.requireNonNull(getClass().getResourceAsStream(donutHolePath)),
                    width, height, false, false);
        }

        iv_donut.setImage(img_Donut);
    }

    private void setFlavor() {
        cmb_flavor.setItems(getType().getFlavors());
    }

    private Donut.Type getType() {
        if(cbx_yeast.isSelected()) {
            return Donut.Type.YEAST;
        }
        else if(cbx_cake.isSelected()) {
            return Donut.Type.CAKE;
        }
        else{
           return Donut.Type.HOLE;
        }
    }

    public void setCurrentOrder(Order order){
        currentOrder = order;
    }

    @FXML
    public void changeDonutType() {
        setFlavor();
        setDonutImage();
        currentDonuts.setType(getType());
    }

    @FXML
    protected void displayMain() {
        primaryStage.setScene(primaryScene);
    }

}
