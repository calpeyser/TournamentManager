package Checkers;

import Base.Record;
import Data.TournamentDataStore;
import DataAction.DefaultOptionsModel;


import java.util.*;

public class ResultsOptionsModel extends DefaultOptionsModel {

	public ResultsOptionsModel(Class<? extends Record> recordType,
			TournamentDataStore db) {
		super(recordType, db);
	}

	@Override
	public List<?> options(String fieldName, Record instance) {
		if (fieldName.equals("winner")) {
			Match match = (Match)instance;
			List<Player> possibilities = new ArrayList<Player>();
			possibilities.add(match.player1); possibilities.add(match.player2);
			return possibilities;
		}
		if (fieldName.equals("turns")) {
			List<Integer> possibilities = new ArrayList<Integer>();
			for (int i = 0; i < 200; i++) {
				possibilities.add(i);
			}
			return possibilities;
		}
		else {
			return super.options(fieldName, instance);
		}
	}
}
