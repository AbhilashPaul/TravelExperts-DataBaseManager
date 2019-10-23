package travelexperts.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import travelexperts.dbhandler.TravelPackageDBHandler;
import travelexperts.models.TravelPackage;

import java.net.URL;
import java.util.ResourceBundle;


public class TravelPackageController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tablePackage"
    private TableView<TravelPackage> tablePackage; // Value injected by FXMLLoader

    @FXML // fx:id="col_pkgId"
    private TableColumn<TravelPackage, Integer> col_pkgId; // Value injected by FXMLLoader

    @FXML // fx:id="col_pkgName"
    private TableColumn<TravelPackage, String> col_pkgName; // Value injected by FXMLLoader



    @FXML // fx:id="btnLoad"
    //private Button btnLoad; // Value injected by FXMLLoader
    private ObservableList<TravelPackage> data;
  //  private DBConnectionManager dc;
    @FXML
    private AnchorPane packageWorkSpace;



    @FXML
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









