package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import controller.ApplicationController;

public class SaveHandler implements EventHandler<ActionEvent> {

	/** Application controller instance of this application */
	ApplicationController controller;

	public SaveHandler(ApplicationController pController) {
		controller = pController;
	}

	/**
	 * Called whenever the save MenuItem was clicked.
	 */
	@Override
	public void handle(ActionEvent pAction) {
		if (!controller.getSavedStatus()) {
			controller.saveCurrentFile();
		}
	}
}
