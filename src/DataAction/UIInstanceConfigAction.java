package DataAction;

import java.lang.reflect.Field;

import Base.Record;
import Data.TournamentDataStore;

public class UIInstanceConfigAction extends UIConfigAction {

	private Record instance;
	private Class<? extends Record> type;
	
	public UIInstanceConfigAction (Record instance, TournamentDataStore db) {
		this.instance = instance;
		this.type = instance.getClass();
		this.bind(db);
		this.model = new DefaultOptionsModel(getRecordType(), db);
	}
	
	public UIInstanceConfigAction (Record instance, TournamentDataStore db, OptionsModel model) {
		this.instance = instance;
		this.type = instance.getClass();
		this.bind(db);
		this.model = model;
	}	
	
	@Override
	protected Record getRecord() {
		return instance;
	}

	@Override
	protected Class<? extends Record> getRecordType() {
		return type;
	}

	@Override
	protected String[] getAttributesToConfigure() {
		Field[] attributes = type.getFields();
		String[] out = new String[attributes.length];
		for (int i = 0; i < attributes.length; i++) {
			out[i] = attributes[i].getName();
		}
		return out;
	}

	@Override
	public String description() {
		return "Configure " + instance;
	}

	@Override
	public boolean isComplete() {
		// TODO: Implement something logical for this.
		return true;
	}

}
