package Model;

import Base.Record;
import DataAction.UITableConfigAction;

public class ConfigureAllTeams extends UITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return MockTeam.class;
	}

	@Override
	public String description() {
		return "Configure all MockTeams";
	}

}
