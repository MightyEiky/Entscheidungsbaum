package component;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import controller.ApplicationController;

/**
 * 
 * @author Julius
 * 
 */
public class EditableTableCell extends TableCell<List<String>, String> {

	/** Textfield used for editing of cells */
	private TextField textField;

	/** Application controller instance of the application */
	private ApplicationController controller;

	/**
	 * Constructor.
	 * 
	 * @param pController
	 */
	public EditableTableCell(ApplicationController pController) {
		controller = pController;
	}

	@Override
	public void startEdit() {
		super.startEdit();
		if (textField == null) {
			createTextField();
		}
		setGraphic(textField);
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				textField.requestFocus();
				textField.selectAll();
			}
		});
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText((String) getItem());
		setContentDisplay(ContentDisplay.TEXT_ONLY);
	}

	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (textField != null) {
					textField.setText(getString());
				}
				setGraphic(textField);
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			} else {
				setText(getString());
				setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}
	}

	private void createTextField() {
		textField = new TextField(getString());
		textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if (t.getCode() == KeyCode.ENTER) {
					commitEdit(textField.getText());
					controller.setSavedStatus(false);
				} else if (t.getCode() == KeyCode.ESCAPE) {
					cancelEdit();
				} else if (t.getCode() == KeyCode.TAB) {
					commitEdit(textField.getText());
					controller.setSavedStatus(false);
					TableColumn<List<String>, ?> nextColumn = getNextColumn(!t.isShiftDown());
					if (nextColumn != null) {
						getTableView().edit(getTableRow().getIndex(), nextColumn);
					}
				}
			}
		});
		textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue && textField != null) {
					commitEdit(textField.getText());
					controller.setSavedStatus(false);
				}
			}
		});
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}

	/**
	 * 
	 * @param forward
	 *            true gets the column to the right, false the column to the
	 *            left of the current column
	 * @return
	 */
	private TableColumn<List<String>, ?> getNextColumn(boolean forward) {
		List<TableColumn<List<String>, ?>> columns = new ArrayList<>();
		for (TableColumn<List<String>, ?> column : getTableView().getColumns()) {
			columns.addAll(getLeaves(column));
		}

		if (columns.size() < 2) {
			return null;
		}
		int currentIndex = columns.indexOf(getTableColumn());
		int nextIndex = currentIndex;
		if (forward) {
			nextIndex++;
			if (nextIndex > columns.size() - 1) {
				nextIndex = 0;
			}
		} else {
			nextIndex--;
			if (nextIndex < 0) {
				nextIndex = columns.size() - 1;
			}
		}
		return columns.get(nextIndex);
	}

	private List<TableColumn<List<String>, ?>> getLeaves(TableColumn<List<String>, ?> root) {
		List<TableColumn<List<String>, ?>> columns = new ArrayList<>();
		if (root.getColumns().isEmpty()) {

			if (root.isEditable()) {
				columns.add(root);
			}
			return columns;
		} else {
			for (TableColumn<List<String>, ?> column : root.getColumns()) {
				columns.addAll(getLeaves(column));
			}
			return columns;
		}
	}
}
