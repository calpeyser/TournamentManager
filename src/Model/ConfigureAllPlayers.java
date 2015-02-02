package Model;

import Base.Record;
import DataAction.UITableConfigAction;

public class ConfigureAllPlayers extends UITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return MockPlayer.class;
	}

	@Override
	public String description() {
		return "Configure All Players";
	}

}
