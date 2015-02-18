package MockTrial;

import DataAction.AutomaticDataAction;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.*;

/**
 * Randomly creates Matches
 */
public class Round1Pairings extends AutomaticDataAction {

	private Queue<Team> getTeamsQueue() {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> root = cq.from(Team.class);
		cq.select(root);
		TypedQuery<Team> tq = db.getEntityManager().createQuery(cq);
		Queue<Team> out = new LinkedList<Team>();
		for (Team t : tq.getResultList()) {
			out.add(t);
		}
		return out;
	}
	
	// randomly pair teams
	@Override
	public void execute() {
		Queue<Team> teams = getTeamsQueue();
		List<Match> matches = new ArrayList<Match>();
		while(teams.size() != 0) {
			assert(teams.size() % 2 == 0);  // must have an even number of teams
			Team canditate1 = teams.poll();
			Team canditate2 = teams.poll();
			if (!canditate1.schoolName.equals(canditate2.schoolName)) {
				Match m = new Match(canditate1, canditate2);
				matches.add(m);
				continue;
			}
			else {
				teams = getTeamsQueue();
				randomizeQueue(teams);
				matches = new ArrayList<Match>();
			}
		}
		// now that we've matched everyone, put the matches in the database
		for (Match m : matches) {
			m.PTeam.opponents.add(m.DTeam);
			m.DTeam.opponents.add(m.PTeam);
			m.PTeam.wentP = true;
			m.DTeam.wentP = false;
			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().persist(m);
			db.getEntityManager().getTransaction().commit();
		}
		
	}
	
	private void randomizeQueue(Queue<Team> q) {
		Collections.shuffle((List<?>) q);
	}

}
