package travelexperts.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.mindrot.jbcrypt.BCrypt;
import travelexperts.dbhandler.CustomerDBHandler;
import travelexperts.models.Customer;
import travelexperts.utils.InputValidator;

public class CustomerDetailsController {

    Customer selectedCustomer;

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

    //event handlers for edit buttons
    @FXML
    void onActionBtnEditAdditional(ActionEvent event) {
        setAdditionalInfoTextFieldsEditable(true);
        txtFeedback.setText("");
        btnSave.setDisable(false);
    }

    @FXML
    void onActionBtnEditAddress(ActionEvent event) {
        setAddressTextFieldsEditable(true);
        txtFeedback.setText("");
        btnSave.setDisable(false);
    }

    @FXML
    void onActionBtnEditContact(ActionEvent event) {
        setContactTextFieldsEditable(true);
        txtFeedback.setText("");
        btnSave.setDisable(false);
    }

    //handles updating the data base with changes in the text fields
    @FXML
    void onActionBtnSave(ActionEvent event) {
        if(validateInputs()) {
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
            if (InputValidator.isNumeric(txtCustAssignedAgentID.getText())) {
                customer.setAssignedAgentID(Integer.parseInt(txtCustAssignedAgentID.getText()));
            } else {
                customer.setAssignedAgentID(0);
            }
            customer.setCustomerUsername(txtCustUserName.getText());
            if (InputValidator.isPresent(txtCustPassword)) {
                customer.setCustomerPassword(BCrypt.hashpw(txtCustPassword.getText().trim(), BCrypt.gensalt()));
            } else {
                customer.setCustomerPassword(selectedCustomer.getCustomerPassword());
            }

            //update database
            if (CustomerDBHandler.updateCustomerDetails(customer)) {
                txtFeedback.setFill(Color.GREEN);
                txtFeedback.setText("Successfully updated customer details!");
                setAdditionalInfoTextFieldsEditable(false);
                setContactTextFieldsEditable(false);
                setAddressTextFieldsEditable(false);
                btnSave.setDisable(true);
            } else {
                txtFeedback.setFill(Color.MAROON);
                txtFeedback.setText("Error while trying to update customer details. Please try again! ");
            }
        }
    }

    //methods to make text fields editable
    //additional info section
    private void setAdditionalInfoTextFieldsEditable(boolean b) {
        txtCustAssignedAgentID.setEditable(b);
        txtCustUserName.setEditable(b);
        txtCustPassword.setEditable(b);
    }
    //address section
    private void setAddressTextFieldsEditable(boolean b) {
        txtCustAddress.setEditable(b);
        txtCustCity.setEditable(b);
        txtCustCountry.setEditable(b);
        txtCustProvince.setEditable(b);
        txtPostalCode.setEditable(b);
    }
    //contacts section
    private void setContactTextFieldsEditable(boolean b) {
        txtCustEmail.setEditable(b);
        txtBusPhone.setEditable(b);
        txtHomePhone.setEditable(b);
    }

    //this method is used to display the customer details
    public void displayCustomerDetails(Customer cust){
        selectedCustomer=cust;
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

    //validate inputs
    private boolean validateInputs(){

        if(InputValidator.isPresent(txtCustCity) &&
                InputValidator.isTextOnly(txtCustCity.getText())){

            if(InputValidator.isPresent(txtCustProvince) &&
                    InputValidator.isTextOnly(txtCustProvince.getText())){

                if(InputValidator.isPresent(txtCustCountry) &&
                        InputValidator.isTextOnly(txtCustCountry.getText())){

                    if(InputValidator.isPresent(txtPostalCode) &&
                            InputValidator.validatePostalCode(txtPostalCode.getText())){

                        if(!InputValidator.isPresent(txtCustEmail) ||
                                InputValidator.validateEmail(txtCustEmail.getText())){

                            if(InputValidator.isPresent(txtHomePhone) &&
                                    InputValidator.validatePhoneNumber(txtHomePhone.getText())){

                                if(!InputValidator.isPresent(txtBusPhone) ||
                                        InputValidator.validatePhoneNumber(txtBusPhone.getText())){

                                    if(!InputValidator.isPresent(txtCustUserName) ||
                                            InputValidator.validateUserName(txtCustUserName.getText())){
                                        return true;
                                    }else{
                                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid username. Allowed charcters: Letters, Numbers and Special Characters: underscore(_), period(.) and hyphen(-)", ButtonType.OK);
                                        alert.show();
                                        return false;
                                    }

                                }else{
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid business phone number", ButtonType.OK);
                                    alert.show();
                                    return false;
                                }

                            }else{
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid home phone number.", ButtonType.OK);
                                alert.show();
                                return false;
                            }
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid Email!", ButtonType.OK);
                            alert.show();
                            return false;
                        }
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid Postal Code!", ButtonType.OK);
                        alert.show();
                        return false;
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid country name", ButtonType.OK);
                    alert.show();
                    return false;
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid province", ButtonType.OK);
                alert.show();
                return false;
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid city name.", ButtonType.OK);
            alert.show();
            return false;
        }
    }
}
