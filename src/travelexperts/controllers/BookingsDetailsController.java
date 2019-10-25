/**
 * Sample Skeleton for 'BookingsDetails.fxml' Controller Class
 */

package travelexperts.controllers;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import travelexperts.dbhandler.CustomerDBHandler;
import travelexperts.dbhandler.DBConnectionManager;
import travelexperts.dbhandler.TravelPackageDBHandler;
import travelexperts.models.Bookings;
import travelexperts.models.Customer;

public class BookingsDetailsController {

    Bookings booking = new Bookings();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfBookingId"
    private TextField tfBookingId; // Value injected by FXMLLoader

    @FXML // fx:id="tfTravelerCount"
    private TextField tfTravelerCount; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="tfTripTypeId"
    private TextField tfTripTypeId; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageId"
    private TextField tfPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="tfBookingNo"
    private TextField tfBookingNo; // Value injected by FXMLLoader

    @FXML // fx:id="btnEdit"
    private Button btnEdit; // Value injected by FXMLLoader

    @FXML // fx:id="tfBookingDate"
    private TextField tfBookingDate; // Value injected by FXMLLoader

    @FXML
    private DatePicker dpBookingDate;

    @FXML
    private ComboBox<Customer> cboCustomerID;

    @FXML
    void onActionBtnEdit(ActionEvent event) {
        btnEdit.setDisable(true);
        tfBookingId.setEditable(true);
        tfTravelerCount.setEditable(true);
        dpBookingDate.setEditable(true);

        if(this.booking.getPackageId() != 0){
            tfTripTypeId.setEditable(false);
        }else{
        tfTripTypeId.setEditable(true);
        }
        tfPackageId.setEditable(true);
        btnSave.setDisable(false);

    }

    @FXML
    void onActionBtnSave(ActionEvent event) throws SQLException {
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        Integer packageId = null;
        String tripType =null;
        packageId = Integer.parseInt(tfPackageId.getText());


        if(tfPackageId.getText() != null && packageId != 0){
                tripType = TravelPackageDBHandler.gatPackageDetails(packageId).getPkgTripType();
        }else{
            tripType = tfTripTypeId.getText();
        }

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
            statement.setDate(1, Date.valueOf(dpBookingDate.getValue()));
            statement.setString(2, tfBookingNo.getText());
            statement.setInt(3, Integer.parseInt(tfTravelerCount.getText()));
            statement.setInt(4, cboCustomerID.getSelectionModel().getSelectedItem().getCustomerID());
            statement.setString(5, tripType);
            if (packageId != null && packageId != 0) {
                statement.setInt(6, packageId);
            } else {
                statement.setNull(6, Types.INTEGER);
            }
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
        tfBookingId.setEditable(false);  //make text fields non-editable
        dpBookingDate.setEditable(false);
        tfBookingNo.setEditable(false);
        tfTravelerCount.setEditable(false);
        tfTripTypeId.setEditable(false);
        tfPackageId.setEditable(false);
        btnEdit.setDisable(false);
        btnSave.setDisable(true);


    }

    public void displayBookingsDetails(Bookings bookings) {
        this.booking = bookings;
        tfBookingId.setText(bookings.getBookingId() + "");
        tfBookingNo.setText(bookings.getBookingNo() + "");
        tfTravelerCount.setText(bookings.getTravelerCount() + "");
        tfTripTypeId.setText(bookings.getTripTypeId() + "");
        tfPackageId.setText(bookings.getPackageId() + "");
        dpBookingDate.setValue(LOCAL_DATE(bookings.getBookingDate().toString()));

        ObservableList<Customer> customers = FXCollections.observableArrayList();
        customers = CustomerDBHandler.getCustomers("");
        Customer selectedCustomer = null;
        selectedCustomer = CustomerDBHandler.getCustomers(String.valueOf(bookings.getCustomerId())).get(0);

        cboCustomerID.setItems(customers);
        cboCustomerID.getSelectionModel().select(selectedCustomer);

    }
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

}


