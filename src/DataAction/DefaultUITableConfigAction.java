package DataAction;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;
import java.util.*;

import javax.persistence.TypedQuery;

import Utils.MiscUtils;
import View.*;
import Base.*;

public abstract class DefaultUITableConfigAction extends UITableConfigAction {

	protected abstract Class<? extends Record> getRecordType();
	
	protected OptionsModel getOptionsModel() {
		return new DefaultOptionsModel(getRecordType(), db);
	}
	
	protected DBTableModel getData() {
		return new DBTableModel(db, getRecordType());
	}
	
}
