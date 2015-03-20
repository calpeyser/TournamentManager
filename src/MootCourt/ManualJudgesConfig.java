package MootCourt;

import Base.Record;
import DataAction.DefaultUITableConfigAction;

public class ManualJudgesConfig extends DefaultUITableConfigAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Judge.class;
	}

	@Override
	public String description() {
		return "Configure Judges Manually";
	}

}
