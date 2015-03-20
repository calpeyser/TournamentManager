package MootCourt;

import java.util.*;

import Base.Record;
import Data.TournamentDataStore;
import DataAction.DefaultOptionsModel;

public class BallotResultsOptionsModel extends DefaultOptionsModel {

	private List<String> rank = new ArrayList<String>();
	private List<String> oneToTen = new ArrayList<String>();
	
	public BallotResultsOptionsModel(Class<? extends Record> recordType,
			TournamentDataStore db) {
		super(recordType, db);
		rank.add("rank1");
		rank.add("rank2");
		rank.add("rank3");
		rank.add("rank4");
		oneToTen.add("P1Presentation");
		oneToTen.add("P1Argument");
		oneToTen.add("P2Presentation");
		oneToTen.add("P2Argument");
		oneToTen.add("PTeamwork");
		oneToTen.add("R1Presentation");
		oneToTen.add("R1Argument");
		oneToTen.add("R2Presentation");
		oneToTen.add("R2Argument");
		oneToTen.add("RTeamwork");
	}

	public List<?> options(String fieldName, Record instance) {		
		if (rank.contains(fieldName)) {
			Match m = ((Judge)instance).currentMatch;
			List<Player> out = new ArrayList<Player>();
			out.add(m.PTeam.player1);
			out.add(m.PTeam.player2);
			out.add(m.RTeam.player1);
			out.add(m.RTeam.player2);
			return out;
		}
		else if (oneToTen.contains(fieldName)){
			List<Integer> out = new ArrayList<Integer>();
			for (int i = 0; i <= 10; i++) {
				out.add(i);
			}
			return out;
		} 
		else { 
			return super.options(fieldName, instance);
		}
	}
	
}
