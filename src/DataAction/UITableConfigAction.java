package DataAction;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;
import java.util.*;

import Utils.MiscUtils;
import View.*;
import Base.*;

public abstract class UITableConfigAction extends UIDataAction {

	protected abstract Class<? extends Record> getRecordType();
	protected TableDialog dialog;
	protected DBTableModel data;
	
	protected OptionsModel getOptionsModel() {
		return new DefaultOptionsModel(getRecordType(), db);
	}
	
	@Override
	public void attachToFrame(Window frame) {
		data = new DBTableModel(db, getRecordType());
		dialog = new TableDialog(frame, data);
		dialog.addListenerToExit(exitButton());
		dialog.addListenerToDelete(deleteButton());
		dialog.addListenerToEdit(editButton());
		dialog.addListenerToAdd(addButton());
		dialog.setVisible(true);		
	}

	protected ActionListener addButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// create the new database object and persist it
				Object databaseObject = null;
				try {
					databaseObject = getRecordType().newInstance();
					db.getEntityManager().getTransaction().begin();
					db.getEntityManager().persist(databaseObject);
					db.getEntityManager().getTransaction().commit();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				// which fields of this thing do we need to configure?
				List<String> attribsToConfig = new ArrayList<String>();
				for (Field f : getRecordType().getFields()) {
					if (f.isAnnotationPresent(MustBeSet.class)) {
						attribsToConfig.add(f.getName());
					}
				}
				// create the UIConfigAction to create this object
				UIConfigAction configure = new UIInstancePartialConfigAction((Record)databaseObject, db, MiscUtils.listToArray(attribsToConfig), getOptionsModel());

				// attach it
				configure.attachToFrame(dialog);
				data.fireTableDataChanged();
			}
		};
	}
	
	// edit a row in the database
	protected ActionListener editButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = dialog.table.getSelectedRow();
				if (selectedRow == -1) {
					return; // there is nothing selected
				}
				Object databaseObject = data.data.get(selectedRow);
				
				// create a UIConfigAction to attach to this thing
				UIConfigAction configure = new UIInstanceConfigAction((Record)databaseObject, db, getOptionsModel());
	
				// attach it
				configure.attachToFrame(dialog);
				data.fireTableDataChanged();
			}
		};
	}
	
	// delete a database entry
	protected ActionListener deleteButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = dialog.table.getSelectedRow();
				if (selectedRow == -1) {
					return; // there is nothing selected
				}
				Object databaseObject = data.data.get(selectedRow);
				db.getEntityManager().getTransaction().begin();
				db.getEntityManager().remove(databaseObject);
				db.getEntityManager().getTransaction().commit();
				data.fireTableDataChanged();
			}
		};
	}
	
	// standard disposal listener
	protected ActionListener exitButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.setVisible(false);
				dialog.dispose();	
			}
		};
	}
}
