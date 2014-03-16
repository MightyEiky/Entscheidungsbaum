package controller;

import factory.FileChooserFactory;
import factory.TableViewFactory;
import handler.DlgCancelHandler;
import handler.DlgDiscardHandler;
import handler.DlgSaveHandler;
import handler.OpenHandler;
import handler.QuitHandler;
import handler.SaveHandler;
import io.CSVParser;
import io.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import listener.SelectionChangeListener;
import util.ListConversionHelper;

/**
 * 
 * @author Julius
 * 
 */
public class ApplicationController {

	/** Used when user intends to close application */
	public static final int CONTEXT_CLOSE_APPLICATION = 1;

	/** Used when user intends to open a file */
	public static final int CONTEXT_OPEN_FILE = 2;

	/** CSV value separator */
	private static final String CSV_SEPARATOR = ";";

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
	@FXML
	private AnchorPane dialog;
	@FXML
	private AnchorPane mainWindow;
	@FXML
	private Button dlgClose;
	@FXML
	private Button dlgCancel;
	@FXML
	private Button dlgSave;

	/** Current csv file displayed in the TableView */
	private File currentFile;

	/** Indicating whether or not there are unsaved changes in current csv file */
	private boolean saved;

	/** Conetext of unsaved changes dialog */
	private int dlgContext;

	@FXML
	void initialize() {
		dlgClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				quit();
			}
		});

		initializeMenu();
		initializeTableView();
		initializeMembers();
		initializeDialog();
	}

	private void initializeDialog() {
		dlgCancel.setOnMouseClicked(new DlgCancelHandler(this));
		dlgClose.setOnMouseClicked(new DlgDiscardHandler(this));
		dlgSave.setOnMouseClicked(new DlgSaveHandler(this));
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
	 * Closes the unsaved changes dialog.
	 */
	public void hideUnsavedChangesDialog() {
		getMainWindow().setDisable(false);
		getMainWindow().setOpacity(1);
		getDialog().setVisible(false);
	}

	/**
	 * Opens a dialog to ask the user to save, discard his changes or cancel.
	 * 
	 * @pContext the context of the dialog
	 * @return true, if the action was not cancelled by the user, false if so
	 */
	public void showUnsavedChangesDialog(int pContext) {
		dlgContext = pContext;
		getMainWindow().setDisable(true);
		getMainWindow().setOpacity(0.5);
		getDialog().setVisible(true);
	}

	/**
	 * Defines the behavior of the application after a discard response from the
	 * dialog.
	 */
	public void responseDiscard() {
		hideUnsavedChangesDialog();
		if (dlgContext == CONTEXT_OPEN_FILE) {
			openFile();
		} else if (dlgContext == CONTEXT_CLOSE_APPLICATION) {
			quit();
		}
	}

	/**
	 * Defines the behavior of the application after a cancel response from the
	 * dialog.
	 */
	public void responseCancel() {
		hideUnsavedChangesDialog();
	}

	/**
	 * Defines the behavior of the application after a save response from the
	 * dialog.
	 */
	public void responseSave() {
		hideUnsavedChangesDialog();
		if (dlgContext == CONTEXT_OPEN_FILE) {
			saveCurrentFile();
			openFile();
		} else if (dlgContext == CONTEXT_CLOSE_APPLICATION) {
			saveCurrentFile();
			quit();
		}
	}

	/**
	 * Saves data from the TableView to the currently opened file.
	 */
	public void saveCurrentFile() {
		TableView<List<String>> tableView = getTableView();
		if (tableView != null) {
			ObservableList<List<String>> items = tableView.getItems();
			if (items != null) {
				File currentFile = getCurrentFile();
				if (currentFile != null) {
					try {
						List<String> lines = ListConversionHelper.tableViewToLines(tableView, getCSVSeparator());
						Writer.writeFile(currentFile, lines);
						setSavedStatus(true);
						setStatus(currentFile.getAbsolutePath() + " gespeichert");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Allows user to pick a file via FileChooser and loads the files data into
	 * the TableView.
	 * 
	 */
	public void openFile() {
		FileChooser fc = FileChooserFactory.createCSVChooser("CSV-Datei �ffnen");
		File csvFile = fc.showOpenDialog(null);

		if (csvFile != null) {
			try {
				populateTableView(CSVParser.parseFile(csvFile, ","));
				setStatus(csvFile.getAbsolutePath() + " ge�ffnet");
				setTitle(csvFile.getAbsolutePath());
				setCurrentFile(csvFile);
				setSavedStatus(true);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
		getTableView().getItems().clear();
		getTableView().getColumns().clear();
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
	 * Returns the main window AnchorPane.
	 * 
	 * @return AnchorPane
	 */
	public AnchorPane getMainWindow() {
		return mainWindow;
	}

	/**
	 * Returns the dialog AnchorPane.
	 * 
	 * @return AnchorPane
	 */
	public AnchorPane getDialog() {
		return dialog;
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
