/**
 * Sample Skeleton for 'invoicegenerator.fxml' Controller Class
 */

package travelexperts.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InvoiceController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfName"
    private TextField tfName; // Value injected by FXMLLoader

    @FXML // fx:id="tfAddress"
    private TextField tfAddress; // Value injected by FXMLLoader

    @FXML // fx:id="tfContact"
    private TextField tfContact; // Value injected by FXMLLoader

    @FXML // fx:id="tfBookingNumber"
    private TextField tfBookingNumber; // Value injected by FXMLLoader

    @FXML // fx:id="tfFlight"
    private TextField tfFlight; // Value injected by FXMLLoader

    @FXML // fx:id="tfCarRental"
    private TextField tfCarRental; // Value injected by FXMLLoader

    @FXML // fx:id="tfHotel"
    private TextField tfHotel; // Value injected by FXMLLoader

    @FXML // fx:id="tfInvoiceNumber"
    private TextField tfInvoiceNumber; // Value injected by FXMLLoader

    @FXML // fx:id="btnEmail"
    private Button btnEmail; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="tfTotal"
    private TextField tfTotal; // Value injected by FXMLLoader

    @FXML
    void onActionBtnEmail(ActionEvent event) {

    }

    @FXML
    void onActionBtnSave(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert tfAddress != null : "fx:id=\"tfAddress\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert tfContact != null : "fx:id=\"tfContact\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert tfBookingNumber != null : "fx:id=\"tfBookingNumber\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert tfFlight != null : "fx:id=\"tfFlight\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert tfCarRental != null : "fx:id=\"tfCarRental\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert tfHotel != null : "fx:id=\"tfHotel\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert tfInvoiceNumber != null : "fx:id=\"tfInvoiceNumber\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert btnEmail != null : "fx:id=\"btnEmail\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'invoicegenerator.fxml'.";
        assert tfTotal != null : "fx:id=\"tfTotal\" was not injected: check your FXML file 'invoicegenerator.fxml'.";

    }


}

