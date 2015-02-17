package MockTrial;

import javax.persistence.criteria.*;
import java.util.*;

import DataAction.AutomaticDataAction;

/**
 * This class is for testing.  It arbitrarily populates matches with scores 
 * and ranks.  Warning: Do not use in real life.
 * @author Cal Peyser
 *
 */
public class TestResolveMatches extends AutomaticDataAction {

	@Override
	public void execute() {
		for (Match m : getMatches()) {
			resolveMatch(m);
		}
	}
	
	private List<Match> getMatches() {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Match> cq = cb.createQuery(Match.class);
		Root<Match> r = cq.from(Match.class);
		cq.select(r);
		List<Match> matches = db.getEntityManager().createQuery(cq).getResultList();	
		return matches;
	}
	
	private List<Player> getPlayers(Match m) {
		List<Player> out = new ArrayList<Player>();
		for (Player p : m.PTeam.players) {
			out.add(p);
		}
		for (Player p : m.DTeam.players) {
			out.add(p);
		}
		return out;
	}
	
	private void resolveMatch(Match m) {
		db.getEntityManager().getTransaction().begin();
		Random generator = new Random();
		m.Ballot1PAttDirect1 = generator.nextInt(11);
		m.Ballot1PAttDirect2 = generator.nextInt(11);
		m.Ballot1PAttDirect3 = generator.nextInt(11);
		m.Ballot1PAttCross1 = generator.nextInt(11);
		m.Ballot1PAttCross2 = generator.nextInt(11);
		m.Ballot1PAttCross3 = generator.nextInt(11);
		m.Ballot1PWitDirect1 = generator.nextInt(11);
		m.Ballot1PWitDirect2 = generator.nextInt(11);
		m.Ballot1PWitDirect3 = generator.nextInt(11);
		m.Ballot1PWitCross1 = generator.nextInt(11);
		m.Ballot1PWitCross2 = generator.nextInt(11);
		m.Ballot1PWitCross3 = generator.nextInt(11);
		m.Ballot1PClose = generator.nextInt(11);
		m.Ballot1POpen = generator.nextInt(11);
		
		m.Ballot1DAttDirect1 = generator.nextInt(11);
		m.Ballot1DAttDirect2 = generator.nextInt(11);
		m.Ballot1DAttDirect3 = generator.nextInt(11);
		m.Ballot1DAttCross1 = generator.nextInt(11);
		m.Ballot1DAttCross2 = generator.nextInt(11);
		m.Ballot1DAttCross3 = generator.nextInt(11);
		m.Ballot1DWitDirect1 = generator.nextInt(11);
		m.Ballot1DWitDirect2 = generator.nextInt(11);
		m.Ballot1DWitDirect3 = generator.nextInt(11);
		m.Ballot1DWitCross1 = generator.nextInt(11);
		m.Ballot1DWitCross2 = generator.nextInt(11);
		m.Ballot1DWitCross3 = generator.nextInt(11);
		m.Ballot1DClose = generator.nextInt(11);
		m.Ballot1DOpen = generator.nextInt(11);

		m.Ballot2PAttDirect1 = generator.nextInt(11);
		m.Ballot2PAttDirect2 = generator.nextInt(11);
		m.Ballot2PAttDirect3 = generator.nextInt(11);
		m.Ballot2PAttCross1 = generator.nextInt(11);
		m.Ballot2PAttCross2 = generator.nextInt(11);
		m.Ballot2PAttCross3 = generator.nextInt(11);
		m.Ballot2PWitDirect1 = generator.nextInt(11);
		m.Ballot2PWitDirect2 = generator.nextInt(11);
		m.Ballot2PWitDirect3 = generator.nextInt(11);
		m.Ballot2PWitCross1 = generator.nextInt(11);
		m.Ballot2PWitCross2 = generator.nextInt(11);
		m.Ballot2PWitCross3 = generator.nextInt(11);
		m.Ballot2PClose = generator.nextInt(11);
		m.Ballot2POpen = generator.nextInt(11);
		
		m.Ballot2DAttDirect1 = generator.nextInt(11);
		m.Ballot2DAttDirect2 = generator.nextInt(11);
		m.Ballot2DAttDirect3 = generator.nextInt(11);
		m.Ballot2DAttCross1 = generator.nextInt(11);
		m.Ballot2DAttCross2 = generator.nextInt(11);
		m.Ballot2DAttCross3 = generator.nextInt(11);
		m.Ballot2DWitDirect1 = generator.nextInt(11);
		m.Ballot2DWitDirect2 = generator.nextInt(11);
		m.Ballot2DWitDirect3 = generator.nextInt(11);
		m.Ballot2DWitCross1 = generator.nextInt(11);
		m.Ballot2DWitCross2 = generator.nextInt(11);
		m.Ballot2DWitCross3 = generator.nextInt(11);
		m.Ballot2DClose = generator.nextInt(11);
		m.Ballot2DOpen = generator.nextInt(11);

		List<Player> players = getPlayers(m);
		m.Ballot1Att1 = players.get(0);
		m.Ballot1Att2 = players.get(1);
		m.Ballot1Att3 = players.get(2);
		m.Ballot1Att4 = players.get(3);
		m.Ballot1Wit1 = players.get(4);
		m.Ballot1Wit2 = players.get(5);
		m.Ballot1Wit3 = players.get(6);
		m.Ballot1Wit4 = players.get(7);
		
		m.Ballot2Att1 = players.get(0);
		m.Ballot2Att2 = players.get(1);
		m.Ballot2Att3 = players.get(2);
		m.Ballot2Att4 = players.get(3);
		m.Ballot2Wit1 = players.get(4);
		m.Ballot2Wit2 = players.get(5);
		m.Ballot2Wit3 = players.get(6);
		m.Ballot2Wit4 = players.get(7);

		db.getEntityManager().getTransaction().commit();
	}
}
