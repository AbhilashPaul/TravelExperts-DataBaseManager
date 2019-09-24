package travelexperts.main;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class HomeController {

    @FXML
    private JFXButton btnCustomers, btnPackage, btnAgents;

    @FXML
    private AnchorPane displayPane;

    //when user clicks on customer button, load customer layout on the right pane
    @FXML
    void onActionBtnCustomers(ActionEvent event) throws IOException {
        Parent customerRoot = FXMLLoader.load(getClass().getResource("customer.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(customerRoot);
    }

    //when user clicks on customer button, load package layout on right pane
    @FXML
    void onActionBtnPackages(ActionEvent event) {

    }

    //when user clicks on customer button, load agents layout on right pane
    @FXML
    void onActionBtnAgents(ActionEvent event) throws IOException {
        Parent agentRoot = FXMLLoader.load(getClass().getResource("agent.fxml"));
        displayPane.getChildren().clear();
        displayPane.getChildren().addAll(agentRoot);
    }


}

