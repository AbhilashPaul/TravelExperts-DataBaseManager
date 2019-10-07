package travelexperts.main;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import travelexperts.models.Customer;

public class CustomerDetailsController {

    @FXML
    private TextField txtCustID;

    @FXML
    private TextField txtCustFirstName;

    @FXML
    private TextField txtCustLastName;

    @FXML
    private TextField txtCustAddress;

    @FXML
    private TextField txtCustCity;

    @FXML
    private TextField txtCustProvince;

    @FXML
    private TextField txtCustCountry;

    @FXML
    private TextField txtCustEmail;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtHomePhone;

    @FXML
    private TextField txtBusPhone;

    @FXML
    private TextField txtCustAssignedAgentID;

    @FXML
    private TextField txtCustUserName;

    @FXML
    private TextField txtCustPassword;

    public void displayCustomerDetails(Customer cust){
        txtCustID.setText(String.valueOf(cust.getCustomerID()));
        txtCustFirstName.setText(cust.getCustomerFirstName());
        txtCustLastName.setText(cust.getCustomerLastName());
        txtCustAddress.setText(cust.getCustomerAddress());
        txtPostalCode.setText(cust.getCustomerPostalCode());
        txtHomePhone.setText(cust.getCustomerHomePhone());
        txtCustCity.setText(cust.getCustomerCity());
        txtCustEmail.setText(cust.getCustomerEmail());
        txtBusPhone.setText(cust.getCustomerBusPhone());
        txtCustProvince.setText(cust.getCustomerProvince());
        txtCustCountry.setText(cust.getCustomerCountry());
        txtCustAssignedAgentID.setText(String.valueOf(cust.getAssignedAgentID()));
        txtCustUserName.setText(String.valueOf(cust.getCustomerUsername()));
    }
}
