package tree;

/**
 * 
 * @author Julius
 * 
 */
public class TierNode {

	/** Parent Node */
	private TierNode parent;

	/** Text of this node */
	private String text;

	/**
	 * Constructor.
	 * 
	 * @param pParent
	 *            Node instance
	 */
	public TierNode(TierNode pParent, String pText) {
		parent = pParent;
		text = pText;
	}

	/**
	 * Returns parent Node.
	 * 
	 * @return
	 */
	public TierNode getParent() {
		return parent;
	}

	/**
	 * Returns the text of this node.
	 * 
	 * @return String
	 */
	public String getText() {
		return text;
	}
}
