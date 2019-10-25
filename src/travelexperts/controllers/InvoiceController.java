/**
 * Sample Skeleton for 'invoicegenerator.fxml' Controller Class
 */

package travelexperts.controllers;

import java.math.BigDecimal;

import java.math.MathContext;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import travelexperts.dbhandler.*;

import travelexperts.models.*;

import travelexperts.models.Bookingdetail;
import travelexperts.models.Bookings;
import travelexperts.models.Customer;
import travelexperts.models.TravelPackage;
import travelexperts.utils.PrintService;


public class InvoiceController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lbAddress1"
    private Label lbAddress1; // Value injected by FXMLLoader

    @FXML // fx:id="lbAddress2"
    private Label lbAddress2; // Value injected by FXMLLoader
    @FXML
    private Label lbCustEmail;

    @FXML
    private Label lbCustPhone;

    @FXML
    private Label lbAddress5;

    @FXML
    private Label lbName;

    @FXML // fx:id="lbPhone"
    private Label lbPhone; // Value injected by FXMLLoader

    @FXML // fx:id="lbEmail"
    private Label lbEmail; // Value injected by FXMLLoader

    @FXML // fx:id="lbInvoiceNumber"
    private Label lbInvoiceNumber; // Value injected by FXMLLoader

    @FXML // fx:id="tableDetails"
    private TableView<Bookingdetail> tableDetails; // Value injected by FXMLLoader
    @FXML
    private TableColumn<Bookingdetail, Integer> col_BookingDetailId;

    @FXML // fx:id="col_Description"
    private TableColumn<Bookingdetail, String> col_Description; // Value injected by FXMLLoader

    @FXML // fx:id="col_Destination"
    private TableColumn<Bookingdetail, String> col_Destination; // Value injected by FXMLLoader

    @FXML // fx:id="col_Price"
    private TableColumn<Bookingdetail, BigDecimal> col_Price; // Value injected by FXMLLoader

    @FXML // fx:id="lbTotal"
    private Label lbTotal; // Value injected by FXMLLoader

    @FXML // fx:id="lbgst"
    private Label lbgst; // Value injected by FXMLLoader

    @FXML // fx:id="lbGrandTotal"
    private Label lbGrandTotal; // Value injected by FXMLLoader

    @FXML // fx:id="btnEmail"
    private Button btnEmail; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBookingId"
    private ComboBox<Integer> cmbBookingId; // Value injected by FXMLLoader

    @FXML
    private Text txtUserFeedback;

    @FXML
    private Text txtJobStatus;

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);


    @FXML
    void onActionBtnEmail(ActionEvent event) {

    }
    @FXML
    private SplitPane invoicePane;

    @FXML
    void onActionBtnPrint(ActionEvent event) {
        PrintService.print(invoicePane, txtJobStatus);
    }
    private ObservableList<Bookingdetail> data;
    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        dataBindComboBox();
        //adds change event listener to the combobox
        cmbBookingId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            //cmbBookingId.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                //retrieve agent details and display it in the gui
                Bookings booking= BookingsDBHandler.gatBookingsDetails(newValue);
                if (booking != null) {
                    Bookingdetail bookingdetail = BookingdetailDBHandler.gatBookingdetail(booking.getBookingId());
                    Customer customer= CustomerDBHandler.getCustomerDetails(booking.getCustomerId());
                    if (customer != null && bookingdetail != null) {
                        //displayBookingdetail(bookingNo);
                        String invoiceRef = Invoice.generateInvoiceRef(8);

                        StringBuilder addressLine2 = new StringBuilder();
                        addressLine2.append(customer.getCustomerCity())
                                .append(", ")
                                .append(customer.getCustomerProvince())
                                .append(" ")
                                .append(customer.getCustomerPostalCode());

                        lbName.setText(customer.getCustomerFirstName() + " "+customer.getCustomerLastName());
                        lbAddress1.setText(customer.getCustomerAddress() + "");
                        lbAddress2.setText( addressLine2.toString());
                        lbCustPhone.setText(customer.getCustomerHomePhone().trim());
                        lbCustEmail.setText(customer.getCustomerEmail().trim());
                        lbInvoiceNumber.setText(invoiceRef+"-"+booking.getBookingId());
                        //lbEmail.setText(customer.getCustomerEmail());
                        //lbAddress4.setText(customer.getCustomerCountry() + "");
                        //lbPhone.setText(customer.getCustomerHomePhone() + "");
                        //lbEmail.setText(customer.getCustomerEmail() + "");

                        ObservableList<Bookingdetail> detailList = FXCollections.observableArrayList();
                        detailList.add(bookingdetail);
                        col_BookingDetailId.setCellValueFactory(new PropertyValueFactory<>("BookingDetailId"));
                        col_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
                        col_Destination.setCellValueFactory(new PropertyValueFactory<>("Destination"));
                        col_Price.setCellValueFactory(new PropertyValueFactory<>("BasePrice"));
                        tableDetails.setItems(detailList);

                        MathContext mc = new MathContext(4);
                        BigDecimal total = new BigDecimal(0);
                        for (Bookingdetail o : tableDetails.getItems()) {
                            total = total.add(o.getBasePrice(), mc);
                        }
                        BigDecimal gst = new BigDecimal(0);
                        gst= total.multiply(new BigDecimal(0.05), mc);
                        BigDecimal grandTotal = new BigDecimal(0);
                        grandTotal = total.add(gst, mc);
                        lbTotal.setText(currencyFormat.format(total));
                        lbgst.setText(currencyFormat.format(gst));
                        lbGrandTotal.setText(currencyFormat.format(grandTotal));


                    }
                } else {
                    txtUserFeedback.setText("No record found!");
                }

            }
        });


    }

    // private void displayBookingdetail(Bookings bookingNo) {


    // }

    private void dataBindComboBox() {
        ObservableList<Integer> bookingsList = FXCollections.observableArrayList();
        try {
            bookingsList.addAll(BookingsDBHandler.getBookingIds());
            cmbBookingId.setItems(bookingsList);
        } catch (Exception e) {
            txtUserFeedback.setFill(Color.MAROON);
            txtUserFeedback.setText("Unable to connect to the database!");
        }
    }
}







