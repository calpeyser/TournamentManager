package Checkers;

import Check.Check;
import Check.CheckFailedException;

public class ResultsEntered extends Check {

	@Override
	public void performCheck() throws CheckFailedException {
		for (Match match : Utils.getAllMatches(db)) {
			if (match.winner == null) {
				throw new CheckFailedException("Match " + match + " has no winner assigned");
			}
		}
	}

}
