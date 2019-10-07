package travelexperts.main;

import com.jfoenix.controls.JFXSpinner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import travelexperts.dbhandler.CustomerDBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import travelexperts.models.Customer;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private JFXSpinner spinnerSearch;
    @FXML
    private TableView<Customer> tableCustomer;
    @FXML
    private TableColumn<Customer, Integer> col_id;
    @FXML
    private TableColumn<Customer, String> col_firstname, col_lastname, col_homephone, col_email;

    ObservableList<Customer> customerList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //link columns in table view to the properties of customer object
        col_id.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("customerFirstName"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("customerLastName"));
        col_homephone.setCellValueFactory(new PropertyValueFactory<>("customerHomePhone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));

        //add a hint to the search box
        Tooltip searchHint = new Tooltip();
        searchHint.setText("Enter customer ID or first name and/or last name to retrieve customer record.");
        txtSearch.setTooltip(searchHint);

        //get customer records and display it on a table view
        customerList = CustomerDBHandler.getCustomers(txtSearch.getText());
        tableCustomer.setItems(customerList);
        tableCustomer.getSortOrder().setAll(col_id); //sort the table view display by customer id ascending

        //add an event listener to the table view
        //display the customer details in a separate window when user clicks on a particular customer in the table view
        TableView.TableViewSelectionModel<Customer> tableCustomerSelect = tableCustomer.getSelectionModel();
        tableCustomerSelect.selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //load the customer details ona  new window
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customerdetail.fxml"));
                try{
                    Parent root = loader.load();
                    Scene detailsScene = new Scene(root, 1000, 650);
                    Stage detailsStage = new Stage();
                    detailsStage.setTitle("Customer Details");
                    detailsStage.setScene(detailsScene);
                    detailsStage.setMaximized(false);

                    CustomerDetailsController detailsController = loader.getController();
                    detailsController.displayCustomerDetails(customerList.get((int)newValue));
                    detailsStage.showAndWait();

                    //not used
                    /*Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Customer Details");
                    alert.setResizable(true);
                    alert.setDialogPane(root);
                    alert.initModality(Modality.WINDOW_MODAL);
                    Window window = alert.getDialogPane().getScene().getWindow();
                    window.setOnCloseRequest(event -> window.hide());
                    alert.showAndWait();*/

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    //search for customer with key words or id
    @FXML
    void onActionBtnSearch(ActionEvent event) throws InterruptedException {
        tableCustomer.getItems().clear();
        spinnerSearch.setOpacity(1);
        customerList = CustomerDBHandler.getCustomers(txtSearch.getText());
        //show an error message if no record was found
        if(customerList.size() == 0){
            new Alert(Alert.AlertType.ERROR, "No Record found!").showAndWait();
        }else { //else, display all of the customer records in the table view
            tableCustomer.setItems(customerList);
            //sort the table view display by customer id ascending
            tableCustomer.getSortOrder().setAll(col_id);
        }
        spinnerSearch.setOpacity(0);
    }

}
