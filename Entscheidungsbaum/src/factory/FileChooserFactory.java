package factory;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * @author Julius
 * 
 */
public class FileChooserFactory {
	/**
	 * Creates a FileChooser for CSV files.
	 * 
	 * @param pTitle
	 *            title of the FileChooser window
	 * @return FileChooser instance
	 */
	public static FileChooser createCSVChooser(String pTitle) {
		FileChooser fc = new FileChooser();
		fc.setTitle(pTitle);
		fc.getExtensionFilters().add(new ExtensionFilter("CSV-Dateien (*.csv)", "*.csv"));
		return fc;
	}

	/**
	 * Not implemented yet!
	 * 
	 * @return
	 */
	public static FileChooser createSaveDialog(String pTitle) {
		return null;
	}
}
