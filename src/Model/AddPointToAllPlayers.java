package Model;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Data.TournamentDataStore;
import DataAction.AutomaticDataAction;

public class AddPointToAllPlayers extends AutomaticDataAction {

	@Override
	public void execute() {
		assertBound();
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MockPlayer> cq = cb.createQuery(MockPlayer.class);
		Root<MockPlayer> root = cq.from(MockPlayer.class);
		cq.select(root);
		
		TypedQuery<MockPlayer> query = db.getEntityManager().createQuery(cq);
		List<MockPlayer> out = query.getResultList();
		
		for (MockPlayer p : out) {
			p.score++;
		}		
	}

}
