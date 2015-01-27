package DataAction;

import Base.Record;
import View.ContextFrame;
import View.RecordDialogue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.*;

import javax.swing.*;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.*;

/**
 * 
 * Translates a record into a Swing form using
 * the Metamodel API
 *
 */
public abstract class UIConfigAction extends UIDataAction {

	protected abstract Record getRecord();
	protected abstract Class<?> getRecordType();
	protected abstract String[] getAttributesToConfigure();
	
	private Map<JComponent, String> compToAttrib;
	
	private RecordDialogue dialog;
	private Metamodel metamodel;
	private EntityType<?> entityType;
	
	@Override
	public void attachToFrame(ContextFrame frame) {
		compileAttributes();
		dialog = new RecordDialogue(frame, compToAttrib);
		dialog.addListenerToSubmit(submitButton());
		dialog.setVisible(true);
	}
	
	private ActionListener submitButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// perform the database update
				Map<String, Object> values = dialog.getValue();
				// get the object to update
				Record toUpdate = getRecord();
				db.getEntityManager().getTransaction().begin();
				for (JComponent comp : compToAttrib.keySet()) {
					String attribName = compToAttrib.get(comp);
					try {
						Field field = getRecordType().getField(attribName);
						field.set(toUpdate, values.get(attribName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				db.getEntityManager().getTransaction().commit();
				
				// return to the main frame
				dialog.setVisible(false);
				dialog.dispose();
			}
		};
	}
	
	private void compileAttributes() {
		assertBound();
		compToAttrib = new HashMap<JComponent, String>();
		metamodel = db.getEntityManager().getMetamodel();
		entityType = metamodel.entity(getRecordType());
		
		String[] attribs = getAttributesToConfigure();
		
		for (String attribName : attribs) {
			Class<?> type = entityType.getAttribute(attribName).getJavaType();
			JComponent comp = createComponent(type, getValue(attribName));
			compToAttrib.put(comp, attribName);
		}
	}
	
	// uses reflection
	private Object getValue(String attribName) {
		try {
			Field f = getRecordType().getField(attribName);
			return f.get(getRecord());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private JComponent createComponent(Class<?> type, Object value) {
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
