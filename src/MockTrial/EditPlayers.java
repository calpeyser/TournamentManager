package MockTrial;

import Base.Record;
import DataAction.UITableEditOnlyAction;

public class EditPlayers extends UITableEditOnlyAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Player.class;
	}

	@Override
	public String description() {
		return "Edit Players in Database";
	}

}
