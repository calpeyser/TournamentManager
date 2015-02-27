package Utils;

import java.lang.annotation.Annotation;
import java.util.*;

import javax.persistence.*;
import javax.persistence.metamodel.Attribute;
import javax.swing.*;

import View.CustomDoubleField;
import View.JNumberTextField;
import Base.Record;
import DataAction.OptionsModel;

public class ConfigUtils {
	/**
	 * 
	 * Returns a mapping from attribute names to given values
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getValue(Map<JComponent, String> compToAttrib) {
		Map<String, Object> out = new HashMap<String, Object>();
		for (JComponent comp : compToAttrib.keySet()) {
			Object value = null;
			if (comp.getClass() == CustomDoubleField.class) {
				value = ((CustomDoubleField)comp).getDoubleValue();
			}
			else if (comp.getClass() == JTextField.class) {
				value = ((JTextField)comp).getText();
			}
			else if (comp.getClass() == JToggleButton.class) {
				value = ((JToggleButton)comp).isSelected();
			}
			else if (comp.getClass() == JSpinner.class) {
				value = ((JSpinner)comp).getValue();
			}
			else if (comp.getClass() == JComboBox.class) {
				value = ((JComboBox<Object>)comp).getSelectedItem();
			}
			else if (comp.getClass() == JScrollPane.class) {
				JList<Object> list = retrieveList((JScrollPane)comp);
				value = list.getSelectedValuesList();
			}
			else {
				throw new RuntimeException("Could not parse component " + comp.getName());
			}
			out.put(compToAttrib.get(comp), value);
		}
		return out;
	}
	
	public static JComponent createComponent(String attribName, Attribute<?,?> attrib, Annotation[] annotations, Record instance, Object value, OptionsModel optionsModel) {
		JComponent out;
		Class<?> type = attrib.getJavaType();
		// basic
		if (type == String.class) {
			JTextField typedOut = new JTextField();
			typedOut.setColumns(10);
			typedOut.setText((String) value);
			out = (JComponent) typedOut;
		}
		else if (type == boolean.class) {
			JToggleButton typedOut = new JToggleButton();
			typedOut.setSelected((boolean) value);
			out = (JComponent) typedOut;
		}
		else if (type == int.class) {
			if (optionsModel.options(attribName, instance) == null) {
				JSpinner typedOut = new JSpinner(new SpinnerNumberModel());
				typedOut.setValue(value);
				out = (JComponent) typedOut;
			}
			else { 
				List<?> options = optionsModel.options(attribName, instance);
				JSpinner typedOut = new JSpinner(new SpinnerListModel(options));
				typedOut.setValue(value);
				out = (JComponent) typedOut;
			}
		}
		else if (type == double.class) {
			CustomDoubleField typedOut = new CustomDoubleField();
			typedOut.setDouble((double) value);
			out = (JComponent) typedOut;
		}
		// one-to-one
		else if (type.isAnnotationPresent(Entity.class)) {
			List<?> optionsList = optionsModel.options(attribName, instance);
			Object[] options = new Object[optionsList.size()];
			for (int i = 0; i < optionsList.size(); i++) {
				options[i] = optionsList.get(i);
			}
			JComboBox<Object> typedOut = new JComboBox<Object>(options);
			typedOut.setSelectedItem(value);
			
			out = (JComponent) typedOut;	
		}
		// many-to-one
		else if (MiscUtils.isOneToMany(annotations)) {
			List<?> optionsList = optionsModel.options(attribName, instance);
			Object[] options = new Object[optionsList.size()];
			for (int i = 0; i < optionsList.size(); i++) {
				options[i] = optionsList.get(i);
			}
			JList<Object> list = new JList<Object>(options);
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			// select the correct elements
			@SuppressWarnings("unchecked")
			Iterable<Object> valueList = (Iterable<Object>)value;
			List<Integer> indexes = new ArrayList<Integer>();
			for (Object v : valueList) {
				indexes.add(optionsList.indexOf(v));
			}
			list.setSelectedIndices(MiscUtils.listToArrayInt(indexes));
			
			out = new JScrollPane(list);
		}
		else {
			throw new RuntimeException("Could not parse type " + type.getName());
		}
		return out;
	}
	
	private static JList<Object> retrieveList(JScrollPane pane) {
			JViewport viewport = pane.getViewport();
			@SuppressWarnings("unchecked")
			JList<Object> list = (JList<Object>)viewport.getView();
			return list;
	}
	
}
