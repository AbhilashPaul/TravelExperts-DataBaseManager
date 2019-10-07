package travelexperts.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    //loads the login page when user start the application
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent homeRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(homeRoot, 1000, 650);
        loginScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setTitle("Travel Experts DataBase Manager");
        primaryStage.setScene(loginScene);
        primaryStage.setMaximized(false);
        primaryStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
