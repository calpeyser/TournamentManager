package Checkers;

import java.util.List;

import javax.persistence.criteria.*;

import Data.TournamentDataStore;


public class Utils {
	
	public static List<Player> getAllPlayers(TournamentDataStore db) {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		Root<Player> r = cq.from(Player.class);
		cq.select(r);
		return db.getEntityManager().createQuery(cq).getResultList();
	}
	
	public static List<Match> getAllMatches(TournamentDataStore db) {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Match> cq = cb.createQuery(Match.class);
		Root<Match> r = cq.from(Match.class);
		cq.select(r);
		return db.getEntityManager().createQuery(cq).getResultList();
	}
}
