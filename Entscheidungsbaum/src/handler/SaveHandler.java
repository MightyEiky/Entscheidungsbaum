package handler;

import io.Writer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import util.ListConversionHelper;
import controller.ApplicationController;

public class SaveHandler implements EventHandler<ActionEvent> {

	/** Application controller instance of this application */
	ApplicationController controller;

	public SaveHandler(ApplicationController pController) {
		controller = pController;
	}

	/**
	 * Called whenever the save MenuItem was clicked.
	 */
	@Override
	public void handle(ActionEvent pAction) {
		if (!controller.getSavedStatus()) {
			TableView<List<String>> tableView = controller.getTableView();
			if (tableView != null) {
				ObservableList<List<String>> items = tableView.getItems();
				if (items != null) {
					File currentFile = controller.getCurrentFile();
					if (currentFile != null) {
						try {
							List<String> lines = ListConversionHelper.tableViewToLines(tableView, controller.getCSVSeparator());
							Writer.writeFile(currentFile, lines);
							controller.setSavedStatus(true);
							controller.setStatus(currentFile.getAbsolutePath() + " gespeichert");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
