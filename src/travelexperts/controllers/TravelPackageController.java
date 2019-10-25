/**
 * Sample Skeleton for 'TravelPackage.fxml' Controller Class
 */

package travelexperts.controllers;

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
import travelexperts.dbhandler.DBConnectionManager;
import travelexperts.dbhandler.TravelPackageDBHandler;
import travelexperts.models.TravelPackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TravelPackageController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tpAddPackage"
    private Tab tpAddPackage; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageId"
    private TextField tfPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageDescripion"
    private TextField tfPackageDescripion; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageStartDate"
    private TextField tfPackageStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageBasePrice"
    private TextField tfPackageBasePrice; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageName"
    private TextField tfPackageName; // Value injected by FXMLLoader

    @FXML // fx:id="tfTripTypeId"
    private TextField tfTripTypeId; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageEndDate"
    private TextField tfPackageEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="tfPackageAgencyCommission"
    private TextField tfPackageAgencyCommission; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddPackage"
    private Button btnAddPackage; // Value injected by FXMLLoader
    @FXML // fx:id="dpStartDate"
    private DatePicker dpStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="dpEndDate"
    private DatePicker dpEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="tpPackages"
    private Tab tpPackages; // Value injected by FXMLLoader

    @FXML // fx:id="tablePackage"
    private TableView<TravelPackage> tablePackage; // Value injected by FXMLLoader

    @FXML // fx:id="col_pkgId"
    private TableColumn<TravelPackage, Integer> col_pkgId; // Value injected by FXMLLoader

    @FXML // fx:id="col_pkgName"
    private TableColumn<TravelPackage, String> col_pkgName; // Value injected by FXMLLoader

    @FXML
    private TabPane tp;
    @FXML
    private Button btnBack;
    @FXML
    void onActionBtnBack(ActionEvent event) {
        tp.getSelectionModel().select(tpPackages);
    }


    @FXML
    void onActiondpStartDate(ActionEvent event) {
        LocalDate ld = dpStartDate.getValue();
   tfPackageStartDate.setText(ld.toString());
    }


    @FXML
    void onActiondpEndDtae(ActionEvent event) {
        LocalDate ld = dpEndDate.getValue();
        tfPackageEndDate.setText(ld.toString());
    }


    @FXML
    void onActionBtnAddPackage(ActionEvent event) throws SQLException {

        String sql = "Insert into packages (PkgName,PkgStartDate,PkgEndDate,PkgDesc,PkgBasePrice,PkgAgencyCommission,TriptypeId) Values (?,?,?,?,?,?,?) ";
        int rows;
        Connection connection = DBConnectionManager.getDBConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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


    private ObservableList<TravelPackage> data;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        data = FXCollections.observableArrayList();
        data = TravelPackageDBHandler.gatAllPackages();
        col_pkgId.setCellValueFactory(new PropertyValueFactory<>("pkgId"));
        col_pkgName.setCellValueFactory(new PropertyValueFactory<>("pkgName"));
        tablePackage.setItems(data);

        TableView.TableViewSelectionModel<TravelPackage> tablePackageSelect = tablePackage.getSelectionModel();
        tablePackageSelect.selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/packagedetails.fxml"));

                try {
                    Parent root = loader.load();
                    Scene detailsScene = new Scene(root, 761, 553);
                    Stage detailsStage = new Stage();
                    detailsStage.setTitle("Package Details");
                    detailsStage.setScene(detailsScene);
                    detailsStage.setMaximized(false);


                    PackageDetailsController detailsController = loader.getController();
                    detailsController.displayPackageDetails(data.get((int) newValue));
                    detailsStage.showAndWait();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        });
    }
}

