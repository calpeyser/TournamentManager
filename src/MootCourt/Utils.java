package MootCourt;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Data.TournamentDataStore;

public class Utils {
	
	public static Bracket getBracket(TournamentDataStore db) {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Bracket> cq = cb.createQuery(Bracket.class);
		Root<Bracket> r = cq.from(Bracket.class);
		cq.select(r);
		List<Bracket> outList = db.getEntityManager().createQuery(cq).getResultList();
		if (outList.size() == 0) {
			return null;
		}
		customAssert(outList.size() == 1, "There are " + outList.size() + " brackets.");
		return outList.get(0);
	}
	
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
	
	public static List<Judge> getAllJudges(TournamentDataStore db) {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Judge> cq = cb.createQuery(Judge.class);
		Root<Judge> r = cq.from(Judge.class);
		cq.select(r);
		return db.getEntityManager().createQuery(cq).getResultList();
	}
	
	public static List<Judge> getAllJudgesInPlay(TournamentDataStore db) {
		List<Judge> out = new ArrayList<Judge>();
		for (Match m : getAllMatches(db)) {
			for (Judge b : m.ballots) {
				out.add(b);
			}
		}
		return out;
	}
	
	public static void customAssert(boolean test, String message) {
		if (!test) {
			throw new RuntimeException("CUSTOM ASSERTION ERROR: " + message);
		}
	}
}
