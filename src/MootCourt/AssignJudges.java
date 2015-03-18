package MootCourt;

import Base.Record;
import DataAction.UITableConfigAction;

public class AssignJudges extends UITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Match.class;
	}

	@Override
	public String description() {
		return "Assign Judges to Rounds";
	}

}
