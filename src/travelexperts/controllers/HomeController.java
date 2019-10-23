package travelexperts.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import travelexperts.main.TravelExpertsApp;
import travelexperts.models.AgenciesUtil;
import travelexperts.models.Agent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class HomeController {

    @FXML
    private JFXButton btnCustomers, btnPackage, btnAgents, btnBookings, btnHome;

    @FXML
    private Button btnLogout;

    @FXML
    private AnchorPane displayPane, rootPane;
    @FXML
    private Text txtAgentName, txtAgentPosition,txtAgentAgency;

    private Agent loggedInAgent;

    @FXML
    private ImageView agentImage;

    @FXML
    void initialize() throws IOException {
        Parent analyticsRoot = FXMLLoader.load(getClass().getResource("../views/analytics.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(analyticsRoot);
        //get the agent details
        loggedInAgent= TravelExpertsApp.getUser();
        String url = "../images/"+loggedInAgent.getAgentFirstName().toLowerCase()+loggedInAgent.getAgentLastName().toLowerCase()+"_tn.jpg";
        Image newImage = new Image(getClass().getResource(url).toExternalForm());
        agentImage.setImage(newImage);
        txtAgentName.setText(loggedInAgent.getAgentFirstName()+" "+loggedInAgent.getAgentLastName());
        txtAgentPosition.setText(loggedInAgent.getAgentPosition());
        HashMap<Integer,String> agencies = AgenciesUtil.createAgencyList();
        txtAgentAgency.setText(AgenciesUtil.getAgencyName(agencies,loggedInAgent.getAgentAgencyID()));
    }



    //when user clicks on customer button, load customer layout on the right pane
    @FXML
    void onActionBtnCustomers(ActionEvent event) throws IOException {
        Parent customerRoot = FXMLLoader.load(getClass().getResource("../views/customer.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(customerRoot);
    }

    //when user clicks on customer button, load package layout on right pane
    @FXML
    void onActionBtnPackages(ActionEvent event) throws IOException {
        Parent packageRoot = FXMLLoader.load(getClass().getResource("../views/TravelPackage.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(packageRoot);

    }

    //when user clicks on customer button, load agents layout on right pane
    @FXML
    void onActionBtnAgents(ActionEvent event) throws IOException {
        Parent agentRoot = FXMLLoader.load(getClass().getResource("../views/agent.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(agentRoot);
    }

    @FXML
    void onActionBtnBookings(ActionEvent event) throws IOException {
        Parent agentRoot = FXMLLoader.load(getClass().getResource("../views/Booking.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(agentRoot);
    }

    @FXML
    void onActionBtnHome(ActionEvent event) throws IOException {
        Parent analyticsRoot = FXMLLoader.load(getClass().getResource("../views/analytics.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(analyticsRoot);
    }

    @FXML
    void onActionBtnLogout(ActionEvent event) throws IOException {
        TravelExpertsApp.setUser(null);
        Stage primaryStage = TravelExpertsApp.getPrimaryStage();
        Parent loginRoot = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        Scene loginScene = new Scene(loginRoot, 1000, 650);
        loginScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setTitle("Travel Experts DataBase Manager");
        primaryStage.setScene(loginScene);
        primaryStage.setMaximized(false);
        primaryStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    public void onActionBtnInvoice(ActionEvent actionEvent) throws IOException {
        Parent invoiceRoot;
        invoiceRoot = FXMLLoader.load(getClass().getResource("../views/invoicegenerator.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(invoiceRoot );
    }
}

