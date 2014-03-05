package handler;

import io.CSVParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import controller.ApplicationController;
import factory.FileChooserFactory;

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
		FileChooser fc = FileChooserFactory.createCSVChooser("CSV öffnen");
		File csvFile = fc.showOpenDialog(null);

		if (csvFile != null) {
			try {
				controller.populateTableView(CSVParser.parseFile(csvFile, ","));
				controller.setStatus(csvFile.getAbsolutePath() + " geöffnet");
				controller.setTitle(csvFile.getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
