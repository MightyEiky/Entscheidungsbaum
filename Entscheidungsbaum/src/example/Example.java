package example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Example extends Application {
	@Override
	public void start(Stage pStage) throws Exception {
		AnchorPane pane = FXMLLoader.load(Example.class.getResource("Example.fxml"));
		Scene scene = new Scene(pane);
		pStage.setScene(scene);
		pStage.setTitle("Example");
		pStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
