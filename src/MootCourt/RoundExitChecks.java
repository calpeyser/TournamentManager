package MootCourt;

import Check.Check;
import Check.CheckFailedException;
import java.util.*;

public class RoundExitChecks extends Check {

	@Override
	public void performCheck() throws CheckFailedException {
		List<Judge> judgesInPlay = Utils.getAllJudgesInPlay(db);
		
		for (Judge b : judgesInPlay) {
			int PScore = b.P1Argument + b.P2Argument + b.P1Presentation + b.P2Presentation + b.PTeamwork;
			int RScore = b.R1Argument + b.R2Argument + b.R1Presentation + b.R2Presentation + b.RTeamwork;
			if (PScore == RScore) {
				throw new CheckFailedException("Tie in ballot of " + b);
			}
			if (b.rank1 == b.rank2) {throw new CheckFailedException("Rank 1 and Rank 2 are both" + b.rank1 + " in ballot of " + b);}
			if (b.rank1 == b.rank3) {throw new CheckFailedException("Rank 1 and Rank 3 are both" + b.rank1 + " in ballot of " + b);}
			if (b.rank1 == b.rank4) {throw new CheckFailedException("Rank 1 and Rank 4 are both" + b.rank1 + " in ballot of " + b);}
			if (b.rank2 == b.rank3) {throw new CheckFailedException("Rank 2 and Rank 3 are both" + b.rank2 + " in ballot of " + b);}
			if (b.rank2 == b.rank4) {throw new CheckFailedException("Rank 2 and Rank 4 are both" + b.rank2 + " in ballot of " + b);}
			if (b.rank3 == b.rank4) {throw new CheckFailedException("Rank 3 and Rank 4 are both" + b.rank3 + " in ballot of " + b);}

		}	
	}

}
