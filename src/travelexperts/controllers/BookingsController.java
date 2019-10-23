/**
 * Sample Skeleton for 'Bookings.fxml' Controller Class
 */

package travelexperts.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import travelexperts.dbhandler.BookingsDBHandler;
import travelexperts.models.Bookings;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class BookingsController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tableBookings"
    private TableView<Bookings> tableBookings; // Value injected by FXMLLoader

    @FXML // fx:id="col_BookingId"
    private TableColumn<Bookings, Integer> col_BookingId; // Value injected by FXMLLoader

    @FXML // fx:id="col_BookingDate"
    private TableColumn<Bookings, Date> col_BookingDate; // Value injected by FXMLLoader

    @FXML // fx:id="col_BookingNo"
    private TableColumn<Bookings, String> col_BookingNo; // Value injected by FXMLLoader

    @FXML
    private Button btnInvoice;

    @FXML
    void onActionBtnInvoice(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/invoicegenerator.fxml"));
            Parent root1 =  fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private ObservableList<Bookings> data;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        data = FXCollections.observableArrayList();
        data = BookingsDBHandler.gatAllBookings();
        col_BookingId.setCellValueFactory(new PropertyValueFactory<>("BookingId"));
        col_BookingDate.setCellValueFactory(new PropertyValueFactory<>("BookingDate"));
        col_BookingNo.setCellValueFactory(new PropertyValueFactory<>("BookingNo"));

        tableBookings.setItems(data);

        TableView.TableViewSelectionModel<Bookings> tableBookingsSelect = tableBookings.getSelectionModel();
        tableBookingsSelect.selectedIndexProperty().addListener(new ChangeListener<Number>() {


            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/BookingsDetails.fxml"));

                try {
                    Parent root = loader.load();
                    Scene detailsScene = new Scene(root, 599, 351);
                    Stage detailsStage = new Stage();
                    detailsStage.setTitle("Bookings Details");
                    detailsStage.setScene(detailsScene);
                    detailsStage.setMaximized(false);

                    BookingsDetailsController detailsController = loader.getController();
                    detailsController.displayBookingsDetails(data.get((int) newValue));
                    detailsStage.showAndWait();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            });

            assert tableBookings != null : "fx:id=\"tableBookings\" was not injected: check your FXML file 'Bookings.fxml'.";
        assert col_BookingId != null : "fx:id=\"col_BookingId\" was not injected: check your FXML file 'Bookings.fxml'.";
        assert col_BookingDate != null : "fx:id=\"col_BookingDate\" was not injected: check your FXML file 'Bookings.fxml'.";
        assert col_BookingNo != null : "fx:id=\"col_BookingNo\" was not injected: check your FXML file 'Bookings.fxml'.";

    }




    }



