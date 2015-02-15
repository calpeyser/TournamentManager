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
		
		// for each match, update teams and players
		for (Match m : allMatches) {
			db.getEntityManager().getTransaction().begin();
			// who won?
			int PScore = m.POpen + m.PAttDirect1 + m.PAttDirect2 + m.PAttDirect3 + m.PAttCross1 + m.PAttCross2 + m.PAttCross3
					+ m.PWitDirect1 + m.PWitDirect2 + m.PWitDirect3 + m.PWitCross1 + m.PWitCross2 + m.PWitCross3 + m.PClose;
			int DScore = m.DOpen + m.DAttDirect1 + m.DAttDirect2 + m.DAttDirect3 + m.DAttCross1 + m.DAttCross2 + m.DAttCross3
					+ m.DWitDirect1 + m.DWitDirect2 + m.DWitDirect3 + m.DWitCross1 + m.DWitCross2 + m.DWitCross3 + m.DClose;
			if (PScore == DScore) {throw new RuntimeException("Tie Game!");}
			Team winner = (PScore > DScore) ? m.PTeam : m.DTeam;
			Team loser = (PScore > DScore) ? m.DTeam : m.PTeam;
			
			// assign the win
			assert(winner != loser);
			winner.wins.add(loser);
			loser.losses.add(winner);
			
			// give ranks
			
			
			db.getEntityManager().getTransaction().commit();
		}
	}

}
