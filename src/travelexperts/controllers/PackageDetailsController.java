/**
 * Sample Skeleton for 'packagedetails.fxml' Controller Class
 */

package travelexperts.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import travelexperts.dbhandler.DBConnectionManager;
import travelexperts.models.TravelPackage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PackageDetailsController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfPackageId"
    private TextField tfPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageName"
    private TextField tfPackageName; // Value injected by FXMLLoader

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
    private DatePicker dpTripStart;

    @FXML
    private DatePicker dpTripEnd;

    @FXML
    void onActionDatePickerTripEnd(ActionEvent event) {
        LocalDate ld = dpTripStart.getValue();
    }

    @FXML
    void onActionDatePickerTripStart(ActionEvent event) {
        LocalDate ld = dpTripStart.getValue();
    }

    @FXML
    void onActionBtnEdit(ActionEvent event) {
        btnEdit.setDisable(true);
        tfPackageId.setEditable(true);
        tfPackageName.setEditable(true);
        tfPackageDescripion.setEditable(true);
        tfPackageBasePrice.setEditable(true);
        tfPackageAgencyCommission.setEditable(true);
        tfTripTypeId.setEditable(true);
        btnSave.setDisable(false);

    }

    @FXML
    void onActionBtnSave(ActionEvent event) throws SQLException, IOException {
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        String sql = "UPDATE `packages` SET `PkgName`=? , `PkgStartDate`=? , `PkgEndDate` =?, `PkgDesc` =?, `PkgBasePrice` =?, `PkgAgencyCommission`=?, `TriptypeId` = ? WHERE `PackageId`= ?";
        int rows;
        Connection connection = DBConnectionManager.getDBConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(8, Integer.parseInt(tfPackageId.getText()));
            statement.setString(1, tfPackageName.getText());
            statement.setDate(2, Date.valueOf(dpTripStart.getValue()));
            statement.setDate(3, Date.valueOf(dpTripEnd.getValue()));
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
            btnEdit.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful", ButtonType.OK);
            alert.show();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tfPackageId != null : "fx:id=\"tfPackageId\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageName != null : "fx:id=\"tfPackageName\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageDescripion != null : "fx:id=\"tfPackageDescripion\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageBasePrice != null : "fx:id=\"tfPackageBasePrice\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfPackageAgencyCommission != null : "fx:id=\"tfPackageAgencyCommission\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert tfTripTypeId != null : "fx:id=\"tfTripTypeId\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'packagedetails.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'packagedetails.fxml'.";
        tfPackageId.setEditable(false);  //make text fields non-editable
        tfPackageName.setEditable(false);
        tfPackageDescripion.setEditable(false);
        tfPackageBasePrice.setEditable(false);
        tfPackageAgencyCommission.setEditable(false);
        tfTripTypeId.setEditable(false);
        btnEdit.setDisable(false);          //enable edit button
        btnSave.setDisable(true);
    }


        public void displayPackageDetails(TravelPackage travelPackage) {
            tfPackageId.setText(travelPackage.getPkgId() + "");
            tfPackageName.setText(travelPackage.getPkgName() + "");
            tfPackageDescripion.setText(travelPackage.getPkgDesc());
            dpTripStart.setValue(LOCAL_DATE(travelPackage.getPkgStartDate().toString()));
            dpTripEnd.setValue(LOCAL_DATE(travelPackage.getPkgEndDate().toString()));
            tfPackageBasePrice.setText(travelPackage.getPkgBasePrice() + "");
            tfPackageAgencyCommission.setText(travelPackage.getPkgCommission() + "");
            tfTripTypeId.setText(travelPackage.getPkgTripType() + "");

        }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    }

