package MootCourt;

import DataAction.AutomaticDataAction;
import DataAction.DataActionException;

import java.util.*;

public class ProcessMatchesElimination extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		List<Match> matches = Utils.getAllMatches(db);
		for (Match m : matches) {
			int PBallots = 0;
			int RBallots = 0;
			for (Judge b : m.ballots) {
				int PScore = b.P1Argument + b.P1Presentation + b.P2Argument + b.P2Presentation + b.PTeamwork;
				int RScore = b.R1Argument + b.R1Presentation + b.R2Argument + b.R2Presentation + b.RTeamwork;
				Utils.customAssert(PScore != RScore, "Tie in match " + m + " ballot " + b);
				if (PScore < RScore) {
					PBallots++;
				}
				else {
					RBallots++;
				}
			}
			Utils.customAssert(PBallots != RBallots, "Tie in ballots for match " + m);
			db.getEntityManager().getTransaction().begin();
			if (PBallots > RBallots) { 
				m.RTeam.eliminated = true;
			}
			else {
				m.RTeam.eliminated = true;
			}
			db.getEntityManager().getTransaction().commit();
		}

	}

}
