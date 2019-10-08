package travelexperts.main;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.mindrot.jbcrypt.BCrypt;
import travelexperts.dbhandler.CustomerDBHandler;
import travelexperts.models.Customer;

public class CustomerDetailsController {

    @FXML
    private TextField txtCustID,txtCustFirstName,txtCustLastName;

    @FXML
    private TextField txtCustAddress,txtCustCity,txtCustProvince,txtCustCountry,txtPostalCode;

    @FXML
    private TextField txtCustEmail,txtHomePhone,txtBusPhone;

    @FXML
    private TextField txtCustAssignedAgentID,txtCustUserName,txtCustPassword;

    @FXML
    private JFXButton btnEditAddress,btnUpdateLoginCredentials,btnEditAdditional;

    @FXML
    private Button btnSave;

    @FXML
    private Text txtFeedback;

    @FXML
    void onActionBtnEditAdditional(ActionEvent event) {
        setAdditionalInfoTextFieldsEditable(true);
    }

    @FXML
    void onActionBtnEditAddress(ActionEvent event) {
        setAddressTextFieldsEditable(true);
    }

    @FXML
    void onActionBtnEditContact(ActionEvent event) {
        setContactTextFieldsEditable(true);
    }

    @FXML
    void onActionBtnSave(ActionEvent event) {
        txtFeedback.setText("");
        Customer customer = new Customer();
        customer.setCustomerID(Integer.parseInt(txtCustID.getText()));
        customer.setCustomerFirstName(txtCustFirstName.getText());
        customer.setCustomerLastName(txtCustLastName.getText());
        customer.setCustomerAddress(txtCustAddress.getText());
        customer.setCustomerCity(txtCustCity.getText());
        customer.setCustomerProvince(txtCustProvince.getText());
        customer.setCustomerPostalCode(txtPostalCode.getText());
        customer.setCustomerCountry(txtCustCountry.getText());
        customer.setCustomerHomePhone(txtHomePhone.getText());
        customer.setCustomerBusPhone(txtBusPhone.getText());
        customer.setCustomerEmail(txtCustEmail.getText());
        if(CustomerDBHandler.isNumeric(txtCustAssignedAgentID.getText())){
            customer.setAssignedAgentID(Integer.parseInt(txtCustAssignedAgentID.getText()));
        }else{
            customer.setAssignedAgentID(0);
        }
        customer.setCustomerUsername(txtCustUserName.getText());
        customer.setCustomerPassword(BCrypt.hashpw(txtCustPassword.getText().trim(),BCrypt.gensalt()));

        //update database
        if(CustomerDBHandler.updateCustomerDetails(customer)){
            txtFeedback.setFill(Color.GREEN);
            txtFeedback.setText("Successfully updated customer details!");
            setAdditionalInfoTextFieldsEditable(false);
            setContactTextFieldsEditable(false);
            setAddressTextFieldsEditable(false);

        }else{
            txtFeedback.setFill(Color.MAROON);
            txtFeedback.setText("Error while trying to update customer details. Please try again! ");
        }
    }

    private void setAdditionalInfoTextFieldsEditable(boolean b) {
        txtCustAssignedAgentID.setEditable(b);
        txtCustUserName.setEditable(b);
        txtCustPassword.setEditable(b);
    }

    private void setAddressTextFieldsEditable(boolean b) {
        txtCustAddress.setEditable(b);
        txtCustCity.setEditable(b);
        txtCustCountry.setEditable(b);
        txtCustProvince.setEditable(b);
        txtPostalCode.setEditable(b);
    }

    private void setContactTextFieldsEditable(boolean b) {
        txtCustEmail.setEditable(b);
        txtBusPhone.setEditable(b);
        txtHomePhone.setEditable(b);
    }

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
