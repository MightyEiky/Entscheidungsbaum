package util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.junit.Test;

/**
 * 
 * @author Julius
 * 
 */
public class ListConversionHelperTest {
	/**
	 * Testing tableViewItemsToLines method of ListConversionHelper class.
	 */
	@Test
	public void tableViewDataToLinesTest() {
		String separator = ",";
		List<String> expected = Arrays.asList("Hello" + separator + "World", "this" + separator + "is", "a" + separator + "test");
		//@formatter:off
		@SuppressWarnings("unchecked")
		ObservableList<List<String>> data = FXCollections.observableArrayList(
				Arrays.asList("Hello", "World"),
				Arrays.asList("this", "is"), 
				Arrays.asList("a", "test"));
		//@formatter:on

		List<String> colNames = data.remove(0);
		TableView<List<String>> tv = new TableView<List<String>>();
		for (String s : colNames) {
			tv.getColumns().add(new TableColumn<List<String>, String>(s));
		}
		tv.setItems(data);

		List<String> actual = ListConversionHelper.tableViewToLines(tv, separator);

		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(expected.get(i), actual.get(i));
		}
	}
}
