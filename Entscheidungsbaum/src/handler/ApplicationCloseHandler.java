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
	ApplicationController controller;

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
			System.out.println("Ungespeicherte Änderungen verloren gegangen");
			controller.quit();
		} else {
			controller.quit();
		}
	}
}
