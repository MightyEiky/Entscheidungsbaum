package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import controller.ApplicationController;

/**
 * Implementation of the EventHandler for the quit MenuItem. <br>
 * Closes the application via the application controller.
 * 
 * @author Julius
 * 
 */
public class QuitHandler implements EventHandler<ActionEvent> {

	/** ApplicationController instance of the application. */
	private ApplicationController controller;

	/**
	 * Constructor.
	 * 
	 * @param pController
	 *            ApplicationController instance of the application
	 */
	public QuitHandler(ApplicationController pController) {
		controller = pController;
	}

	@Override
	public void handle(ActionEvent pAction) {
		if (!controller.getSavedStatus()) {
			controller.showUnsavedChangesDialog(ApplicationController.CONTEXT_CLOSE_APPLICATION);
			pAction.consume();
		} else {
			controller.quit();
		}
	}
}
