package MockTrial;

import Visibles.TableVisible;
import java.util.*;

public class CurrentMatches extends TableVisible {

	@Override
	public Object[] getColumnNames() {
		String[] out = {"P Team", "D Team"};
		return out;
	}

	@Override
	public Object[][] getData() {
		List<Match> matches = Utils.getAllMatches(db);
		Object[][] out = new Object[matches.size()][2];
		for (int matchIndex = 0; matchIndex < matches.size(); matchIndex++) {
			out[matchIndex][0] = matches.get(matchIndex).PTeam;
			out[matchIndex][1] = matches.get(matchIndex).DTeam;
		}
		return out;
	}

	@Override
	public String getName() {
		return "Current Matches";
	}

}
