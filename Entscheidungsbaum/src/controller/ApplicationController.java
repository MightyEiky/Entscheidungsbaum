package controller;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import factory.TableViewFactory;

public class ApplicationController {

	@FXML
	private TableView<List<String>> tableview;

	@FXML
	void initialize() {
		//@formatter:off
		ObservableList<List<String>> rows = FXCollections.observableArrayList(Arrays.asList(
					Arrays.asList("Reihe1 Spalte1", "Reihe1 Spalte2", "Reihe1 Spalte3"),
					Arrays.asList("Reihe2 Spalte1", "Reihe2 Spalte2", "Reihe3 Spalte2")
				));
		//@formatter:on

		TableView<List<String>> result = TableViewFactory.createTableView(rows);
		tableview.getColumns().addAll(result.getColumns());
		tableview.setItems(result.getItems());
	}
}
