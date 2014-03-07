package handler;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import controller.ApplicationController;

/**
 * 
 * @author Julius
 * 
 */
public class ApplicationCloseHandler implements EventHandler<WindowEvent> {

	/**
	 * Application controller instance of this application.
	 */
	private ApplicationController controller;

	/**
	 * Constructor.
	 * 
	 * @param pController
	 *            ApplicationController instance of this application
	 */
	public ApplicationCloseHandler(ApplicationController pController) {
		controller = pController;
	}

	@Override
	public void handle(WindowEvent pEvent) {
		if (!controller.getSavedStatus()) {
			controller.showUnsavedChangesDialog(ApplicationController.CONTEXT_CLOSE_APPLICATION);
			pEvent.consume();
		} else {
			controller.quit();
		}
	}
}
