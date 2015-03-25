package MootCourt;

import DataAction.*;

import java.util.*;

public class ProcessMatchesNonElimination extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		List<Match> matches = Utils.getAllMatches(db);
		for (Match m : matches) {
			// we should have one or two ballots...
			Utils.customAssert((m.ballots.size() == 1) || (m.ballots.size() == 2), "One or two judges in first rounds");
			int pointDifferential = 0;
			int PBallots = 0;
			int RBallots = 0;
						
			if (m.ballots.size() == 1) {
				Judge b = m.ballots.get(0);
				int PScore = b.P1Argument + b.P1Presentation + b.P2Argument + b.P2Presentation + b.PTeamwork;
				int RScore = b.R1Argument + b.R1Presentation + b.R2Argument + b.R2Presentation + b.RTeamwork;
				Utils.customAssert(PScore != RScore, "Tie in match " + m + " ballot " + b);
				if (PScore > RScore) {PBallots = 2;}
				else {RBallots = 2;}
				pointDifferential = (2 * (PScore - RScore));
				
				db.getEntityManager().getTransaction().begin();
				m.PTeam.ballots += PBallots;
				m.RTeam.ballots += RBallots;
				m.PTeam.ballots += pointDifferential;
				m.RTeam.ballots -= pointDifferential;
				
				b.rank1.ranks += 4;
				b.rank2.ranks += 6;
				b.rank3.ranks += 8;
				b.rank4.ranks += 10;
				
				m.PTeam.hitTeams.add(m.RTeam);
				m.RTeam.hitTeams.add(m.PTeam);
				
				db.getEntityManager().getTransaction().commit();
			}
			else {
				Judge b1 = m.ballots.get(0);
				Judge b2 = m.ballots.get(1);
				
				int PScore1 = b1.P1Argument + b1.P1Presentation + b1.P2Argument + b1.P2Presentation + b1.PTeamwork;
				int RScore1 = b1.R1Argument + b1.R1Presentation + b1.R2Argument + b1.R2Presentation + b1.RTeamwork;
				int PScore2 = b2.P2Argument + b2.P1Presentation + b2.P2Argument + b2.P2Presentation + b2.PTeamwork;
				int RScore2 = b2.R2Argument + b2.R1Presentation + b2.R2Argument + b2.R2Presentation + b2.RTeamwork;
				Utils.customAssert(PScore1 != RScore1, "Tie in match " + m + " ballot " + b1);
				Utils.customAssert(PScore2 != RScore2, "Tie in match " + m + " ballot " + b2);

				if (PScore1 > RScore1) {PBallots += 1;}
				else {RBallots += 1;}
				if (PScore2 > RScore2) {PBallots += 1;}
				else {RBallots += 1;}
				
				pointDifferential = ((PScore1 - RScore1) + (PScore2 - RScore2));
				
				db.getEntityManager().getTransaction().begin();
				m.PTeam.ballots += PBallots;
				m.RTeam.ballots += RBallots;
				m.PTeam.ballots += pointDifferential;
				m.RTeam.ballots -= pointDifferential;
				
				b1.rank1.ranks += 2;
				b1.rank2.ranks += 3;
				b1.rank3.ranks += 4;
				b1.rank4.ranks += 5;
				b2.rank1.ranks += 2;
				b2.rank2.ranks += 3;
				b2.rank3.ranks += 4;
				b2.rank4.ranks += 5;

				
				m.PTeam.hitTeams.add(m.RTeam);
				m.RTeam.hitTeams.add(m.PTeam);
				
				db.getEntityManager().getTransaction().commit();
			}
		}
	}

}
