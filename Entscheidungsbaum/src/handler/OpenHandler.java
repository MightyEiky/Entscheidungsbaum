package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import controller.ApplicationController;

public class OpenHandler implements EventHandler<ActionEvent> {

	/** Application controller instance */
	private ApplicationController controller;

	/**
	 * Constructor.
	 * 
	 * @param pController
	 *            ApplicationController instance of application
	 */
	public OpenHandler(ApplicationController pController) {
		controller = pController;
	}

	/**
	 * Allows the user to choose a CSV file via FileChooser to populate the
	 * TableView with its data.
	 */
	@Override
	public void handle(ActionEvent pAction) {
		if (!controller.getSavedStatus()) {
			controller.showUnsavedChangesDialog(ApplicationController.CONTEXT_OPEN_FILE);
		} else {
			controller.openFile();
		}
	}
}
