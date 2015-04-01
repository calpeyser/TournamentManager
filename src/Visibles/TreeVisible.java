package Visibles;

import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

public abstract class TreeVisible extends Visible {

	private Map<String, DefaultMutableTreeNode> nodes = new HashMap<String, DefaultMutableTreeNode>();
	//private DefaultTreeModel model;
	
	protected abstract String rootName();
	
	protected abstract void configure();
	
	protected void setChild(String child, String parent) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(child);
		nodes.put(child, newNode);
		try {
			nodes.get(parent).add(newNode);
		}
		catch (Exception e) {
			throw new RuntimeException("Bad Tree Construction");
		}
	}
	
	@Override
	public JComponent getComponent() {
		nodes.put(rootName(), new DefaultMutableTreeNode(rootName()));
		configure();
		//model = new DefaultTreeModel(nodes.get(rootName()));
		JTree tree = new JTree(nodes.get(rootName()));
		JScrollPane out = new JScrollPane(tree);
		return out;
	}
}
