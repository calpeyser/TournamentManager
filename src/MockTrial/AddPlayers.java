package MockTrial;

import Base.Record;
import DataAction.DefaultUITableConfigAction;

public class AddPlayers extends DefaultUITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Player.class;
	}

	@Override
	public String description() {
		return "Add Players to the Tournament";
	}
	
}
