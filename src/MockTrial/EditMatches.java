package MockTrial;

import Base.Record;
import DataAction.OptionsModel;
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
	
	@Override
	protected OptionsModel getOptionsModel() {
		return new MatchOptionsModel(Match.class, db);
	}
	
	@Override
	public boolean isComplete() {
		if (data == null) {
			return false;
		}
		for (Object o : data.data) {
			Match m = (Match) o;
			if (!m.isSet()) {
				return false;
			}
		}
		return true;
	}
	
}
