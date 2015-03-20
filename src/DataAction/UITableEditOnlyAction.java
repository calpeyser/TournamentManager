package DataAction;

import java.awt.Window;

import View.TableDialog;
import Base.Record;

public abstract class UITableEditOnlyAction extends UITableConfigAction {

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
