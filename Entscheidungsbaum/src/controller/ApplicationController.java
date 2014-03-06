package controller;

import factory.TableViewFactory;
import handler.OpenHandler;
import handler.QuitHandler;
import handler.SaveHandler;

import java.io.File;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import listener.SelectionChangeListener;

public class ApplicationController {

	/** CSV value separator */
	private static final String CSV_SEPARATOR = ",";

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
	private Label status;

	/** Current csv file displayed in the TableView */
	private File currentFile;

	/** Indicating whether or not there are unsaved changes in current csv file */
	private boolean saved;

	@FXML
	void initialize() {
		initializeMenu();
		initializeTableView();
		initializeMembers();
		initializeApplication();
	}

	/**
	 * Initializes the application
	 * */
	private void initializeApplication() {

	}

	/**
	 * Initializes attributes of this class.
	 */
	private void initializeMembers() {
		saved = true;
		currentFile = null;
	}

	/**
	 * Initialize TableView.
	 */
	private void initializeTableView() {
		getTableView().setEditable(true);
		getTableView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		getTableView().getSelectionModel().selectedItemProperty().addListener(new SelectionChangeListener());
	}

	/**
	 * Adds EventHandlers to all MenuItems.
	 */
	private void initializeMenu() {
		getOpenItem().setOnAction(new OpenHandler(this));
		getSaveItem().setOnAction(new SaveHandler(this));
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
		TableView<List<String>> result = TableViewFactory.createTableView(rows, this);
		getTableView().getColumns().addAll(result.getColumns());
		getTableView().setItems(result.getItems());
	}

	/**
	 * Allows to put message in the status line of the application.
	 * 
	 * @pString status message
	 */
	public void setStatus(String pString) {
		getStatusLabel().setText(pString);
	}

	/**
	 * Returns the Stage of this application.
	 * 
	 * @return Stage
	 */
	public Stage getStage() {
		return (Stage) getStatusLabel().getScene().getWindow();
	}

	/**
	 * Returns the title of the application window.
	 * 
	 * @return String
	 */
	public String getTitle() {
		return getStage().getTitle();
	}

	/**
	 * Sets the title of the application window.
	 * 
	 * @param pString
	 *            title
	 */
	public void setTitle(String pString) {
		getStage().setTitle(pString);
	}

	/**
	 * Closes the application.
	 */
	public void quit() {
		Platform.exit();
	}

	/**
	 * Returns the value separator used in the CSV files.
	 * 
	 * @return String
	 */
	public String getCSVSeparator() {
		return CSV_SEPARATOR;
	}

	/**
	 * Allows to access the currently opened File.
	 * 
	 * @return currently opened File instance
	 */
	public File getCurrentFile() {
		return currentFile;
	}

	/**
	 * Allows to set the currently openend file.
	 * 
	 * @pFile the file
	 */
	public void setCurrentFile(File pFile) {
		currentFile = pFile;
	}

	/**
	 * Allows to set the indicator for unsaved changes.
	 * 
	 * @param pB
	 *            true meaning there are <b>no</b> unsaved changes, false
	 *            meaning there <b>are</b> unsaved changes
	 */
	public void setSavedStatus(boolean pB) {
		saved = pB;
		if (pB) {
			setTitle(getCurrentFile().getAbsolutePath());
		} else {
			setTitle(getCurrentFile().getAbsolutePath() + "*");
		}
	}

	/**
	 * Indicates whether or not there are unsaved changes in the current csv
	 * file.
	 * 
	 * @return true if no unsaved changes, false otherwise
	 */
	public boolean getSavedStatus() {
		return saved;
	}

	/**
	 * Returns the status Label instance.
	 * 
	 * @return Label instance
	 */
	public Label getStatusLabel() {
		return status;
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
