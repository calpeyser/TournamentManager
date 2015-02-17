package MockTrial;

import java.util.*;

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
	
	public static List<Player> getAllPlayers(TournamentDataStore db) {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		Root<Player> r = cq.from(Player.class);
		cq.select(r);
		return db.getEntityManager().createQuery(cq).getResultList();
	}
	
	public static boolean matchIsPermissible(Team t1, Team t2) {
		if (t1.schoolName.equals(t2.schoolName)) {
			return false;
		}
		else if (t1.opponents.contains(t2)){
			return false;
		}
		else {
			return true;
		}
	}
}
