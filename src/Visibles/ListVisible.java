package Visibles;

import javax.swing.*;
import java.util.*;

public abstract class ListVisible extends Visible {

	public abstract List<String> getStrings();
	
	@Override
	public JComponent getComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for (String s : getStrings()) {
			panel.add(new JLabel(s));
		}
		JScrollPane out = new JScrollPane(panel);
		return out;
	}

}
