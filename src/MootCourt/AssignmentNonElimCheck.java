package MootCourt;

import Check.Check;
import Check.CheckFailedException;
import java.util.*;

public class AssignmentNonElimCheck extends Check {

	@Override
	public void performCheck() throws CheckFailedException {
		// there must be either one or two judges
		List<Match> allMatches = Utils.getAllMatches(db);
		for (Match m : allMatches) {
			if (m.ballots.size() == 0) {
				throw new CheckFailedException("No judges in " + m);
			}
			if (m.ballots.size() > 2) {
				throw new CheckFailedException(m.ballots.size() + " judges in " + m);
			}
		}
	}
}
