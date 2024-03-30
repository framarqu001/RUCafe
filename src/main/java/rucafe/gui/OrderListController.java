package rucafe.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class OrderListController {
    private Stage primaryStage;
    private Scene primaryScene;
    private OrderList orderList;


    @FXML
    ListView<MenuItem> lv_orders;

    @FXML
    ChoiceBox<Order> cb_orderNumbers;

    @FXML
    TextField tf_subTotal;


    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
        if (orderList.isEmpty()) return;

        ObservableList<MenuItem> menuItems = orderList.getOrderList().getLast().getMenuItems();
        cb_orderNumbers.valueProperty().addListener((observable) -> {
            if (cb_orderNumbers.getValue() != null) {
                lv_orders.setItems(cb_orderNumbers.getValue().getMenuItems());
                tf_subTotal.setText(cb_orderNumbers.getValue().subTotalStringProperty().get());
            }
        });
        cb_orderNumbers.setItems(orderList.getOrderList());
        cb_orderNumbers.setValue(orderList.getOrderList().getLast());
    }

    @FXML
    public void removeOrder() {
        if (orderList.isEmpty()) return;

        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Remove item");
        warning.setContentText("Are you sure you want to remove order #: " + cb_orderNumbers.getValue());
        Optional<ButtonType> button = warning.showAndWait();
        if (button.get() == ButtonType.OK) {
            orderList.removeOrder(cb_orderNumbers.getValue());
            Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmAlert.setTitle("Remove Item");
            confirmAlert.setHeaderText("");
            confirmAlert.setContentText("Order has been removed");
            confirmAlert.showAndWait();
            if (orderList.isEmpty()) {
                lv_orders.getItems().clear();
                tf_subTotal.setText("$0.00");
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                informationAlert.setTitle("Order");
                informationAlert.setHeaderText("");
                informationAlert.setContentText("No more Orders to be displayed");
                informationAlert.showAndWait();
                primaryStage.setScene(primaryScene);
                return;
            }
            cb_orderNumbers.setValue(orderList.getOrderList().getLast());

        }


    }

    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }

    @FXML
    public void exportOrder() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order confirmation");
        alert.setContentText("Export these orders?");
        Optional<ButtonType> button = alert.showAndWait();
        if (button.get() == ButtonType.OK) {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = primaryStage;
            File targetFile = chooser.showSaveDialog(stage);
            if (targetFile == null) {
                return;
            }
            try (PrintWriter pw = new PrintWriter(targetFile)) {
                pw.write(orderList.printOrders());
            } catch (IOException e) {
                System.out.println("cannot open file");
            }
        }

    }
}
