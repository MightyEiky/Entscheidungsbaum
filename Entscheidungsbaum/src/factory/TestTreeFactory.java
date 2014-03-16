package factory;

import java.util.ArrayList;
import java.util.List;

import tree.Tier;
import tree.TierNode;

/**
 * 
 * @author Julius
 * 
 */
public class TestTreeFactory {

	/**
	 * 
	 * @return
	 */
	public static List<Tier> createTree() {
		TierNode masterNode = new TierNode(null, "Hello World");
		TierNode node1 = new TierNode(masterNode, "Knoten 1");
		TierNode node2 = new TierNode(masterNode, "Knoten 2");
		TierNode node11 = new TierNode(node1, "Knoten 1 1");
		TierNode node12 = new TierNode(node1, "Knoten 1 2");
		TierNode node13 = new TierNode(node1, "Knoten 1 3");
		TierNode node21 = new TierNode(node2, "Knoten 2 1");
		TierNode node22 = new TierNode(node2, "Knoten 2 2");

		Tier t0 = new Tier();
		t0.addNode(masterNode);

		Tier t1 = new Tier();
		t1.addNode(node1);
		t1.addNode(node2);

		Tier t2 = new Tier();
		t2.addNode(node11);
		t2.addNode(node12);
		t2.addNode(node13);
		t2.addNode(node21);
		t2.addNode(node22);

		List<Tier> tree = new ArrayList<Tier>();
		tree.add(t0);
		tree.add(t1);
		tree.add(t2);

		return tree;
	}
}
