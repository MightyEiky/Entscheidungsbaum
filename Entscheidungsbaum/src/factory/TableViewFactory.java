package factory;

import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;
import listener.RadioButtonGroupListener;

import component.EditableTableCell;

import controller.ApplicationController;

/**
 * 
 * @author Julius
 * 
 */
public final class TableViewFactory {

	/**
	 * Creates a TableView from given data.
	 * 
	 * @param pDataRows
	 *            data as List of rows
	 * @return TableView instance
	 */
	public static TableView<List<String>> createTableView(List<List<String>> pDataRows, final ApplicationController pController) {
		List<String> colNames = pDataRows.get(0);
		ObservableList<List<String>> observableDataRows = FXCollections.observableArrayList(pDataRows);
		TableView<List<String>> tableView = new TableView<List<String>>();
		ToggleGroup radioGroup = new ToggleGroup();
		for (int i = 0; i < colNames.size(); i++) {
			final int index = i;
			TableColumn<List<String>, String> currentCol = new TableColumn<List<String>, String>(colNames.get(i));
			currentCol.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<List<String>, String> arg) {
					return new MyObservableValue(arg.getValue().get(index));
				}
			});
			currentCol.setCellFactory(new Callback<TableColumn<List<String>, String>, TableCell<List<String>, String>>() {
				@Override
				public TableCell<List<String>, String> call(TableColumn<List<String>, String> arg0) {
					return new EditableTableCell(pController);
				}
			});
			currentCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<List<String>, String>>() {
				@Override
				public void handle(CellEditEvent<List<String>, String> arg0) {
					((List<String>) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow())).set(arg0.getTablePosition()
							.getColumn(), arg0.getNewValue());
				}
			});
			RadioButton r = new RadioButton();
			r.setUserData(i);
			r.setToggleGroup(radioGroup);
			if (i == colNames.size() - 1) {
				r.setSelected(true);
			}
			currentCol.setGraphic(r);
			currentCol.setMinWidth(150);
			currentCol.setStyle(ApplicationController.STYLE_NOT_SELECTED);
			tableView.getColumns().add(currentCol);
		}
		radioGroup.selectedToggleProperty().addListener(new RadioButtonGroupListener(pController));
		observableDataRows.remove(0);
		tableView.setItems(observableDataRows);
		return tableView;
	}
}
