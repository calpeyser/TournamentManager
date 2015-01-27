package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.*;

public class RecordDialogue extends JDialog {

	// Visible components
	public JButton submitButton;
	
	// Record data
	private Map<JComponent, String> compToAttrib;
	
	public RecordDialogue(JFrame parent, Map<JComponent, String> compToAttrib) {
		super(parent, "Record Configuration", true);
		
		this.compToAttrib = compToAttrib;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		
		getContentPane().setLayout(new GridLayout(0, 2));
		
				
		// add all of the given components, with labels
		for (JComponent comp : compToAttrib.keySet()) {
			getContentPane().add(new JLabel(compToAttrib.get(comp)));
			getContentPane().add(comp);
		}
		
		// button to perform the database update
		submitButton = new JButton("Perform Database Update");
		getContentPane().add(submitButton);

	}
	
	public void addListenerToSubmit(ActionListener listener) {
		submitButton.addActionListener(listener);
	}
	
	/**
	 * 
	 * Returns a mapping from attribute names to given values
	 */
	public Map<String, Object> getValue() {
		Map<String, Object> out = new HashMap<String, Object>();
		for (JComponent comp : compToAttrib.keySet()) {
			Object value = null;
			if (comp.getClass() == JTextField.class) {
				value = ((JTextField)comp).getText();
			}
			else if (comp.getClass() == JToggleButton.class) {
				value = ((JToggleButton)comp).isSelected();
			}
			else if (comp.getClass() == JSpinner.class) {
				value = ((JSpinner)comp).getValue();
			}
			else {
				throw new RuntimeException("Could not parse component " + comp.getName());
			}
			out.put(compToAttrib.get(comp), value);
		}
		return out;
	}
	
}
