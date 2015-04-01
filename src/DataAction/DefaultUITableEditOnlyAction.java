package DataAction;

import java.awt.Window;

import View.TableDialog;
import Base.Record;

public abstract class DefaultUITableEditOnlyAction extends DefaultUITableConfigAction {

	protected abstract Class<? extends Record> getRecordType();
	
	protected OptionsModel getOptionsModel() {
		return new DefaultOptionsModel(getRecordType(), db);
	}
	
	protected DBTableModel getData() {
		return new DBTableModel(db, getRecordType());
	}
	
	@Override
	public void attachToFrame(Window frame) {
		data = getData();
		dialog = new TableDialog(frame, data);
		dialog.addListenerToExit(exitButton());
		dialog.addListenerToEdit(editButton());
		dialog.disableAdd();
		dialog.disableDelete();
		dialog.setVisible(true);		
	}
}
