package View;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Window;
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
	
	public RecordDialogue(Window parent, Map<JComponent, String> compToAttrib) {
		super(parent, "Record Configuration", Dialog.ModalityType.APPLICATION_MODAL);
		
		this.compToAttrib = compToAttrib;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 900);
		
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
	
	
}
