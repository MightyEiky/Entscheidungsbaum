package launcher;

import javafx.application.Application;
import ui.UserInterface;

/**
 * Launches the application.
 * 
 * @author Julius
 * 
 */
public class Launcher {
	/**
	 * Main method, launching the Javafx application.
	 * 
	 * @param args
	 *            cmd line arguments
	 */
	public static void main(String[] args) {
		Application.launch(UserInterface.class, args);
	}
}
