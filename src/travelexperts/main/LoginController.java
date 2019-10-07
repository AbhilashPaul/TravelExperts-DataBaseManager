package travelexperts.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import travelexperts.dbhandler.AgentDBHandler;
import travelexperts.models.Agent;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameinput;

    @FXML
    private PasswordField passwordinput;

    @FXML
    private AnchorPane rootPane;

    Agent loggedinAgent = new Agent();

    //verify username and password on user sign in
    @FXML
    void handleSubmitButtonAction(ActionEvent event) throws IOException {
        //get user entered user name and password
        String username = usernameinput.getText();
        String password = passwordinput.getText();

        //use BCrypt algorithm to check if the entered password matches with password stored in db
        String storedPassword = AgentDBHandler.Login(username);
        if(storedPassword != null){
            String hash_password;

            // Change the hash identifier if necessary
            // Difference in hashed password occurs if the customer uses the webform to register.
            // BCrypt in PHP hashes passwords using hash using the "$2y$" identifier while java uses "$2a$" identifier
            String hashIdentifier =storedPassword.substring(0,4);
            if( hashIdentifier.equals("$2y$")){
                hash_password = storedPassword.replaceFirst("2y", "2a");
            }else{
                hash_password = storedPassword;
            }
            //load the home window if the passwords match
            if(BCrypt.checkpw(password,hash_password)){
                //get details of the agent and store it in an agent object
                createAgentObject(username);
                //load the home window
                loadTheHomeWindow();
            }else{
                //display error message
                new Alert(Alert.AlertType.ERROR, "Invalid Username/Password. Please try again!").showAndWait();
            }
        }else{
            //display error message
            new Alert(Alert.AlertType.ERROR, "Invalid Username/Password. Please try again!").showAndWait();
        }
    }


    private void createAgentObject(String username) {
        if(AgentDBHandler.getAgentDetailsByUsername(username) != null){
            loggedinAgent = AgentDBHandler.getAgentDetailsByUsername(username);
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Username/Password. Please try again!").showAndWait();
        }
    }

    private void loadTheHomeWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = (Parent)loader.load();

        HomeController homeController = loader.getController();
        homeController.setUser(loggedinAgent);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        Scene homeScene = new Scene(root, 1000, 650);
        stage.setScene(homeScene);
        stage.show();
    }
}


