package MootCourt;

import DataAction.*;

import java.util.*;

public class ProcessMatches extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		List<Match> matches = Utils.getAllMatches(db);
		for (Match m : matches) {
			// process the individual match
			int pointDifferential = 0;
			int PBallots = 0;
			int RBallots = 0;
			for (Judge b : m.ballots) {
				int PScore = b.P1Argument + b.P1Presentation + b.P2Argument + b.P2Presentation + b.PTeamwork;
				int RScore = b.R1Argument + b.R1Presentation + b.R2Argument + b.R2Presentation + b.RTeamwork;		
				Utils.customAssert(PScore != RScore, "Tie in match " + m + " ballot " + b);
				pointDifferential += (PScore - RScore);
				if (PScore > RScore) {
					PBallots += 1;
				}
				else {
					RBallots += 1;
				}
			}

			Utils.customAssert(PBallots != RBallots, "Tie in ballot numbers");
			
			db.getEntityManager().getTransaction().begin();
			if (PBallots > RBallots) {
				m.PTeam.score++;
			}
			else {
				m.RTeam.score++;
			}
			m.PTeam.pointDifferential += pointDifferential;
			m.RTeam.pointDifferential -= pointDifferential;
			m.PTeam.hitTeams.add(m.RTeam);
			m.RTeam.hitTeams.add(m.PTeam);
			db.getEntityManager().getTransaction().commit();
			
		}
	}

}
