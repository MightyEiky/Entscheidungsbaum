package factory;

import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public final class TableViewFactory {

	/**
	 * 
	 * @param pDataRows
	 * @return
	 */
	public static TableView<List<String>> createTableView(List<List<String>> pDataRows) {
		List<String> colNames = pDataRows.get(0);
		ObservableList<List<String>> observableDataRows = FXCollections.observableArrayList(pDataRows);
		TableView<List<String>> tableView = new TableView<List<String>>();

		for (int i = 0; i < colNames.size(); i++) {
			final int index = i;
			TableColumn<List<String>, String> currentCol = new TableColumn<List<String>, String>(colNames.get(i));
			currentCol.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<List<String>, String> arg) {
					return new MyObservableValue(arg.getValue().get(index));
				}
			});
			tableView.getColumns().add(currentCol);
		}

		tableView.setItems(observableDataRows);
		return tableView;
	}
}
