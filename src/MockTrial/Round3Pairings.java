package MockTrial;

import java.util.*;
import DataAction.AutomaticDataAction;
import MockTrial.Utils.Swap;

public class Round3Pairings extends AutomaticDataAction {

	private Team getTeamByNetRank(int rank, List<Team> Teams1, List<Team> Teams2) {
		if (rank % 2 == 1) {
			return Teams2.get((rank / 2));
		}
		else {return Teams1.get(rank/2);}
	}
	
	
	@Override
	public void execute() {
		List<Team> allTeams = Utils.getAllTeams(db);
		Collections.sort(allTeams);
		
		List<Team> Teams1 = new ArrayList<Team>();
		List<Team> Teams2 = new ArrayList<Team>();

		for (int i = 0; i < allTeams.size(); i+= 2) {
			Teams1.add(allTeams.get(i));
			Teams2.add(allTeams.get(i + 1));
		}
		
		// should be the same size, half the size of allTeams
		if (Teams1.size() != Teams2.size() || Teams1.size() != allTeams.size()/2) {
			throw new RuntimeException("List messed up");
		}
		
		// resolve impermissible matches
		List<Team> haveSwapped = new ArrayList<Team>();
		int index = 0; int lookDistance = 0;
		while (index < Teams1.size() + Teams2.size()) {
			if (!Utils.matchIsPermissible(Teams1.get(index / 2), Teams2.get(index / 2))) {
				// get candidate swap teams
				lookDistance++;
				Map<Team, Integer> canditatesForSwapWith1 = new HashMap<Team, Integer>();
				Map<Team, Integer> canditatesForSwapWith2 = new HashMap<Team, Integer>();

				while(canditatesForSwapWith1.size() + canditatesForSwapWith2.size() == 0) {
					if (lookDistance > Teams1.size()/2) {
						throw new RuntimeException("Error with Round3Pairings");
					}
					if (index > lookDistance) {
						if (!haveSwapped.contains(getTeamByNetRank(index - 1 - (2*(lookDistance - 1)), Teams1, Teams2))) {
							canditatesForSwapWith1.put(getTeamByNetRank(index - 1 - (2*(lookDistance - 1)), Teams1, Teams2), (index / 2));
						}
					}
					if (index < Teams1.size() + Teams2.size() - 2) {
						if (!haveSwapped.contains(getTeamByNetRank(index + 2 + (2*(lookDistance - 1)), Teams1, Teams2))) {
							canditatesForSwapWith2.put(getTeamByNetRank(index + 2 + (2*(lookDistance - 1)), Teams1, Teams2), (index / 2));
						}
					}
				}
				// find best swap
				Swap bestSwap = Utils.bestSwap(canditatesForSwapWith1, canditatesForSwapWith2, Teams1.get(index/2), Teams2.get(index/2));
				haveSwapped.add(bestSwap.t1); haveSwapped.add(bestSwap.t2);

				// execute the swaps
				int index1 = Teams1.contains(bestSwap.t1) ? Teams1.indexOf(bestSwap.t1) : Teams1.indexOf(bestSwap.t2);
				int index2 = Teams2.contains(bestSwap.t1) ? Teams2.indexOf(bestSwap.t1) : Teams2.indexOf(bestSwap.t2);
				
				if (Teams1.contains(bestSwap.t1)) {
					Teams1.set(index1, bestSwap.t2);
					Teams2.set(index2, bestSwap.t1);
				}
				else {
					Teams1.set(index1, bestSwap.t1);
					Teams2.set(index2, bestSwap.t2);
				}
				index = 0; lookDistance = 0;
			}
			else {
				index += 2; lookDistance = 0;
			}
		}

		
		// set up matches, flip a coin 
		Random r = new Random();
		boolean P = (r.nextDouble() > 0.5) ? true : false;
		for (int rank = 0; rank < Teams1.size(); rank++) {
			Match m;
			if (P) {
				m = new Match(Teams1.get(rank), Teams2.get(rank));
				Teams1.get(rank).wentP = true;
				Teams2.get(rank).wentP = false;
			}
			else {
				m = new Match(Teams2.get(rank), Teams1.get(rank));
				Teams1.get(rank).wentP = false;
				Teams2.get(rank).wentP = true;
			}
			Teams1.get(rank).opponents.add(Teams2.get(rank));
			Teams2.get(rank).opponents.add(Teams1.get(rank));

			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().persist(m);
			db.getEntityManager().getTransaction().commit();
		}
	}

}
