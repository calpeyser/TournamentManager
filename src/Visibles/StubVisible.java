package Visibles;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class StubVisible extends Visible {

	private String name;
	
	public StubVisible(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public JComponent getComponent() {
		return new JLabel(name);
	}

}
