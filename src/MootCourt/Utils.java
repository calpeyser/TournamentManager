package MootCourt;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Data.TournamentDataStore;

public class Utils {
	public static List<Match> getAllMatches(TournamentDataStore db) {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Match> cq = cb.createQuery(Match.class);
		Root<Match> r = cq.from(Match.class);
		cq.select(r);
		return db.getEntityManager().createQuery(cq).getResultList();
	}
	
	public static List<Team> getAllTeams(TournamentDataStore db) {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> r = cq.from(Team.class);
		cq.select(r);
		return db.getEntityManager().createQuery(cq).getResultList();
	}
}
