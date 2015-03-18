package MootCourt;

import Base.Record;
import DataAction.UITableEditOnlyAction;

public class EditMatches extends UITableEditOnlyAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Match.class;
	}

	@Override
	public String description() {
		return "Enter Match Results";
	}

}
