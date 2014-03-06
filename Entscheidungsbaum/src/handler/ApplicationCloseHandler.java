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
	public void handle(WindowEvent arg0) {
		if (!controller.getSavedStatus()) {
			controller.getMainWindow().setDisable(true);
			controller.getMainWindow().setOpacity(0.5);
			controller.getDialog().setVisible(true);
			arg0.consume();
		} else {
			controller.quit();
		}
	}
}
