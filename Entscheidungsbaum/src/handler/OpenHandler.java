package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OpenHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent pAction) {
		System.out.println("Open a file");
	}
}
