package MockTrial;

import Check.Check;
import Check.CheckFailedException;

import java.util.*;

import javax.persistence.criteria.*;

public class MatchesResolved extends Check {

	private List<Match> getAllMatches() {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Match> cq = cb.createQuery(Match.class);
		Root<Match> r = cq.from(Match.class);
		cq.select(r);
		return db.getEntityManager().createQuery(cq).getResultList();
	}
	
	@Override
	public void performCheck() throws CheckFailedException {
		List<Match> matches = getAllMatches();
		for (Match m : matches) {
			checkMatch(m);
		}
	}
	
	public void checkMatch(Match m) throws CheckFailedException {
		List<Player> rankedPlayers1 = new ArrayList<Player>();
		List<Player> rankedPlayers2 = new ArrayList<Player>();

		rankedPlayers1.add(m.Ballot1Att1); rankedPlayers1.add(m.Ballot1Att2); rankedPlayers1.add(m.Ballot1Att3); rankedPlayers1.add(m.Ballot1Att4);
		rankedPlayers2.add(m.Ballot2Att1); rankedPlayers2.add(m.Ballot2Att2); rankedPlayers2.add(m.Ballot2Att3); rankedPlayers2.add(m.Ballot2Att4);
		rankedPlayers1.add(m.Ballot1Wit1); rankedPlayers1.add(m.Ballot1Wit2); rankedPlayers1.add(m.Ballot1Wit3); rankedPlayers1.add(m.Ballot1Wit4);
		rankedPlayers2.add(m.Ballot2Wit1); rankedPlayers2.add(m.Ballot2Wit2); rankedPlayers2.add(m.Ballot2Wit3); rankedPlayers2.add(m.Ballot2Wit4);

		for (Player p : rankedPlayers1) {
			if (p == null) {throw new CheckFailedException("Unset rank in " + m);}
		}
		for (Player p : rankedPlayers2) {
			if (p == null) {throw new CheckFailedException("Unset rank in " + m);}
		}
		
		List<Player> duplicateChecker = new ArrayList<Player>();
		for (Player p : rankedPlayers1) {
			if (duplicateChecker.contains(p)) {
				throw new CheckFailedException("Duplicate " + p + " in ranks for " + m);
			}
			duplicateChecker.add(p);
		}
		duplicateChecker = new ArrayList<Player>();
		for (Player p : rankedPlayers2) {
			if (duplicateChecker.contains(p)){
				throw new CheckFailedException("Duplicate " + p + " in ranks for " + m);
			}
			duplicateChecker.add(p);
		}
	}

}
