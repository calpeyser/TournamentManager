package MockTrial;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import DataAction.AutomaticDataAction;

public class ClearMatches extends AutomaticDataAction {

	@Override
	public void execute() {
		// get matches
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Match> cq = cb.createQuery(Match.class);
		Root<Match> r = cq.from(Match.class);
		cq.select(r);
		TypedQuery<Match> q = db.getEntityManager().createQuery(cq);
		List<Match> allMatches = q.getResultList();

		// delete matches
		db.getEntityManager().getTransaction().begin();
		for (Match m : allMatches) {
			db.getEntityManager().remove(m);
		}
		db.getEntityManager().getTransaction().commit();
	}

}
