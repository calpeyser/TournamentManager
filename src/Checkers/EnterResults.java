package Checkers;

import Base.Record;
import DataAction.DBTableModel;
import DataAction.DefaultUITableEditOnlyAction;
import DataAction.OptionsModel;
import DataAction.UITableEditOnlyAction;

public class EnterResults extends DefaultUITableEditOnlyAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Match.class;
	}

	@Override
	protected OptionsModel getOptionsModel() {
		return new ResultsOptionsModel(Match.class, db);
	}

	@Override
	public String description() {
		return "Enter Results of Matches";
	}
}
