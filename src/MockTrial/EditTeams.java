package MockTrial;

import Base.Record;
import DataAction.UITableEditOnlyAction;

public class EditTeams extends UITableEditOnlyAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Team.class;
	}

	@Override
	public String description() {
		return "Edit Teams in Database";
	}

}
