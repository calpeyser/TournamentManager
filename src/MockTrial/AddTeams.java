package MockTrial;

import Base.Record;
import DataAction.DefaultUITableConfigAction;

public class AddTeams extends DefaultUITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Team.class;
	}

	@Override
	public String description() {
		return "Add Teams to the Tournament";
	}	

}
