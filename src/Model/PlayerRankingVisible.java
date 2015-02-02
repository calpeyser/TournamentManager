package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import Visibles.ListVisible;

public class PlayerRankingVisible extends ListVisible {

	private class playerComp implements Comparator<MockPlayer> {
		@Override
		public int compare(MockPlayer arg0, MockPlayer arg1) {
			if (arg0.score > arg1.score) {return -1;}
			if (arg0.score < arg1.score) {return 1;}
			return 0;
		}
	}
	
	@Override
	public List<String> getStrings() {
		// get all players
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MockPlayer> cq = cb.createQuery(MockPlayer.class);
		Root<MockPlayer> root = cq.from(MockPlayer.class);
		cq.select(root);
		TypedQuery<MockPlayer> query = db.getEntityManager().createQuery(cq);
		List<MockPlayer> allPlayers = query.getResultList();

		Collections.sort(allPlayers, new playerComp());
		
		List<String> out = new ArrayList<String>();
		for (MockPlayer mp : allPlayers) {
			out.add(mp.name + " with score " + mp.score);
		}
		return out;
	}

	@Override
	public String getName() {
		return "Player Rankings";
	}

}
