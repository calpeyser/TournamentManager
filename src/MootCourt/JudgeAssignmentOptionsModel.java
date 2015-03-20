package MootCourt;

import java.util.*;

import Base.Record;
import Data.TournamentDataStore;
import DataAction.DefaultOptionsModel;

public class JudgeAssignmentOptionsModel extends DefaultOptionsModel {

	public JudgeAssignmentOptionsModel(Class<? extends Record> recordType,
			TournamentDataStore db) {
		super(recordType, db);		
	}
	
	public List<?> options(String fieldName, Record instance) {		
		if (fieldName == "ballots") {
			List<Match> otherMatches = Utils.getAllMatches(db); otherMatches.remove(instance);
			List<Judge> canditateJudges = Utils.getAllJudges(db);
			for (Match m : otherMatches) {
				for (Judge j : m.ballots) {
					canditateJudges.remove(j);
				}
			}
			return canditateJudges;
		}
		else {
			return super.options(fieldName, instance);
		}
	}
}
