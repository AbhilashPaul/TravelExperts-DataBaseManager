/**
 * Sample Skeleton for 'Booking.fxml' Controller Class
 */

package travelexperts.controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import travelexperts.dbhandler.BookingsDBHandler;
import travelexperts.dbhandler.DBConnectionManager;
import travelexperts.dbhandler.TravelPackageDBHandler;
import travelexperts.models.Bookings;


public class BookingController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tableBookings"
    private TableView<Bookings> tableBookings; // Value injected by FXMLLoader

    @FXML // fx:id="col_BookingId"
    private TableColumn<Bookings, Integer> col_BookingId; // Value injected by FXMLLoader

    @FXML // fx:id="col_BookingDate"
    private TableColumn<Bookings, java.util.Date> col_BookingDate; // Value injected by FXMLLoader

    @FXML // fx:id="col_BookingNo"
    private TableColumn<Bookings, String> col_BookingNo; // Value injected by FXMLLoader

    @FXML
    private Button btnAddBookings; // Value injected by FXMLLoader

    @FXML // fx:id="tfBookingId"
    private TextField tfBookingId; // Value injected by FXMLLoader

    @FXML // fx:id="tfTravelerCount"
    private TextField tfTravelerCount; // Value injected by FXMLLoader

    @FXML // fx:id="tfTripTypeId"
    private TextField tfTripTypeId; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageId"
    private TextField tfPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustomerId"
    private TextField tfCustomerId; // Value injected by FXMLLoader

    @FXML // fx:id="tfBookingNo"
    private TextField tfBookingNo; // Value injected by FXMLLoader

    @FXML // fx:id="tfBookingDate"
    private TextField tfBookingDate; // Value injected by FXMLLoader



    @FXML
    void onActionBtnAddBookings(ActionEvent event) throws SQLException {
        btnAddBookings.setDisable(true);

        int customerId=0, packageId = 0, travelerCount=0;
        String tripType=null;
        String bookingNo=null;
        long millis=System.currentTimeMillis();
        java.sql.Date bookingDate = new java.sql.Date(millis);

        try{
            customerId = Integer.parseInt(tfCustomerId.getText());
            if(tfPackageId.getText() != null){
                packageId = Integer.parseInt(tfPackageId.getText());
                tripType = TravelPackageDBHandler.gatPackageDetails(packageId).getPkgTripType();
            }else{
                tripType = tfTripTypeId.getText();
            }
            travelerCount = Integer.parseInt(tfTravelerCount.getText());
        }catch (Exception e){
            e.getStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please verify your inputs!", ButtonType.OK);
            alert.show();
        }

        bookingNo = Bookings.generateBookingNumber(5);


        String sql = "Insert into bookings (BookingDate,BookingNo,TravelerCount,CustomerId,TripTypeId,PackageId) Values (?,?,?,?,?,?) ";
        int rows;
        Connection connection = DBConnectionManager.getDBConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, (java.sql.Date)bookingDate );
            statement.setString(2, bookingNo);
            statement.setInt(3, travelerCount);
            statement.setInt(4, customerId);
            statement.setString(5, tripType);
            statement.setInt(6, packageId);
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
            initialize();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Insertion successful", ButtonType.OK);
            alert.show();
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

        TableView.TableViewSelectionModel<Bookings> tableBookingsSelect = (TableView.TableViewSelectionModel<Bookings>) tableBookings.getSelectionModel();

        tableBookingsSelect.selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/BookingsDetails.fxml"));

                try {
                    Parent root = loader.load();
                    Scene detailsScene = new Scene(root, 699, 551);
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

