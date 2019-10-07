package travelexperts.main;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import travelexperts.dbhandler.TravelPackageDBHandler;
import travelexperts.models.Agent;
import travelexperts.models.TravelPackage;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {

    @FXML
    private JFXButton btnCustomers, btnPackage, btnAgents, btnAnalytics, btnHome;

    @FXML
    private AnchorPane displayPane, rootPane;
    @FXML
    private Text txtAgentName;

    private Agent agent;


    //when user clicks on customer button, load customer layout on the right pane
    @FXML
    void onActionBtnCustomers(ActionEvent event) throws IOException {
        Parent customerRoot = FXMLLoader.load(getClass().getResource("customer.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(customerRoot);
    }

    //when user clicks on customer button, load package layout on right pane
    @FXML
    void onActionBtnPackages(ActionEvent event) throws IOException {
        Parent packageRoot = FXMLLoader.load(getClass().getResource("packages.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(packageRoot);

    }

    //when user clicks on customer button, load agents layout on right pane
    @FXML
    void onActionBtnAgents(ActionEvent event) throws IOException {
        Parent agentRoot = FXMLLoader.load(getClass().getResource("agent.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(agentRoot);
    }

    @FXML
    void onActionBtnAnalytics(ActionEvent event) throws IOException {
        Parent analyticsRoot = FXMLLoader.load(getClass().getResource("analytics.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(analyticsRoot);
    }

    public void setUser(Agent agent) { // Setting the client-object in ClientViewController
        this.agent = agent;
        txtAgentName.setText(agent.getAgentFirstName()+" "+agent.getAgentLastName());
    }
    @FXML
    void onActionBtnHome(ActionEvent event) throws IOException {
        Parent agentRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
        rootPane.getChildren().clear();
        rootPane.getChildren().addAll(agentRoot);
    }
}

