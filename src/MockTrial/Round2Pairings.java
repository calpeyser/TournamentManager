package MockTrial;

import DataAction.AutomaticDataAction;
import MockTrial.Utils.Swap;

import java.util.*;

/**
 * Configures the database with matches from round 2.
 * @author Cal Peyser
 *
 */
public class Round2Pairings extends AutomaticDataAction {
	
	@Override
	public void execute() {
		// get P and D constrained teams
		List<Team> PTeams = new ArrayList<Team>();
		List<Team> DTeams = new ArrayList<Team>();
		for (Team t : Utils.getAllTeams(db)) {
			if (t.wentP) {DTeams.add(t);}
			else {PTeams.add(t);}
		}
		
		// they should be the same size...
		if (PTeams.size() != DTeams.size()) {
			throw new RuntimeException("P and D teams not equal in size");
		}
		
		// sort by strength
		Collections.sort(PTeams);
		Collections.sort(DTeams);
		
		// resolve impermissible matches
		List<Swap> swaps = new ArrayList<Swap>();
		List<Team> haveSwapped = new ArrayList<Team>();
		int index = 0;
		while (index < PTeams.size()) {
			if (!Utils.matchIsPermissible(PTeams.get(index), DTeams.get(index))) {
				// get candidate swap teams
				int lookDistance = 0;  
				Map<Team, Integer> canditatesForSwapWithP = new HashMap<Team, Integer>();
				Map<Team, Integer> canditatesForSwapWithD = new HashMap<Team, Integer>();

				while(canditatesForSwapWithP.size() + canditatesForSwapWithD.size() == 0) {
					lookDistance++;
					if (index > lookDistance - 1) {
						if (!haveSwapped.contains(PTeams.get(index - lookDistance))) {canditatesForSwapWithP.put(PTeams.get(index - lookDistance), index - lookDistance);}
						if (!haveSwapped.contains(DTeams.get(index - lookDistance))) {canditatesForSwapWithD.put(DTeams.get(index - lookDistance), index - lookDistance);}
					}
					if (index < PTeams.size() - lookDistance) {
						if (!haveSwapped.contains(PTeams.get(index + lookDistance))) {canditatesForSwapWithP.put(PTeams.get(index + lookDistance), index - lookDistance);}
						if (!haveSwapped.contains(DTeams.get(index + lookDistance))) {canditatesForSwapWithD.put(DTeams.get(index + lookDistance), index - lookDistance);}
					}
				}
				// find and track best swap
				Swap bestSwap = Utils.bestSwap(canditatesForSwapWithP, canditatesForSwapWithD, PTeams.get(index), DTeams.get(index));
				swaps.add(bestSwap);
				haveSwapped.add(bestSwap.t1); haveSwapped.add(bestSwap.t2);
								
				// execute the swap
				if (PTeams.contains(bestSwap.t1) && PTeams.contains(bestSwap.t2)) {
					int index1 = PTeams.indexOf(bestSwap.t1);
					int index2 = PTeams.indexOf(bestSwap.t2);
					PTeams.set(index1, bestSwap.t2);
					PTeams.set(index2, bestSwap.t1);
				}
				else if (DTeams.contains(bestSwap.t1) && DTeams.contains(bestSwap.t2)) {
					int index1 = DTeams.indexOf(bestSwap.t1);
					int index2 = DTeams.indexOf(bestSwap.t2);
					DTeams.set(index1, bestSwap.t2);
					DTeams.set(index2, bestSwap.t1);
				}
				else {
					throw new RuntimeException("Swap Failure");
				}
				index = 0;
			}
			else {
				index ++;
			}
		}
		// set up matches
		for (int rank = 0; rank < PTeams.size(); rank++) {
			Match m = new Match(PTeams.get(rank), DTeams.get(rank));
			PTeams.get(rank).opponents.add(DTeams.get(rank));
			DTeams.get(rank).opponents.add(PTeams.get(rank));
			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().persist(m);
			db.getEntityManager().getTransaction().commit();
		}
	}
}
