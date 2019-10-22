/**
 * Sample Skeleton for 'BookingsDetails.fxml' Controller Class
 */

package travelexperts.main;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import travelexperts.dbhandler.BookingsDBHandler;
import travelexperts.dbhandler.DBConnectionManager;
import travelexperts.dbhandler.TravelPackageDBHandler;
import travelexperts.models.Bookings;
import travelexperts.models.TravelPackage;

public class BookingsDetailsController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfBookingId"
    private TextField tfBookingId; // Value injected by FXMLLoader

    @FXML // fx:id="tfTravelerCount"
    private TextField tfTravelerCount; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddBookings"
    private Button btnAddBookings; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="tfTripTypeId"
    private TextField tfTripTypeId; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageId"
    private TextField tfPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustomerId"
    private TextField tfCustomerId; // Value injected by FXMLLoader

    @FXML // fx:id="tfBookingNo"
    private TextField tfBookingNo; // Value injected by FXMLLoader

    @FXML // fx:id="btnEdit"
    private Button btnEdit; // Value injected by FXMLLoader

    @FXML // fx:id="tfBookingDate"
    private TextField tfBookingDate; // Value injected by FXMLLoader

    private ObservableList<Bookings> data;


    @FXML
    void onActionBtnAddBookings(ActionEvent event) throws SQLException {

        btnAddBookings.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        String sql = "Insert into bookings (BookingId,BookingDate,BookingNo,TravelerCount,CustomerId,TripTypeId,PackageId) Values (?,?,?,?,?,?,?) ";
        int rows;
        Connection connection = DBConnectionManager.getDBConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(tfBookingId.getText()));
            statement.setDate(2, Date.valueOf(tfBookingDate.getText()));
            statement.setString(3, tfBookingNo.getText());
            statement.setInt(4, Integer.parseInt(tfTravelerCount.getText()));
            statement.setInt(5, Integer.parseInt(tfCustomerId.getText()));
            statement.setString(6, tfTripTypeId.getText());
            statement.setInt(7, Integer.parseInt(tfPackageId.getText()));
            rows = statement.executeUpdate();
        }
        connection.close();
        if (rows == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insertion failed, contact tech support", ButtonType.OK);
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Insertion successful", ButtonType.OK);
            alert.show();
        }

    }


    @FXML
    void onActionBtnEdit(ActionEvent event) {
        btnEdit.setDisable(true);
        tfBookingId.setEditable(true);
        tfBookingDate.setEditable(true);
        tfTravelerCount.setEditable(true);
        tfCustomerId.setEditable(true);
        tfTripTypeId.setEditable(true);
        tfPackageId.setEditable(true);
        btnSave.setDisable(false);
        btnAddBookings.setDisable(false);

    }


    @FXML
    void onActionBtnSave(ActionEvent event) throws SQLException {
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        String sql = "UPDATE `bookings` SET `BookingDate`=? , `BookingNo`=? ,  `TravelerCount` =?, `CustomerId` =?, `TriptypeId` = ?, `PackageId` =? WHERE `BookingId`= ?";
        int rows;
        Connection connection = DBConnectionManager.getDBConnection();
        // if (connection == null)
        // System.out.println("Connection is NULL!");
        // else
        //System.out.println("Connection is NOT NULL!");
        // System.out.println("Setting PackageID to "+Integer.parseInt(tfPackageId.getText()));
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(7, Integer.parseInt(tfBookingId.getText()));
            statement.setDate(1, Date.valueOf(tfBookingDate.getText()));
            statement.setString(2, tfBookingNo.getText());
            statement.setInt(3, Integer.parseInt(tfTravelerCount.getText()));
            statement.setInt(4, Integer.parseInt(tfCustomerId.getText()));
            statement.setString(5, tfTripTypeId.getText());
            statement.setInt(6, Integer.parseInt(tfPackageId.getText()));
            rows = statement.executeUpdate();
        }
        connection.close();
        if (rows == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed, contact tech support", ButtonType.OK);
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful", ButtonType.OK);
            alert.show();
        }

    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tfBookingId != null : "fx:id=\"tfBookingId\" was not injected: check your FXML file 'BookingsDetails.fxml'.";
        assert tfTravelerCount != null : "fx:id=\"tfTravelerCount\" was not injected: check your FXML file 'BookingsDetails.fxml'.";
        assert btnAddBookings != null : "fx:id=\"btnAddBookings\" was not injected: check your FXML file 'BookingsDetails.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'BookingsDetails.fxml'.";
        assert tfTripTypeId != null : "fx:id=\"tfTripTypeId\" was not injected: check your FXML file 'BookingsDetails.fxml'.";
        assert tfPackageId != null : "fx:id=\"tfPackageId\" was not injected: check your FXML file 'BookingsDetails.fxml'.";
        assert tfCustomerId != null : "fx:id=\"tfCustomerId\" was not injected: check your FXML file 'BookingsDetails.fxml'.";
        assert tfBookingNo != null : "fx:id=\"tfBookingNo\" was not injected: check your FXML file 'BookingsDetails.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'BookingsDetails.fxml'.";
        assert tfBookingDate != null : "fx:id=\"tfBookingDate\" was not injected: check your FXML file 'BookingsDetails.fxml'.";



        tfBookingId.setEditable(false);  //make text fields non-editable
        tfBookingDate.setEditable(false);
        tfBookingNo.setEditable(false);
        tfTravelerCount.setEditable(false);
        tfCustomerId.setEditable(false);
        tfTripTypeId.setEditable(false);
        tfPackageId.setEditable(false);
        btnEdit.setDisable(false);
        btnSave.setDisable(true);
        btnAddBookings.setDisable(true);

    }

    public void displayBookingsDetails(Bookings bookings) {
        tfBookingId.setText(bookings.getBookingId() + "");
        tfBookingDate.setText(bookings.getBookingDate() + "");
        tfBookingNo.setText(bookings.getBookingNo() + "");
        tfTravelerCount.setText(bookings.getTravelerCount() + "");
        tfCustomerId.setText(bookings.getCustomerId() + "");
        tfTripTypeId.setText(bookings.getTripTypeId() + "");
        tfPackageId.setText(bookings.getPackageId() + "");


    }


}

