package MootCourt;

import DataAction.*;

import java.util.*;

public class ProcessMatches extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		List<Match> matches = Utils.getAllMatches(db);
		for (Match m : matches) {
			int PScore = m.P1Argument + m.P1Presentation + m.P2Argument + m.P2Presentation + m.PProfessionalism;
			int RScore = m.R1Argument + m.R1Presentation + m.R2Argument + m.R2Presentation + m.RProfessionalism;
			
			if (PScore == RScore) {
				throw new DataActionException("Ties are not allowed");
			}
			
			int pointDifference = (PScore - RScore);
			db.getEntityManager().getTransaction().begin();
			if (pointDifference > 0) {
				m.PTeam.score++;
			}
			else {
				m.RTeam.score++;
			}
			m.PTeam.pointDifferential += pointDifference;
			m.RTeam.pointDifferential -= pointDifference;
			db.getEntityManager().getTransaction().commit();
			
		}
	}

}
