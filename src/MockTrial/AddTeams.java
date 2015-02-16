package MockTrial;

import Base.Record;
import DataAction.UITableConfigAction;

public class AddTeams extends UITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Team.class;
	}

	@Override
	public String description() {
		return "Add teams to the tournament";
	}

	@Override
	public boolean isComplete() {
		return true;
	}
	
}
