package handler;

import io.Writer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import util.ListConversionHelper;
import controller.ApplicationController;

public class DlgSaveHandler implements EventHandler<MouseEvent> {

	/** ApplicationController instance of application */
	private ApplicationController controller;

	/**
	 * Constructor.
	 * 
	 * @param pController
	 *            ApplicationController instance of application
	 */
	public DlgSaveHandler(ApplicationController pController) {
		controller = pController;
	}

	@Override
	public void handle(MouseEvent arg0) {
		TableView<List<String>> tableView = controller.getTableView();
		if (tableView != null) {
			ObservableList<List<String>> items = tableView.getItems();
			if (items != null) {
				File currentFile = controller.getCurrentFile();
				if (currentFile != null) {
					try {
						List<String> lines = ListConversionHelper.tableViewToLines(tableView, controller.getCSVSeparator());
						Writer.writeFile(currentFile, lines);
						controller.quit();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
