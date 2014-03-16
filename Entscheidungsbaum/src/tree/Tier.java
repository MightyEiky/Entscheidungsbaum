package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Julius
 * 
 */
public class Tier {

	/** All Nodes in this tier. */
	List<TierNode> nodes;

	/**
	 * Constructor.
	 */
	public Tier() {
		nodes = new ArrayList<TierNode>();
	}

	/**
	 * Adds a TierNode to this Tier.
	 * 
	 * @param pNode
	 *            TierNode instance
	 */
	public void addNode(TierNode pNode) {
		getNodes().add(pNode);
	}

	/**
	 * Returns the List of TierNodes of this Tier.
	 * 
	 * @return List&lt;TierNode&gt;
	 */
	public List<TierNode> getNodes() {
		return nodes;
	}
}
