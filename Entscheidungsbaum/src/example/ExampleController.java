package example;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ExampleController {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Button pane1button;
	@FXML
	private Button pane2button;

	@FXML
	public void initialize() {
		pane1button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				pane2.setVisible(true);
				pane2button.setVisible(true);
				System.out.println("hmm");
			}
		});

		pane2button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				pane2.setVisible(false);
				pane2button.setVisible(false);
				System.out.println("hmm");
			}
		});
	}
}
