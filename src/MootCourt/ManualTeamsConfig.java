package MootCourt;

import Base.Record;
import DataAction.DefaultUITableConfigAction;

public class ManualTeamsConfig extends DefaultUITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Team.class;
	}

	@Override
	public String description() {
		return "Configure Teams Manually";
	}

}
