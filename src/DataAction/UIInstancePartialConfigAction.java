package DataAction;

import java.lang.reflect.Field;

import Base.Record;
import Data.TournamentDataStore;

public class UIInstancePartialConfigAction extends UIConfigAction {

	private Record instance;
	private Class<? extends Record> type;
	private String[] attribsToConfigure;
	
	public UIInstancePartialConfigAction (Record instance, TournamentDataStore db, String[] attribsToConfigure) {
		this.instance = instance;
		this.type = instance.getClass();
		this.attribsToConfigure = attribsToConfigure;
		this.bind(db);
		this.model = new DefaultOptionsModel(getRecordType(), db);
	}
	
	public UIInstancePartialConfigAction (Record instance, TournamentDataStore db, String[] attribsToConfigure, OptionsModel model) {
		this.instance = instance;
		this.type = instance.getClass();
		this.attribsToConfigure = attribsToConfigure;
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
		return this.attribsToConfigure;
	}

	@Override
	public String description() {
		return "Configure " + instance;
	}

	@Override
	public boolean isComplete() {
		// TODO Implement something logical for this
		return true;
	}


}
