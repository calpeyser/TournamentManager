package DataAction;

import Base.Record;
import View.ContextFrame;
import View.RecordDialogue;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.*;

import javax.swing.*;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.*;

import Utils.ConfigUtils;

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
	
	public RecordDialogue dialog;
	private Metamodel metamodel;
	private EntityType<?> entityType;
	
	@Override
	public void attachToFrame(Window frame) {
		compileAttributes();
		dialog = new RecordDialogue(frame, compToAttrib);
		dialog.addListenerToSubmit(submitButton());
		dialog.setVisible(true);
	}
	
	private ActionListener submitButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// perform the database update
				Map<String, Object> values = Utils.ConfigUtils.getValue(compToAttrib);
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
			JComponent comp = Utils.ConfigUtils.createComponent(type, getValue(attribName));
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
	
	@Override
	public void execute() {
		return;
	}
}
