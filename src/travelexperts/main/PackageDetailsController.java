/**
 * Sample Skeleton for 'packagedetails.fxml' Controller Class
 */

package travelexperts.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import travelexperts.dbhandler.DBConnectionManager;
import travelexperts.models.TravelPackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

;


public class PackageDetailsController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfPackageId"
    private TextField tfPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageName"
    private TextField tfPackageName; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageStartDate"
    private TextField tfPackageStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageEndDate"
    private TextField tfPackageEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageDescripion"
    private TextField tfPackageDescripion; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageBasePrice"
    private TextField tfPackageBasePrice; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageAgencyCommission"
    private TextField tfPackageAgencyCommission; // Value injected by FXMLLoader

    @FXML // fx:id="tfTripTypeId"
    private TextField tfTripTypeId; // Value injected by FXMLLoader

    @FXML // fx:id="btnEdit"
    private Button btnEdit; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML
    private Button btnAddPackage;

    @FXML
    void onActionBtnAddPackage(ActionEvent event) throws SQLException {
        btnAddPackage.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        String sql = "Insert into packages (PackageId,PkgName,PkgStartDate,PkgEndDate,PkgDesc,PkgBasePrice,PkgAgencyCommission,TriptypeId) Values (?,?,?,?,?,?,?,?) ";
        int rows;
        Connection connection = DBConnectionManager.getDBConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(tfPackageId.getText()));
            statement.setString(2, tfPackageName.getText());
            statement.setDate(3, Date.valueOf(tfPackageStartDate.getText()));
            statement.setDate(4, Date.valueOf(tfPackageEndDate.getText()));
            statement.setString(5, tfPackageDescripion.getText());
            statement.setDouble(6, Double.parseDouble(tfPackageBasePrice.getText()));
            statement.setDouble(7, Double.parseDouble(tfPackageAgencyCommission.getText()));
            statement.setString(8, tfTripTypeId.getText());
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
    void onActionBtnEdit(ActionEvent event) throws SQLException {
        btnEdit.setDisable(true);
        tfPackageId.setEditable(true);
        tfPackageName.setEditable(true);
        tfPackageStartDate.setEditable(true);
        tfPackageEndDate.setEditable(true);
        tfPackageDescripion.setEditable(true);
        tfPackageBasePrice.setEditable(true);
        tfPackageAgencyCommission.setEditable(true);
        tfTripTypeId.setEditable(true);
        btnSave.setDisable(false);
        btnAddPackage.setDisable(false);

    }


    @FXML
    void onActionBtnSave(ActionEvent event)  throws SQLException {
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        String sql = "UPDATE `packages` SET `PkgName`=? , `PkgStartDate`=? , `PkgEndDate` =?, `PkgDesc` =?, `PkgBasePrice` =?, `PkgAgencyCommission`=?, `TriptypeId` = ? WHERE `PackageId`= ?";
        int rows;
        Connection connection = DBConnectionManager.getDBConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(8, Integer.parseInt(tfPackageId.getText()));
            statement.setString(1, tfPackageName.getText());
            statement.setDate(2, Date.valueOf(tfPackageStartDate.getText()));
            statement.setDate(3, Date.valueOf(tfPackageEndDate.getText()));
            statement.setString(4, tfPackageDescripion.getText());
            statement.setDouble(5, Double.parseDouble(tfPackageBasePrice.getText()));
            statement.setDouble(6, Double.parseDouble(tfPackageAgencyCommission.getText()));
            statement.setString(7, tfTripTypeId.getText());
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
        assert tfPackageId != null : "fx:id=\"tfPackageId\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageName != null : "fx:id=\"tfPackageName\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageStartDate != null : "fx:id=\"tfPackageStartDate\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageEndDate != null : "fx:id=\"tfPackageEndDate\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageDescripion != null : "fx:id=\"tfPackageDescripion\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageBasePrice != null : "fx:id=\"tfPackageBasePrice\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageAgencyCommission != null : "fx:id=\"tfPackageAgencyCommission\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'packagedetails.fxml'.";

        tfPackageId.setEditable(false);  //make text fields non-editable
        tfPackageName.setEditable(false);
        tfPackageStartDate.setEditable(false);
        tfPackageEndDate.setEditable(false);
        tfPackageDescripion.setEditable(false);
        tfPackageBasePrice.setEditable(false);
        tfPackageAgencyCommission.setEditable(false);
        tfTripTypeId.setEditable(false);
        btnEdit.setDisable(false);          //enable edit button
        btnSave.setDisable(true);
        btnAddPackage.setDisable(true);
    }

    public void displayPackageDetails(TravelPackage travelPackage) {
        tfPackageId.setText(travelPackage.getPkgId() + "");
        tfPackageName.setText(travelPackage.getPkgName() + "");
        tfPackageStartDate.setText(travelPackage.getPkgStartDate() + "");
        tfPackageEndDate.setText(travelPackage.getPkgEndDate()   + "");
        tfPackageDescripion.setText(travelPackage.getPkgDesc());
        tfPackageBasePrice.setText(travelPackage.getPkgBasePrice() + "");
        tfPackageAgencyCommission.setText(travelPackage.getPkgCommission() + "");
        tfTripTypeId.setText(travelPackage.getPkgTripType() + "");

    }


}

