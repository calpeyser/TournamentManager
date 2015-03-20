package MockTrial;

import Base.Record;
import DataAction.OptionsModel;
import DataAction.DefaultUITableEditOnlyAction;

public class EditMatches extends DefaultUITableEditOnlyAction {

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
	
}
