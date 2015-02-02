package DataAction;

import java.lang.reflect.Field;

import Base.Record;
import Data.TournamentDataStore;

public class UIInstancePartialConfigAction extends UIConfigAction {

	private Record instance;
	private Class<?> type;
	private String[] attribsToConfigure;
	
	public UIInstancePartialConfigAction (Record instance, TournamentDataStore db, String[] attribsToConfigure) {
		this.instance = instance;
		this.type = instance.getClass();
		this.attribsToConfigure = attribsToConfigure;
		this.bind(db);
	}
	
	@Override
	protected Record getRecord() {
		return instance;
	}

	@Override
	protected Class<?> getRecordType() {
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


}
