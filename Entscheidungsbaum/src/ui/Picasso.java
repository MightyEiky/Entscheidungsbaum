package ui;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tree.Tier;
import tree.TierNode;

/**
 * 
 * @author Julius
 * 
 */
public class Picasso {

	/**
	 * Pablo doing his job.
	 * 
	 * @return Canvas
	 */
	public static void drawTree(GraphicsContext pGc, List<Tier> pTiers, int pVGap, int pHGap, boolean pDisplayGrid) {
		int canvasWidth = computeCanvasWidth(pTiers, pGc.getFont(), pVGap);
		int canvasHeight = computeCanvasHeight(pTiers, pGc.getFont(), pHGap);
		pGc.getCanvas().setWidth(canvasWidth + 500);
		pGc.getCanvas().setHeight(canvasHeight + 500);
		((Pane) pGc.getCanvas().getParent()).setPrefWidth(canvasWidth);
		((Pane) pGc.getCanvas().getParent()).setPrefHeight(canvasHeight);
		clear(pGc);

		if (pDisplayGrid) {
			pGc.setStroke(Color.RED);
			pGc.strokeRect(0, 0, canvasWidth, canvasHeight);
		}

		pGc.setStroke(Color.BLACK);
		pGc.setLineWidth(1);

		int cellWidth = canvasWidth / getMaxTierElements(pTiers);
		int cellHeight = canvasHeight / pTiers.size();
		int lineHeight = computeLineHeight(pGc.getFont());

		int startY = 0;
		int startX = 0;

		int y = 0;
		int x = 0;

		for (int i = 0; i < pTiers.size(); i++) {
			Tier tier = pTiers.get(i);
			startY = cellHeight * i;
			y = startY + (cellHeight / 2) - (int) (lineHeight * 0.75);
			for (int j = 0; j < tier.getNodes().size(); j++) {
				List<TierNode> nodes = tier.getNodes();
				int elems = nodes.size();
				TierNode node = tier.getNodes().get(j);
				String text = node.getText();

				startX = (canvasWidth / elems) * j + ((canvasWidth / elems) / 2) - (cellWidth / 2);
				if (pDisplayGrid) {
					pGc.strokeRect(startX, startY, cellWidth, cellHeight);
				}

				x = startX + (cellWidth / 2) - (computeStringWidth(text, pGc.getFont()) / 2);
				pGc.fillText(text, x, y + lineHeight);
				pGc.strokeOval(startX + pVGap / 2, startY + pHGap / 2, cellWidth - pVGap, cellHeight - pHGap);

				if (node.getParent() != null) {
					TierNode parent = node.getParent();
					List<TierNode> lastTierNodes = pTiers.get(i - 1).getNodes();
					for (int k = 0; k < lastTierNodes.size(); k++) {
						if (lastTierNodes.get(k).getText().equals(parent.getText())) {
							int parentX = canvasWidth / lastTierNodes.size() * k + (canvasWidth / lastTierNodes.size()) / 2;
							int parentY = cellHeight * i - 1;
							int thisX = startX + (cellWidth / 2);
							int thisY = startY + (pHGap / 2);
							pGc.strokeLine(thisX, thisY, parentX, parentY - (pHGap / 2) + 1);
						}
					}
				}
			}
		}
	}

	/**
	 * Computes the tree canvas' maximum width.
	 * 
	 * @param pTiers
	 *            List of Tiers
	 * @param pF
	 *            Font used
	 * @param pGap
	 *            vertical gap between nodes
	 * @return max( width of Tiers ) as int
	 */
	private static int computeCanvasWidth(List<Tier> pTiers, Font pF, int pVGap) {
		int maxStringWidth = getMaxStringWidth(pTiers, pF);
		int maxElements = getMaxTierElements(pTiers);
		int maxWidth = maxElements * (maxStringWidth + 40 + pVGap);
		return maxWidth;
	}

	/**
	 * Computes the tree canvas' maximum height.
	 * 
	 * @param pTiers
	 *            List of Tiers
	 * @param pF
	 *            Font used
	 * @param pGap
	 *            horizontal gap between Tiers
	 * @return height of the tree as int
	 */
	private static int computeCanvasHeight(List<Tier> pTiers, Font pF, int pHGap) {
		int lineHeight = computeLineHeight(pF);
		int height = pTiers.size() * (lineHeight + 40 + pHGap);
		return height;
	}

	/**
	 * Returns the maximum width of the Strings the given Tier carries.
	 * 
	 * @return max width as int
	 */
	private static int getMaxStringWidth(List<Tier> pTiers, Font pF) {
		int maxStringWidth = 0;
		for (Tier t : pTiers) {
			for (TierNode node : t.getNodes()) {
				int currentStringWidth = computeStringWidth(node.getText(), pF);
				if (maxStringWidth < currentStringWidth) {
					maxStringWidth = currentStringWidth;
				}
			}
		}
		return maxStringWidth;
	}

	/**
	 * Returns the number of max elements in a single Tier of given List of
	 * Tiers.
	 * 
	 * @param pTiers
	 *            List of Tiers
	 * @return max elements a single Tier carries as int
	 */
	private static int getMaxTierElements(List<Tier> pTiers) {
		int maxElements = 0;
		for (Tier t : pTiers) {
			if (maxElements < t.getNodes().size()) {
				maxElements = t.getNodes().size();
			}
		}
		return maxElements;
	}

	/**
	 * Computes the width of a String.
	 * 
	 * @param pS
	 *            String
	 * @param pF
	 *            Font used
	 * @return width as int
	 */
	private static int computeStringWidth(String pS, Font pF) {
		int width = (int) Math.ceil(com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(pS, pF));
		return width;
	}

	/**
	 * Computes the line height of a given Font.
	 * 
	 * @param pF
	 *            Font
	 * @return height as int
	 */
	private static int computeLineHeight(Font pF) {
		float lineHeight = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(pF).getLineHeight();
		return (int) Math.ceil(lineHeight);
	}

	/**
	 * Clear canvas.
	 */
	private static void clear(GraphicsContext pGc) {
		int width = (int) Math.ceil(pGc.getCanvas().getWidth());
		int height = (int) Math.ceil(pGc.getCanvas().getHeight());
		pGc.setFill(Color.WHITE);
		pGc.fillRect(0, 0, width, height);
		pGc.setFill(Color.BLACK);
	}
}
