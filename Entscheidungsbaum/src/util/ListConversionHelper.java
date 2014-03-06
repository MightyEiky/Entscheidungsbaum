package util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * 
 * @author Julius
 * 
 */
public class ListConversionHelper {
	/**
	 * Converts an instance of ObservableList&lt;List&lt;String&gt;&gt; to a new
	 * instance of List&lt;String&gt;.
	 * 
	 * @param pItems
	 *            ObservableList&lt;List&lt;String&gt;&gt;
	 * @param pSeparator
	 *            separator to use for the values
	 * @return List&lt;String&gt;
	 */
	public static List<String> tableViewToLines(TableView<List<String>> pTableView, String pSeparator) {
		List<String> lines = new ArrayList<String>();
		String line;

		line = null;
		for (TableColumn<List<String>, ?> col : pTableView.getColumns()) {
			if (line == null) {
				line = col.getText();
			} else {
				line = line.concat(pSeparator + col.getText());
			}
		}
		lines.add(line);

		for (List<String> row : pTableView.getItems()) {
			line = null;
			for (String s : row) {
				if (line == null) {
					line = s;
				} else {
					line = line.concat(pSeparator + s);
				}
			}
			lines.add(line);
		}

		return lines;
	}
}
