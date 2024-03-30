package rucafe.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

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
        setFlavors();

        sp_quantity.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                MIN_QUANTITY,
                MAX_QUANTITY));
        sp_quantity.valueProperty().addListener((obj, previousValue, newValue) ->
            {setQuantity();});

        currentDonuts = new Donut(getType(), sp_quantity.getValue());


        donuts = new DonutBox();
        donuts.setPendingDonut(new Donut(getType(), sp_quantity.getValue()));

        tf_price.textProperty().bind(donuts.priceStringProperty());

        tc_donut.setCellValueFactory(new PropertyValueFactory<>("donutText"));
        tc_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ObservableList<Donut> donutList = donuts.getDonutList();
        tv_donuts.setItems(donutList);
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

    private void setFlavors() {
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

    private void alertIncompleteDonut() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Unfinished Order");
        alert.setHeaderText("Flavor Not Selected");
        alert.setContentText("Please choose a donut flavor to add to your box.");
        alert.showAndWait();
    }

    private void alertSelectToBeRemoveDonut() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection Made");
        alert.setHeaderText("");
        alert.setContentText("Please select donuts to remove them from your box.");
        alert.showAndWait();
    }

    private void alertAddDonutToBox() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Donut Box Empty");
        alert.setHeaderText("");
        alert.setContentText("Please add your donuts to your box before placing your order.");
        alert.showAndWait();
    }

    public boolean alertAddPendingDonutToBox() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Add");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Discard");
        alert.setTitle("Current donut not yet added to box");
        alert.setHeaderText("");
        alert.setContentText("Current donut not yet added to your box, would you like to add or discard?");

        Optional<ButtonType> button = alert.showAndWait();
        return (button.get() == ButtonType.OK);
    }

    private boolean confirmOrder() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        alert.setTitle("Order confirmation");
        alert.setHeaderText("");
        alert.setContentText("Do you want to place this order?");

        Optional<ButtonType> button = alert.showAndWait();
        return (button.get() == ButtonType.OK);
    }

    private boolean confirmLeave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        alert.setTitle("Order not placed");
        alert.setHeaderText("");
        alert.setContentText("There are still donuts in your box, are you sure you want to leave the page?");

        Optional<ButtonType> button = alert.showAndWait();
        return (button.get() == ButtonType.OK);
    }

    private void successAddDonuts() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Status");
        alert.setHeaderText("");
        alert.setContentText("Your Donut Box was successfully added to your cart!");
        alert.showAndWait();
    }

    public void setCurrentOrder(Order order){
        currentOrder = order;
    }

    public void setQuantity() {
        if(!(donuts.hasPendingDonut())) {
            donuts.setPendingDonut(new Donut(getType(), sp_quantity.getValue()));
            if(cmb_flavor.valueProperty().getValue() != null)
                setFlavor();
        }

        donuts.getPendingDonut().setQuantity(sp_quantity.getValue());
        donuts.setPrice(donuts.price());
    }

    @FXML
    private void setFlavor() {
        if(!(donuts.hasPendingDonut()))
            donuts.setPendingDonut(new Donut(getType(),sp_quantity.getValue()));

        donuts.getPendingDonut().setFlavor(cmb_flavor.getValue());
    }

    @FXML
    public void changeDonutType() {
        setFlavors();
        setDonutImage();

        if(!(donuts.hasPendingDonut()))
            donuts.setPendingDonut(new Donut(getType(),sp_quantity.getValue()));

        donuts.getPendingDonut().setType(getType());
        donuts.setPrice(donuts.price());
    }

    @FXML
    protected void displayMain() {
        if(!(donuts.isEmpty()) && !(confirmLeave()))
            return;
        primaryStage.setScene(primaryScene);
    }

    @FXML
    public void addToBox() {
        if(!(donuts.hasPendingDonut()) || donuts.getPendingDonut().isIncomplete()) {
            if(cmb_flavor.valueProperty().getValue() == null) {
                alertIncompleteDonut();
                return;
            }
            setFlavor();
        }

        donuts.getPendingDonut().setQuantity(sp_quantity.getValue());
        donuts.addPendingDonut();
        sp_quantity.getValueFactory().setValue(MIN_QUANTITY);
        donuts.clearPendingDonut();
    }

    @FXML
    public void removeFromBox() {
        Donut donut = tv_donuts.getSelectionModel().getSelectedItem();
        if(donut == null) {
            alertSelectToBeRemoveDonut();
            return;
        }
        donuts.removeDonut(donut);
    }

    @FXML
    public void addBoxToOrder() {
        if(donuts.isEmpty()) {
            alertAddDonutToBox();
            return;
        }

        if(donuts.hasPendingDonut() && !(donuts.getPendingDonut().isIncomplete())) {
            if(alertAddPendingDonutToBox())
                donuts.addPendingDonut();

            donuts.clearPendingDonut();
            return;
        }

        if(confirmOrder()) {
            ObservableList<Donut> donutOrder = donuts.getDonutList();
            for(Donut donut: donutOrder){
                currentOrder.addToOrder(donut);
            }
            successAddDonuts();
            donuts = new DonutBox();
            displayMain();
        }
    }

}
