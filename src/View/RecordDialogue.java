package View;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import javax.swing.*;

import Base.FormFormat;

public class RecordDialogue extends JDialog {

	private final String DEFAULT_TAB_NAME = "Default Tab";
	
	// Visible components
	public JButton submitButton;
	public JTabbedPane tabbedPane;
	
	// Record data
	private Map<JComponent, String> compToAttrib;
	private Map<String, Field> nameToField;
	
	public RecordDialogue(Window parent, Map<JComponent, String> compToAttrib, Map<String, Field> nameToField) {
		super(parent, "Record Configuration", Dialog.ModalityType.APPLICATION_MODAL);
		
		this.compToAttrib = compToAttrib;
		this.nameToField = nameToField;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 900);
		
		// compile tabnames
		List<String> tabNames = new ArrayList<String>();
		for (String name : nameToField.keySet()) {
			Field f = nameToField.get(name);
			if (f.isAnnotationPresent(FormFormat.class)) {
				FormFormat format = f.getAnnotation(FormFormat.class);
				String tabName = format.tab();
				if (!tabNames.contains(tabName)) {
					tabNames.add(tabName);
				}
			}
		}
		// if we have tabnames, create the tabs.  Otherwise, create one trivial tab
		tabbedPane = new JTabbedPane();
		Map<String, JPanel> namesToTabs = new LinkedHashMap<String, JPanel>();
		namesToTabs.put(DEFAULT_TAB_NAME, new JPanel());
		for (String tabName : tabNames) {
			namesToTabs.put(tabName, new JPanel());
		}
		for (String tabName : namesToTabs.keySet()) {
			namesToTabs.get(tabName).setLayout(new GridLayout(0,2));
			tabbedPane.addTab(tabName, namesToTabs.get(tabName));
		}

		getContentPane().setLayout(new GridLayout(0, 1));
		getContentPane().add(tabbedPane);
				
		// add all of the given components, with labels, to the correct tab
		for (JComponent comp : compToAttrib.keySet()) {
			Field f = nameToField.get(compToAttrib.get(comp));
			String tabName;
			if (f.isAnnotationPresent(FormFormat.class)) {
				FormFormat format = f.getAnnotation(FormFormat.class);
				tabName = format.tab();
			}
			else {
				tabName = DEFAULT_TAB_NAME;
			}
			
			namesToTabs.get(tabName).add(new JLabel(compToAttrib.get(comp)));
			namesToTabs.get(tabName).add(comp);
		}
		
		// button to perform the database update
		submitButton = new JButton("Perform Database Update");
		namesToTabs.get(DEFAULT_TAB_NAME).add(submitButton);

	}
	
	public void addListenerToSubmit(ActionListener listener) {
		submitButton.addActionListener(listener);
	}
	
	
}
