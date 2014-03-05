package controller;

import factory.TableViewFactory;
import handler.OpenHandler;
import handler.QuitHandler;
import handler.SaveHandler;
import io.CSVParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

public class ApplicationController {
	@FXML
	private TableView<List<String>> tableView;
	@FXML
	private MenuItem open;
	@FXML
	private MenuItem quit;
	@FXML
	private MenuItem saveAs;
	@FXML
	private MenuItem save;

	@FXML
	void initialize() throws FileNotFoundException, IOException {
		initializeMenu();
		populateTableView(CSVParser.parseFile(new File("Beispiel.csv"), ","));
	}

	/**
	 * Adds EventHandlers to all MenuItems.
	 */
	private void initializeMenu() {
		getOpenItem().setOnAction(new OpenHandler());
		getSaveItem().setOnAction(new SaveHandler());
		getQuitItem().setOnAction(new QuitHandler(this));
	}

	/**
	 * Populates the TableView of the application with given data.
	 * 
	 * @param pData
	 *            given data as a list of rows
	 */
	public void populateTableView(List<List<String>> pData) {
		ObservableList<List<String>> rows = FXCollections.observableArrayList(pData);
		TableView<List<String>> result = TableViewFactory.createTableView(rows);
		getTableView().getColumns().addAll(result.getColumns());
		getTableView().setItems(result.getItems());
	}

	/**
	 * Closes the application.
	 */
	public void quit() {
		Platform.exit();
	}

	/**
	 * Returns the MenuItem associated with the quit command.
	 * 
	 * @return quit MenuItem
	 */
	public MenuItem getQuitItem() {
		return quit;
	}

	/**
	 * Returns the MenuItem associated with the open file command.
	 * 
	 * @return open MenuItem
	 */
	public MenuItem getOpenItem() {
		return open;
	}

	/**
	 * Returns the MenuItem associated with the save command.
	 * 
	 * @return save MenuItem
	 */
	public MenuItem getSaveItem() {
		return save;
	}

	/**
	 * Returns the TableView of the application.
	 * 
	 * @return TableView instance
	 */
	public TableView<List<String>> getTableView() {
		return tableView;
	}
}
