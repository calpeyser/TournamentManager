package MootCourt;

import java.util.*;

import javax.persistence.TypedQuery;

import Base.Record;
import DataAction.DBTableModel;
import DataAction.DefaultOptionsModel;
import DataAction.DefaultUITableEditOnlyAction;
import DataAction.OptionsModel;
import DataAction.UITableEditOnlyAction;

public class EnterBallotResults extends UITableEditOnlyAction {

	@Override
	protected Class<? extends Record> getRecordType() {
		return Judge.class;
	}

	@Override
	public String description() {
		return "Enter Ballot Results";
	}
	
	@Override 
	protected DBTableModel getData() {
		return new DBTableModel(db, Judge.class) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 851224486283854131L;

			@Override
			public void refresh() {
				List<Judge> out = new ArrayList<Judge>();
				for (Match m : Utils.getAllMatches(db)) {
					for (Judge b : m.ballots) {
						out.add(b);
					}
				}
				data = out;
			}
		};
	}

	@Override
	protected OptionsModel getOptionsModel() {
		return new BallotResultsOptionsModel(getRecordType(), db);
	}
	
}
