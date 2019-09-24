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

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameinput;

    @FXML
    private PasswordField passwordinput;

    @FXML
    private AnchorPane rootPane;

    //verify username and password on user sign in
    @FXML
    void handleSubmitButtonAction(ActionEvent event) throws IOException {
        //get user entered user name and password
        String username = usernameinput.getText();
        String password = passwordinput.getText();

        //use BCrypt algorithm to check if the entered password matches with password stored in db
        String storedPassword = AgentDBHandler.Login(username);
        if(storedPassword != null){
            String hash_pword = storedPassword.replaceFirst("2y", "2a");
            if(BCrypt.checkpw(password,hash_pword)){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent root = (Parent)loader.load();
                Stage stage = (Stage) rootPane.getScene().getWindow();
                Scene homeScene = new Scene(root, 1000, 650);
                stage.setScene(homeScene);
                stage.show();
            }else{
                //display error message
                new Alert(Alert.AlertType.ERROR, "Invalid Username/Password. Please try again!").showAndWait();
            }
        }else{
            //display error message
            new Alert(Alert.AlertType.ERROR, "Invalid Username/Password. Please try again!").showAndWait();
        }
    }

}


