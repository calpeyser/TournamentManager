package MootCourt;

import Base.Record;
import DataAction.DefaultUITableConfigAction;

public class ManualPlayersConfig extends DefaultUITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Player.class;
	}

	@Override
	public String description() {
		return "Configure Players Manually";
	}

}
