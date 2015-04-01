package MootCourt;

import java.util.List;

import Check.Check;
import Check.CheckFailedException;

public class AssignmentElimCheck extends Check {

	@Override
	public void performCheck() throws CheckFailedException {
		// each match must have at least one judge
		List<Match> allMatches = Utils.getAllMatches(db);
		for (Match m : allMatches) {
			if (m.ballots.size() == 0) {
				throw new CheckFailedException("No judges in " + m);
			}
			if (m.ballots.size() % 2 == 0) {
				throw new CheckFailedException("Even number of judges in " + m);
			}
		}
	}
}
