package MootCourt;

import Base.Record;
import DataAction.OptionsModel;
import DataAction.DefaultUITableConfigAction;
import DataAction.DefaultUITableEditOnlyAction;

public class AssignJudges extends DefaultUITableEditOnlyAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Match.class;
	}

	@Override
	public String description() {
		return "Assign Judges to Rounds";
	}
	
	@Override
	protected OptionsModel getOptionsModel() {
		return new JudgeAssignmentOptionsModel(Match.class, db);
	}

}
