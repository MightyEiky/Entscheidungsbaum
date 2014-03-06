package ui;

import handler.ApplicationCloseHandler;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controller.ApplicationController;

/**
 * UserInterface of Entscheidungsbaum.
 * 
 * @author Julius
 * 
 */
public class UserInterface extends Application {
	@Override
	public void start(Stage pStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL location = UserInterface.class.getResource("GUI.fxml");
		fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		fxmlLoader.setLocation(location);
		AnchorPane pane = (AnchorPane) fxmlLoader.load(location.openStream());

		Scene scene = new Scene(pane);
		pStage.setScene(scene);
		pStage.setTitle("Entscheidungsbaum");
		pStage.show();
		pStage.setOnCloseRequest(new ApplicationCloseHandler((ApplicationController) fxmlLoader.getController()));
	}
}
