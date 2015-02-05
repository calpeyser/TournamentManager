package Model;

import Base.Record;
import DataAction.UITableConfigAction;

public class ConfigureAllMatches extends UITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return MockMatch.class;
	}

	@Override
	public String description() {
		return "Configure all matches";
	}

}
