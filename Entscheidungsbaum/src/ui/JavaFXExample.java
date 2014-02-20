package ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JavaFXExample extends Application {

    public static void main(String[] args) {
        Application.launch(JavaFXExample.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane page = (AnchorPane) FXMLLoader.load(JavaFXExample.class.getResource("Simple.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hello World Sample");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(JavaFXExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
