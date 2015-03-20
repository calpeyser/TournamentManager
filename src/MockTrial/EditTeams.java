package MockTrial;

import Base.Record;
import DataAction.DefaultUITableEditOnlyAction;

public class EditTeams extends DefaultUITableEditOnlyAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Team.class;
	}

	@Override
	public String description() {
		return "Edit Teams in Database";
	}

}
