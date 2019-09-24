package exercise6.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent customerRoot = FXMLLoader.load(getClass().getResource("customer.fxml"));
        Scene customerScene = new Scene(customerRoot, 700, 550);
        customerScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setTitle("Agent Data Manager");
        primaryStage.setScene(customerScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
