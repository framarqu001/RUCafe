package rucafe.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

/**
 * Controller for Sandwich view, allows user to order a sandwich based on what protein, bread and add-ons desired.
 * Once finished the user can add their sandwich to their current order.
 *
 * @author Ashley Berlinski
 */
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

    /**
     * If the pending sandwich of the controller is null, creates a new empty sandwich.
     */
    private void setSandwich() {
        if(sandwich == null)
            sandwich = new Sandwich();
    }

    /**
     * Given a checkbox, determines if it is currently selected and if so adds the given add-on to the sandwich,
     * otherwise it will remove the add-on given.
     * @param box the checkbox whose status is being verified
     * @param addOn the addOn that will be added or removed from the current sandwich.
     */
    private void manageAddOns(CheckBox box, Sandwich.AddOn addOn) {
        if(box.isSelected())
            sandwich.addAddOn(addOn);
        else
            sandwich.removeAddOn(addOn);
    }

    /**
     * Displays alert informing user that they need to choose a bread and/or protein before proceeding.
     */
    private void alertIncompleteSandwich() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Unfinished Order");
        alert.setHeaderText("Bread/Protein Not Selected");
        alert.setContentText("Please choose your protein/bread to complete your order.");
        alert.showAndWait();
    }

    /**
     * Displays confirmation message to user that sandwich was successfully added to order.
     */
    private void successAddSandwich() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Status");
        alert.setHeaderText("");
        alert.setContentText("Your Sandwich (" + sandwich + ") was successfully added to your cart!");
        alert.showAndWait();
    }

    /**
     * Asks if user wants to place their sandwich order.
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
     * Sets the stage for the sandwich controller as well as all of its components,
     * stores references to parent stage and scene.
     * @param stage the reference to the parent stage.
     * @param scene the reference to the parent scene.
     */
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

    /**
     * Sets current order to that of the order given
     * @param order the current order being taken.
     */
    public void setCurrentOrder(Order order){
        currentOrder = order;
    }

    /**
     * Sets protein of the sandwich depending on value of combobox.
     */
    @FXML
    protected void setProtein() {
        sandwich.setProtein(cmb_Protein.getValue());
    }

    /**
     * Sets protein of the sandwich depending on value of combobox.
     */
    @FXML
    protected void setBread() {
        sandwich.setBread(cmb_Bread.getValue());
    }

    /**
     * If the cheese checkbox is selected or not, adds or removes cheese from the sandwich respectively.
     */
    @FXML
    protected void addCheese() {
        manageAddOns(chk_cheese, Sandwich.AddOn.CHEESE);
    }

    /**
     * If the lettuce checkbox is selected or not, adds or removes lettuce from the sandwich respectively.
     */
    @FXML
    protected void addLettuce() {
        manageAddOns(chk_lettuce, Sandwich.AddOn.LETTUCE);
    }

    /**
     * If the tomatoes checkbox is selected or not, adds or removes tomatoes from the sandwich respectively.
     */
    @FXML
    protected void addTomatoes() {
        manageAddOns(chk_tomatoes, Sandwich.AddOn.TOMATOES);
    }

    /**
     * If the onions checkbox is selected or not, adds or removes onions from the sandwich respectively.
     */
    @FXML
    protected void addOnions() {
        manageAddOns(chk_onions, Sandwich.AddOn.ONIONS);
    }

    /**
     * Creates a new sandwich and resets all comboboxes and checkboxes to their default values.
     */
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

    /**
     * Checks the sandwich is complete with a bread and protein being selected
     * If both are true asks user to confirm their order and if so adds the sandwich to their order.
     * If there is an issue displays warning to user informing them what they need to do to place their order.
     */
    @FXML
    void addToOrder() {
        if(sandwich.isIncomplete()) {
            alertIncompleteSandwich();
            return;
        }

        if(confirmOrder()) {
            currentOrder.addToOrder(sandwich);
            successAddSandwich();
            displayMain();
        }
    }

    /**
     * Returns user back to main page.
     */
    @FXML
    protected void displayMain() {
        primaryStage.setScene(primaryScene);
    }

}
