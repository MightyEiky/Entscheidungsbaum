package listener;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * 
 * @author Julius
 * 
 */
public class SelectionChangeListener implements ChangeListener<List<String>> {
	/**
	 * Called whenever the selection in the TableView changes.
	 */
	@Override
	public void changed(ObservableValue<? extends List<String>> arg0, List<String> pOld, List<String> pNew) {
		if (pOld != null)
			System.out.println(pOld);

		if (pNew != null)
			System.out.println(pNew);
	}
}
