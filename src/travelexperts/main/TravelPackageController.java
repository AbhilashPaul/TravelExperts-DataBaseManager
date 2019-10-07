package travelexperts.main;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import travelexperts.dbhandler.DBConnectionManager;
import travelexperts.dbhandler.TravelPackageDBHandler;
import travelexperts.models.TravelPackage;




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
    private Button btnLoad; // Value injected by FXMLLoader
    private ObservableList<TravelPackage> data;
    private DBConnectionManager dc;

    @FXML
    void initialize() {
        data = FXCollections.observableArrayList();
        data = TravelPackageDBHandler.gatAllPackages();
        col_pkgId.setCellValueFactory(new PropertyValueFactory<>("pkgId"));
        col_pkgName.setCellValueFactory(new PropertyValueFactory<>("pkgName"));
        tablePackage.setItems(data);
    }

    @FXML
    void loadDataFromDatabase(ActionEvent event) throws SQLException {


    }


    public Button getBtnLoad() {
        return btnLoad;
    }

    public void setBtnLoad(Button btnLoad) {
        this.btnLoad = btnLoad;
    }
}
