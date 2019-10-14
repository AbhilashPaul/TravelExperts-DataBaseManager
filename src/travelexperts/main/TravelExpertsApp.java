package travelexperts.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import travelexperts.models.Agent;

public class TravelExpertsApp extends Application {

    private static Agent user;
    private static Stage primaryStage;

    public static Agent getUser() {
        return user;
    }
    public static void setUser(Agent user) {
        TravelExpertsApp.user = user;
    }
    public static Stage getPrimaryStage() { return primaryStage; }
    public static void setPrimaryStage(Stage primaryStage) { TravelExpertsApp.primaryStage = primaryStage; }

    //loads the login page when user start the application
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent homeRoot = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        Scene loginScene = new Scene(homeRoot, 1000, 650);
        loginScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setTitle("Travel Experts DataBase Manager");
        primaryStage.setScene(loginScene);
        primaryStage.setMaximized(false);
        primaryStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        TravelExpertsApp.setPrimaryStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
