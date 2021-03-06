package DataAction;

import Base.Record;
import View.ContextFrame;
import View.RecordDialogue;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import javax.swing.*;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.*;

import Data.TournamentDataStore;
import Utils.ConfigUtils;

/**
 * 
 * Translates a record into a Swing form using
 * the Metamodel API
 *
 */
public abstract class UIConfigAction extends UIDataAction {

	protected abstract Record getRecord();
	protected abstract Class<? extends Record> getRecordType();
	protected abstract String[] getAttributesToConfigure();
	protected OptionsModel model;
	
	private Map<JComponent, String> compToAttrib;
	private Map<String, Field> nameToField;
	
	public RecordDialogue dialog;
	private Metamodel metamodel;
	private EntityType<?> entityType;
	
	@Override
	public void attachToFrame(Window frame) {
		super.attachToFrame(frame);
		compileAttributes();
		dialog = new RecordDialogue(frame, compToAttrib, nameToField);
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
		compToAttrib = new LinkedHashMap<JComponent, String>(); // must preserve order
		nameToField = new LinkedHashMap<String, Field>();
		metamodel = db.getEntityManager().getMetamodel();
		entityType = metamodel.entity(getRecordType());
		
		String[] attribs = getAttributesToConfigure();
		
		for (String attribName : attribs) {
			Attribute<?, ?> attrib = entityType.getAttribute(attribName);
			
			Field f = null;
			try {
				f = getRecordType().getField(attribName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Annotation[] annotations = f.getAnnotations();
			
			if (model == null) {
				model = new DefaultOptionsModel(getRecordType(), db);
			}
			
			JComponent comp = Utils.ConfigUtils.createComponent(attribName, attrib, annotations, getRecord(), getValue(attribName), model);
			compToAttrib.put(comp, attribName);
			nameToField.put(attribName, f);
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
}
