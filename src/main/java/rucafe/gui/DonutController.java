package rucafe.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

/**
 * Controller for Donut view, allows user to order a box of donuts and add them to their order.
 */
public class DonutController {

    private Stage primaryStage;
    private Scene primaryScene;
    private Order currentOrder;
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


    /**
     * Sets the stage for the donut view as well as all of its components, stores references to parent stage and scene.
     * @param stage the reference to the parent stage.
     * @param scene the reference to the parent scene.
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;

        setDonutImage(getType());
        setFlavors();

        sp_quantity.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                MIN_QUANTITY,
                MAX_QUANTITY));
        sp_quantity.valueProperty().addListener((obj, previousValue, newValue) ->
            {setQuantity();});

        donuts = new DonutBox();
        donuts.setPendingDonut(new Donut(getType(), sp_quantity.getValue()));

        tf_price.textProperty().bind(donuts.priceStringProperty());

        tc_donut.setCellValueFactory(new PropertyValueFactory<>("donutText"));
        tc_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ObservableList<Donut> donutList = donuts.getDonutList();
        tv_donuts.setItems(donutList);
    }

    /**
     * Depending on the currently selected donut type, displays an image associated with that type.
     */
    private void setDonutImage(Donut.Type type) {

        String yeastPath = "yeastDonut.jpg"; // Source: https://www.biggerbolderbaking.com/no-yeast-homemade-donuts/
        String cakePath = "cakeDonut.jpg"; // Source: https://bakemark.com/en/product/cake-donut-mixes-bases/
        String donutHolePath = "donutHoles.jpg"; //Source: https://beyondflourblog.com/gluten-free-doughnut-holes-recipe/
        Image img_Donut;
        String imagePath = "";
        int height = (int) iv_donut.getFitHeight();
        int width = (int) iv_donut.getFitWidth();

        switch(type) {
            case Donut.Type.YEAST:
                imagePath = yeastPath;
                break;
            case Donut.Type.CAKE:
                imagePath = cakePath;
                break;
            default:
                imagePath = donutHolePath;
        }

        img_Donut = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)),
                width, height, false, false);

        iv_donut.setImage(img_Donut);
    }

    /**
     * Depending on the flavor selected, fills the combo box with all flavors available for the donut type.
     */
    private void setFlavors() {
        cmb_flavor.setItems(getType().getFlavors());
    }

    /**
     * Returns type of donut that is currently selected on the radiobuttons.
     * @return the type of donut that is currently selected.
     */
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

    /**
     * Displays alert informing user that they need to choose a donut flavor before proceeding.
     */
    private void alertIncompleteDonut() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Unfinished Order");
        alert.setHeaderText("Flavor Not Selected");
        alert.setContentText("Please choose a donut flavor to add to your box.");
        alert.showAndWait();
    }

    /**
     * Displays alert informing user that they need to select a donut from the box to remove it.
     */
    private void alertSelectToBeRemoveDonut() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection Made");
        alert.setHeaderText("");
        alert.setContentText("Please select donuts to remove them from your box.");
        alert.showAndWait();
    }

    /**
     * Displays alert informing user they must add donuts to the box before placing an order.
     */
    private void alertAddDonutToBox() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Donut Box Empty");
        alert.setHeaderText("");
        alert.setContentText("Please add your donuts to your box before placing your order.");
        alert.showAndWait();
    }

    /**
     * Displays a warning to user that they have a donut pending to be added to their box,
     * asks user if they would like to add donut to box or discard it.
     * @return true if the user choose to add the donut to the box, false if otherwise.
     */
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

    /**
     * Asks if user wants to place their order of donuts.
     * @return true if the user answers yes, false if otherwise.
     */
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

    /**
     * Displays warning to user if there is still donuts in box, asks if user want to leave page and lose progress or
     * not.
     * @return true if the user chooses to leave false if otherwise.
     */
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

    /**
     * Displays confirmation message to user that donuts were successfully added to order.
     */
    private void successAddDonuts() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Status");
        alert.setHeaderText("");
        alert.setContentText("Your Donut Box was successfully added to your cart!");
        alert.showAndWait();
    }

    /**
     * Sets current order to that of the order given
     * @param order the current order being taken.
     */
    public void setCurrentOrder(Order order){
        currentOrder = order;
    }

    /**
     * Sets the quantity of the pending donut to the current value of the spinner
     * If there is no pending donut, creates a new one.
     */
    public void setQuantity() {
        if(!(donuts.hasPendingDonut())) {
            donuts.setPendingDonut(new Donut(getType(), sp_quantity.getValue()));
            if(cmb_flavor.valueProperty().getValue() != null)
                setFlavor();
        }

        donuts.getPendingDonut().setQuantity(sp_quantity.getValue());
        donuts.setPrice(donuts.price());
    }

    /**
     * Sets flavor of the pending donut depending on value of combobox.
     * If there is no pending donut, creates a new one.
     */
    @FXML
    private void setFlavor() {
        if(!(donuts.hasPendingDonut()))
            donuts.setPendingDonut(new Donut(getType(),sp_quantity.getValue()));

        donuts.getPendingDonut().setFlavor(cmb_flavor.getValue());
    }

    /**
     * Changes the type of the pending donuts depending on what radio button is selected.
     * If there is no pending donut, creates a new one.
     */
    @FXML
    public void changeDonutType() {
        Donut.Type type = getType();
        setFlavors();
        setDonutImage(type);

        if(!(donuts.hasPendingDonut()))
            donuts.setPendingDonut(new Donut(type,sp_quantity.getValue()));

        donuts.getPendingDonut().setType(getType());
        donuts.setPrice(donuts.price());
    }

    /**
     * Checks if the donut box is empty before returning user back to the main menu.
     * If the box has donuts inside, asks user if they want to leave.
     */
    @FXML
    protected void displayMain() {
        if(!(donuts.isEmpty()) && !(confirmLeave()))
            return;
        primaryStage.setScene(primaryScene);
    }

    /**
     * Adds pending donut to the donut box and clears/sets all values to default afterwards.
     * If there is no pending donut, creates a new one to be added.
     */
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

    /**
     * Removes selected donut from the box, if there is no selected donut displays error message to user.
     */
    @FXML
    public void removeFromBox() {
        Donut donut = tv_donuts.getSelectionModel().getSelectedItem();
        if(donut == null) {
            alertSelectToBeRemoveDonut();
            return;
        }
        donuts.removeDonut(donut);
    }

    /**
     * Checks if there is no complete pending donuts and if the box has any donuts inside.
     * If both are true asks user to confirm their order and if so adds each donut to the order.
     * If there is an issue displays warning to user informing them what they need to do to place their order.
     */
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
