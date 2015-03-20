package MootCourt;

import java.util.List;

import Visibles.TableVisible;

public class CurrentMatches extends TableVisible {

	@Override
	public Object[] getColumnNames() {
		String[] out = {"Petitioner", "Respondent"};
		return out;
	}

	@Override
	public Object[][] getData() {
		List<Match> matches = Utils.getAllMatches(db);
		Object[][] out = new Object[matches.size()][2];
		for (int matchIndex = 0; matchIndex < matches.size(); matchIndex++) {
			out[matchIndex][0] = matches.get(matchIndex).PTeam;
			out[matchIndex][1] = matches.get(matchIndex).RTeam;
		}
		return out;	}

	@Override
	public String getName() {
		return "Current Matches";
	}

}
