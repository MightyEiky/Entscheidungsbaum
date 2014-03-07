package handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import controller.ApplicationController;

/**
 * 
 * @author Julius
 * 
 */
public class DlgDiscardHandler implements EventHandler<MouseEvent> {

	/** ApplicationController instance of application */
	private ApplicationController controller;

	/**
	 * Constructor.
	 * 
	 * @param pController
	 *            ApplicationController instance of application
	 */
	public DlgDiscardHandler(ApplicationController pController) {
		controller = pController;
	}

	@Override
	public void handle(MouseEvent pEvent) {
		controller.responseDiscard();
		pEvent.consume();
	}
}
