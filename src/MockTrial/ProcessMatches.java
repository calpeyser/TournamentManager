package MockTrial;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import DataAction.AutomaticDataAction;

public class ProcessMatches extends AutomaticDataAction {

	@Override
	public void execute() {
		// get matches
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Match> cq = cb.createQuery(Match.class);
		Root<Match> r = cq.from(Match.class);
		cq.select(r);
		TypedQuery<Match> q = db.getEntityManager().createQuery(cq);
		List<Match> allMatches = q.getResultList();
		
		// for each match and each ballot, update teams and players
		for (Match m : allMatches) {
			db.getEntityManager().getTransaction().begin();
			// who won?
			int Ballot1PScore = m.Ballot1POpen + m.Ballot1PAttDirect1 + m.Ballot1PAttDirect2 + m.Ballot1PAttDirect3 + m.Ballot1PAttCross1 + m.Ballot1PAttCross2 + m.Ballot1PAttCross3
					+ m.Ballot1PWitDirect1 + m.Ballot1PWitDirect2 + m.Ballot1PWitDirect3 + m.Ballot1PWitCross1 + m.Ballot1PWitCross2 + m.Ballot1PWitCross3 + m.Ballot1PClose;
			int Ballot1DScore = m.Ballot1DOpen + m.Ballot1DAttDirect1 + m.Ballot1DAttDirect2 + m.Ballot1DAttDirect3 + m.Ballot1DAttCross1 + m.Ballot1DAttCross2 + m.Ballot1DAttCross3
					+ m.Ballot1DWitDirect1 + m.Ballot1DWitDirect2 + m.Ballot1DWitDirect3 + m.Ballot1DWitCross1 + m.Ballot1DWitCross2 + m.Ballot1DWitCross3 + m.Ballot1DClose;
			
			int Ballot2PScore = m.Ballot2POpen + m.Ballot2PAttDirect1 + m.Ballot2PAttDirect2 + m.Ballot2PAttDirect3 + m.Ballot2PAttCross1 + m.Ballot2PAttCross2 + m.Ballot2PAttCross3
					+ m.Ballot2PWitDirect1 + m.Ballot2PWitDirect2 + m.Ballot2PWitDirect3 + m.Ballot2PWitCross1 + m.Ballot2PWitCross2 + m.Ballot2PWitCross3 + m.Ballot2PClose;
			int Ballot2DScore = m.Ballot2DOpen + m.Ballot2DAttDirect1 + m.Ballot2DAttDirect2 + m.Ballot2DAttDirect3 + m.Ballot2DAttCross1 + m.Ballot2DAttCross2 + m.Ballot2DAttCross3
					+ m.Ballot2DWitDirect1 + m.Ballot2DWitDirect2 + m.Ballot2DWitDirect3 + m.Ballot2DWitCross1 + m.Ballot2DWitCross2 + m.Ballot2DWitCross3 + m.Ballot2DClose;

			
			// score
			if (Ballot1PScore == Ballot1DScore) {
				m.PTeam.score += 0.5;
				m.DTeam.score += 0.5;
			}
			else {
				Team Ballot1Winner = (Ballot1PScore > Ballot1DScore) ? m.PTeam : m.DTeam;
				Ballot1Winner.score += 1;	
			}
			if (Ballot2PScore == Ballot2DScore) {
				m.PTeam.score += 0.5;
				m.DTeam.score += 0.5;
			}
			else {
				Team Ballot2Winner = (Ballot2PScore > Ballot2DScore) ? m.PTeam : m.DTeam;
				Ballot2Winner.score += 1;
			}
			
			// point differential
			m.PTeam.PointDifferential += (Ballot1PScore - Ballot1DScore);
			m.DTeam.PointDifferential += (Ballot1DScore - Ballot1PScore);
			m.PTeam.PointDifferential += (Ballot2PScore - Ballot2DScore);
			m.DTeam.PointDifferential += (Ballot2DScore - Ballot2PScore);
			
			
			// CS
			int PTeamCS = 0;
			for (Team opponent : m.PTeam.opponents) {
				PTeamCS += opponent.score;
			}
			m.PTeam.CombinedStrength = PTeamCS;
			int DTeamCS = 0;
			for (Team opponent : m.DTeam.opponents) {
				DTeamCS += opponent.score;
			}
			m.DTeam.CombinedStrength = DTeamCS;
			
			// give ranks	
			if (isP(m.Ballot1Att1, m)) {m.Ballot1Att1.PAttRanks += 5;} else {m.Ballot1Att1.DAttRanks += 5;}
			if (isP(m.Ballot1Att2, m)) {m.Ballot1Att2.PAttRanks += 4;} else {m.Ballot1Att2.DAttRanks += 4;}
			if (isP(m.Ballot1Att3, m)) {m.Ballot1Att3.PAttRanks += 3;} else {m.Ballot1Att3.DAttRanks += 3;}
			if (isP(m.Ballot1Att4, m)) {m.Ballot1Att4.PAttRanks += 2;} else {m.Ballot1Att4.DAttRanks += 2;}
			if (isP(m.Ballot1Wit1, m)) {m.Ballot1Wit1.PWitRanks += 5;} else {m.Ballot1Wit1.DWitRanks += 5;}
			if (isP(m.Ballot1Wit2, m)) {m.Ballot1Wit2.PWitRanks += 4;} else {m.Ballot1Wit2.DWitRanks += 4;}
			if (isP(m.Ballot1Wit3, m)) {m.Ballot1Wit3.PWitRanks += 3;} else {m.Ballot1Wit3.DWitRanks += 3;}
			if (isP(m.Ballot1Wit4, m)) {m.Ballot1Wit4.PWitRanks += 2;} else {m.Ballot1Wit4.DWitRanks += 2;}
			
			if (isP(m.Ballot2Att1, m)) {m.Ballot2Att1.PAttRanks += 5;} else {m.Ballot2Att1.DAttRanks += 5;}
			if (isP(m.Ballot2Att2, m)) {m.Ballot2Att2.PAttRanks += 4;} else {m.Ballot2Att2.DAttRanks += 4;}
			if (isP(m.Ballot2Att3, m)) {m.Ballot2Att3.PAttRanks += 3;} else {m.Ballot2Att3.DAttRanks += 3;}
			if (isP(m.Ballot2Att4, m)) {m.Ballot2Att4.PAttRanks += 2;} else {m.Ballot2Att4.DAttRanks += 2;}
			if (isP(m.Ballot2Wit1, m)) {m.Ballot2Wit1.PWitRanks += 5;} else {m.Ballot2Wit1.DWitRanks += 5;}
			if (isP(m.Ballot2Wit2, m)) {m.Ballot2Wit2.PWitRanks += 4;} else {m.Ballot2Wit2.DWitRanks += 4;}
			if (isP(m.Ballot2Wit3, m)) {m.Ballot2Wit3.PWitRanks += 3;} else {m.Ballot2Wit3.DWitRanks += 3;}
			if (isP(m.Ballot2Wit4, m)) {m.Ballot2Wit4.PWitRanks += 2;} else {m.Ballot2Wit4.DWitRanks += 2;}

			
			db.getEntityManager().getTransaction().commit();
		}
	}
	
	private boolean isP(Player p, Match m) {
		return m.PTeam.players.contains(p);
	}

}
