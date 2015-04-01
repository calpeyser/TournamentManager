package Checkers;

import Base.Record;
import DataAction.DBTableModel;
import DataAction.DefaultUITableConfigAction;
import DataAction.OptionsModel;
import DataAction.UITableConfigAction;

public class ConfigurePlayers extends DefaultUITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Player.class;
	}

	@Override
	public String description() {
		return "Configure Players Manually";
	}
}
