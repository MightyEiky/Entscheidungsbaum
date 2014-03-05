package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * UserInterface of Entscheidungsbaum.
 * 
 * @author Julius
 * 
 */
public class UserInterface extends Application {
	@Override
	public void start(Stage pStage) throws Exception {
		VBox pane = (VBox) FXMLLoader.load(UserInterface.class.getResource("GUI.fxml"));
		Scene scene = new Scene(pane);
		pStage.setScene(scene);
		pStage.setTitle("Entscheidungsbaum");
		pStage.show();
	}
}
