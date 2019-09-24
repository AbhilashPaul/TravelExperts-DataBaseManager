package travelexperts.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //loads the login page when user start teh application
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent homeRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(homeRoot, 1000, 650);
        loginScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setTitle("Travel Experts DataBase Manager");
        primaryStage.setScene(loginScene);
        primaryStage.setMaximized(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
