package listener;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tab;
import tree.Tier;
import ui.Picasso;
import controller.ApplicationController;
import factory.TestTreeFactory;

/**
 * 
 * @author Julius
 * 
 */
public class TabChangeListener implements ChangeListener<Tab> {

	/** Application controller */
	ApplicationController controller;

	/**
	 * Constructor.
	 * 
	 * @param pController
	 *            ApplicationController
	 */
	public TabChangeListener(ApplicationController pController) {
		controller = pController;
	}

	@Override
	public void changed(ObservableValue<? extends Tab> arg0, Tab pOld, Tab pNew) {
		String newPaneLabel = pNew.getText();
		if (newPaneLabel.equals("Tabellenansicht")) {

		} else if (newPaneLabel.equals("Baumansicht")) {
			List<Tier> tree = TestTreeFactory.createTree();
			GraphicsContext gc = controller.getTreeCanvas().getGraphicsContext2D();
			Picasso.drawTree(gc, tree, 20, 20, false);
		}
	}
}
