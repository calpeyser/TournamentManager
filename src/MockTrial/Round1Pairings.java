package MockTrial;

import DataAction.AutomaticDataAction;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.*;

/**
 * Randomly creates Matches
 */
public class Round1Pairings extends AutomaticDataAction {

	private List<Team> getTeams() {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> root = cq.from(Team.class);
		cq.select(root);
		TypedQuery<Team> tq = db.getEntityManager().createQuery(cq);
		return tq.getResultList();
	}
	
	@Override
	public void execute() {
		List<Team> teams = getTeams();
	}

}
