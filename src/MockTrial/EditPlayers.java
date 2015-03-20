package MockTrial;

import Base.Record;
import DataAction.DefaultUITableEditOnlyAction;

public class EditPlayers extends DefaultUITableEditOnlyAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Player.class;
	}

	@Override
	public String description() {
		return "Edit Players in Database";
	}

}
