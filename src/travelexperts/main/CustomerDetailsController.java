package travelexperts.main;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import travelexperts.models.Customer;

public class CustomerDetailsController {

    @FXML
    private TextArea txtCustomerDetails;

    public void displayCustomerDetails(Customer cust){
        txtCustomerDetails.setText(cust.toString());
    }
}
