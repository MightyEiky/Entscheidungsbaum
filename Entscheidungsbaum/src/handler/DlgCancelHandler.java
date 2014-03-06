package handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import controller.ApplicationController;

public class DlgCancelHandler implements EventHandler<MouseEvent> {

	/** Application controller of application */
	private ApplicationController controller;

	/**
	 * Constructor.
	 * 
	 * @param pController
	 *            Application controller of application
	 */
	public DlgCancelHandler(ApplicationController pController) {
		controller = pController;
	}

	@Override
	public void handle(MouseEvent arg0) {
		controller.getDialog().setVisible(false);
		controller.getMainWindow().setOpacity(1.0);
		controller.getMainWindow().setDisable(false);
	}
}
