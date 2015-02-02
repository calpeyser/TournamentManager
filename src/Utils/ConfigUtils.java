package Utils;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class ConfigUtils {
	/**
	 * 
	 * Returns a mapping from attribute names to given values
	 */
	public static Map<String, Object> getValue(Map<JComponent, String> compToAttrib) {
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
	
	public static JComponent createComponent(Class<?> type, Object value) {
		JComponent out;
		if (type == String.class) {
			JTextField typedOut = new JTextField();
			typedOut.setColumns(10);
			typedOut.setText((String) value);
			out = (JComponent) typedOut;
		}
		else if (type == boolean.class || type == Boolean.class) {
			JToggleButton typedOut = new JToggleButton();
			typedOut.setSelected((boolean) value);
			out = (JComponent) typedOut;
		}
		else if (type == int.class || type == Integer.class) {
			JSpinner typedOut = new JSpinner();
			typedOut.setValue(value);
			out = (JComponent) typedOut;
		}
		else {
			throw new RuntimeException("Could not parse type " + type.getName());
		}
		return out;
	}	

}
