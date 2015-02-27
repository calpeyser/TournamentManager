package MockTrial;

import Base.Record;
import DataAction.UITableConfigAction;

public class AddPlayers extends UITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Player.class;
	}

	@Override
	public String description() {
		return "Add Players to the Tournament";
	}
	
}
