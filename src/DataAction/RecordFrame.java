package DataAction;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.persistence.Basic;
import javax.persistence.OneToOne;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import Base.*;
import Data.*;

import java.util.*;

public class RecordFrame extends JFrame {

	private JPanel contentPane;
	private TournamentDataStore db;
	private JButton btnSubmit;
	public Map<ValueComponent, Field> vcMap;
	private Record toConfigure;
	
	private interface ValueComponent {
		public JComponent getComponent();
		public <T> T getValue();
	}
		
	public void addEventToSubmit(ActionListener listener) {
		btnSubmit.addActionListener(listener);
	}

	public ValueComponent getComponent(Field f) throws IllegalArgumentException, IllegalAccessException {
		ValueComponent out = null;
		if (f.isAnnotationPresent(Basic.class)) {
			if (f.getType() == String.class) {
				final JTextField typedOut = new JTextField();
				typedOut.setText((String)f.get(toConfigure));
				typedOut.setColumns(10);
				
				out = new ValueComponent() {
					public JComponent getComponent() {
						return (JComponent)typedOut;
					}
					@SuppressWarnings("unchecked")
					public String getValue() {
						return typedOut.getText();
					}
				};
				
			}
			else if (f.getType() == boolean.class || f.getType() == Boolean.class) {
				final JToggleButton typedOut = new JToggleButton();
				typedOut.setSelected(f.getBoolean(toConfigure));
				
				out = new ValueComponent() {
					public JComponent getComponent() {
						return (JComponent)typedOut;
					}
					@SuppressWarnings("unchecked")
					public Boolean getValue() {
						return typedOut.isSelected();
					}
				};	
			}
			
			else if (f.getType() == int.class || f.getType() == Integer.class) {
				final JSpinner typedOut = new JSpinner();
				typedOut.setValue(f.getInt(toConfigure));
				out = new ValueComponent() {
					public JComponent getComponent() {
						return (JComponent) typedOut;
					}
					@SuppressWarnings("unchecked")
					public Integer getValue() {
						return (Integer) typedOut.getValue();
					}
				};
			}
		}
		else if (f.isAnnotationPresent(OneToOne.class)) {
			// TODO: Set up some JList instance
		}
		
		return out;
	}
	
	public RecordFrame(Record toConfigure, TournamentDataStore db) {
		this.db = db; this.toConfigure = toConfigure;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/* Reflectively populate form */
		int gridx = 2; int gridy = 2;
		vcMap = new HashMap<ValueComponent, Field>();
		for (Field f : toConfigure.getClass().getFields()) {
			if (f.isAnnotationPresent(Basic.class)) {
				ValueComponent vc;
				try {
					vc = getComponent(f);
				} catch (Exception e) {
					e.printStackTrace();

				vcMap.put(vc, f);
				JComponent component = vc.getComponent();
				JLabel label = new JLabel(f.getName());

				
				GridBagConstraints gbcComponent = new GridBagConstraints();
				GridBagConstraints gbcLabel = new GridBagConstraints();

				gbcComponent.fill = GridBagConstraints.HORIZONTAL;
				gbcComponent.gridx = gridx;
				gbcComponent.gridy = gridy;
				gbcLabel.fill = GridBagConstraints.HORIZONTAL;
				gbcLabel.gridx = gridx - 1;
				gbcLabel.gridy = gridy;
				
				contentPane.add(component, gbcComponent);
				contentPane.add(label, gbcLabel);
				gridy++;
				
			}
		}
		
		/* Add submit button */
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 0, 5);
		gbc_btnSubmit.gridx = gridx;
		gbc_btnSubmit.gridy = gridy;
		contentPane.add(btnSubmit, gbc_btnSubmit);

	}

}
