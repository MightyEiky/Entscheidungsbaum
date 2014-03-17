package listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import controller.ApplicationController;

/**
 * 
 * @author Julius
 * 
 */
public class RadioButtonGroupListener implements ChangeListener<Toggle> {

	/**
	 *
	 */
	private ApplicationController controller;

	/**
	 * 
	 * @param pController
	 */
	public RadioButtonGroupListener(ApplicationController pController) {
		controller = pController;
	}

	@Override
	public void changed(ObservableValue<? extends Toggle> arg0, Toggle oldRadio, Toggle newRadio) {
		controller.setTargetAttribute((int) newRadio.getUserData());
	}
}
