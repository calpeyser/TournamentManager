package Checkers;

import java.util.List;
import Visibles.TableVisible;

public class CurrentMatches extends TableVisible {

	@Override
	public Object[] getColumnNames() {
		String[] names = {"Player 1", "Player 2"};
		return names;
	}

	@Override
	public Object[][] getData() {
		List<Match> matches = Utils.getAllMatches(db);
		Object[][] data = new Object[matches.size()][2];
		for (int matchIndex = 0; matchIndex < matches.size(); matchIndex++) {
			data[matchIndex][0] = matches.get(matchIndex).player1;
			data[matchIndex][1] = matches.get(matchIndex).player2;
		}
		return data;		
	}

	@Override
	public String getName() {
		return "Current Matches";
	}

}
