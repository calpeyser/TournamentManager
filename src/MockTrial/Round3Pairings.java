package MockTrial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import DataAction.AutomaticDataAction;

public class Round3Pairings extends AutomaticDataAction {

	private class Swap {
		private Team t1; private Team t2;
		public Swap(Team t1, Team t2) {
			this.t1 = t1; this.t2 = t2;
		}
		
		public double ballotDifference() {
			return Math.abs(t1.score - t2.score);
		}
		
		public double combinedStrengthDifference() {
			return Math.abs(t1.CombinedStrength - t2.CombinedStrength);
		}
		
		public int pointDifferentialDifference() {
			return Math.abs(t1.PointDifferential - t2.PointDifferential);
		}
		
		public void execute(List<Team> Teams1, List<Team> Teams2) {
			int index1 = Teams1.contains(t1) ? Teams1.indexOf(t1) : Teams1.indexOf(t2);
			int index2 = Teams2.contains(t1) ? Teams2.indexOf(t1) : Teams2.indexOf(t2);
			if (Teams1.contains(t1)) {
				Teams1.set(index1, t2);
				Teams2.set(index2, t1);
			}
			else {
				Teams1.set(index1, t1);
				Teams2.set(index2, t2);
			}
		}
		
		@Override
		public String toString() {
			return "Swap between " + t1 + " and " + t2;
		}
		
		@Override 
		public boolean equals(Object o) {
			Swap that;
			try {
				that = (Swap)o;
			}
			catch (Exception e) {
				return false;
			}
			if (this.t1 == that.t1 && this.t2 == that.t2) {return true;}
			if (this.t1 == that.t2 && this.t2 == that.t1) {return true;}
			return false;
		}
	}
	
	private Swap bestSwap(Map<Team, Integer> PCandidatesAndRank, Map<Team, Integer> DCanditatesAndRank, Team PTeam, Team DTeam) {
		Set<Team> PCandidates = PCandidatesAndRank.keySet();
		Set<Team> DCanditates = DCanditatesAndRank.keySet();
		List<Swap> possibleSwaps = new ArrayList<Swap>();
		for (Team pt : PCandidates) {
			possibleSwaps.add(new Swap(pt, PTeam));
		}
		for (Team dt : DCanditates) {
			possibleSwaps.add(new Swap(dt, DTeam));
		}
		
		// see if we can decide a swap based on ballots won
		double smallestBallotDifference = Double.MAX_VALUE;
		for (Swap s : possibleSwaps) {
			if (s.ballotDifference() < smallestBallotDifference) {
				smallestBallotDifference = s.ballotDifference();
			}
		}
		int numberOfBestSwaps = 0;
		Swap bestSwap = null;
		for (Swap s : possibleSwaps) {
			if (s.ballotDifference() == smallestBallotDifference) {
				numberOfBestSwaps++;
				bestSwap = s;
			}
		}
		if (numberOfBestSwaps == 1) {
			return bestSwap;
		}
		else {
			List<Swap> badSwaps = new ArrayList<Swap>();
			for (Swap s : possibleSwaps) {
				if (s.ballotDifference() != smallestBallotDifference) {
					badSwaps.add(s);
				}
			}
			for (Swap s : badSwaps) {
				possibleSwaps.remove(s);
			}
		}
		
		// Ok, we have a tie.  Let's see if we can decide a swap based on strength
		double smallestPointStrengthDifference = Double.MAX_VALUE;
		for (Swap s : possibleSwaps) {
			if (s.combinedStrengthDifference() < smallestPointStrengthDifference) {
				smallestPointStrengthDifference = s.combinedStrengthDifference();
			}
		}
		numberOfBestSwaps = 0;
		bestSwap = null;
		for (Swap s : possibleSwaps) {
			if (s.combinedStrengthDifference() == smallestPointStrengthDifference) {
				numberOfBestSwaps++;
				bestSwap = s;
			}
		}
		if (numberOfBestSwaps == 1) {
			return bestSwap;
		}
		else {
			List<Swap> badSwaps = new ArrayList<Swap>();
			for (Swap s : possibleSwaps) {
				if (s.combinedStrengthDifference() != smallestPointStrengthDifference) {
					badSwaps.add(s);
				}
			}
			for (Swap s : badSwaps) {
				possibleSwaps.remove(s);
			}
		}
		
		
		// now we try point differential
		int smallestPointDifferentialDifference = Integer.MAX_VALUE;
		for (Swap s : possibleSwaps) {
			if (s.pointDifferentialDifference() < smallestPointDifferentialDifference) {
				smallestPointDifferentialDifference = s.pointDifferentialDifference();
			}
		}
		numberOfBestSwaps = 0;
		bestSwap = null;
		for (Swap s : possibleSwaps) {
			if (s.pointDifferentialDifference() == smallestPointDifferentialDifference) {
				numberOfBestSwaps++;
				bestSwap = s;
			}
		}
		if (numberOfBestSwaps == 1) {
			return bestSwap;
		}
		else {
			List<Swap> badSwaps = new ArrayList<Swap>();
			for (Swap s : possibleSwaps) {
				if (s.pointDifferentialDifference() != smallestPointDifferentialDifference) {
					badSwaps.add(s);
				}
			}
			for (Swap s : badSwaps) {
				possibleSwaps.remove(s);
			}
		}
		
		// Not working yet.  Move on to sum of ranks
		int maxRankSum = 0;
		for (Swap s : possibleSwaps) {
			if (PCandidatesAndRank.get(s.t1) + DCanditatesAndRank.get(s.t2) > maxRankSum) {
				maxRankSum = PCandidatesAndRank.get(s.t1) + DCanditatesAndRank.get(s.t2);
			}
		}
		numberOfBestSwaps = 0;
		bestSwap = null;
		for (Swap s : possibleSwaps) {
			if (PCandidatesAndRank.get(s.t1) + DCanditatesAndRank.get(s.t2) == maxRankSum) {
				numberOfBestSwaps++;
				bestSwap = s;
			}
		}
		if (numberOfBestSwaps == 1) {
			return bestSwap;
		}
		else {
			// we know there are only two swaps, one P, and one D
			if (possibleSwaps.get(0).t1.wentP) {
				return possibleSwaps.get(0);
			}
			else {
				return possibleSwaps.get(1);
			}
		}
	}
	
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
		List<Swap> swaps = new ArrayList<Swap>();
		List<Team> haveSwapped = new ArrayList<Team>();
		int index = 0;
		while (index < Teams1.size() + Teams2.size()) {
			if (!Utils.matchIsPermissible(Teams1.get(index / 2), Teams2.get((index / 2)))) {
				// get candidate swap teams
				int lookDistance = 0;  
				Map<Team, Integer> canditatesForSwapWith1 = new HashMap<Team, Integer>();
				Map<Team, Integer> canditatesForSwapWith2 = new HashMap<Team, Integer>();

				while(canditatesForSwapWith1.size() + canditatesForSwapWith2.size() == 0) {
					lookDistance++;
					if (lookDistance > Teams1.size()/2) {
						throw new RuntimeException("Error with Round3Pairings");
					}
					if (index > lookDistance - 1) {
						if (!haveSwapped.contains(getTeamByNetRank(index + 1 + lookDistance, Teams1, Teams2))) {
							canditatesForSwapWith2.put(getTeamByNetRank(index + 1 + lookDistance, Teams1, Teams2), (index / 2));
						}
					}
					if (index < Teams1.size() + Teams2.size() - 2) {
						if (!haveSwapped.contains(getTeamByNetRank(index - lookDistance, Teams1, Teams2))) {
							canditatesForSwapWith1.put(getTeamByNetRank(index - lookDistance, Teams1, Teams2), (index/2));
						}
					}					
				}
				// find best swap
				Swap bestSwap = bestSwap(canditatesForSwapWith1, canditatesForSwapWith2, Teams1.get(index/2), Teams2.get(index/2));				
				swaps.add(bestSwap);	
				bestSwap.execute(Teams1, Teams2);
				haveSwapped.add(bestSwap.t1); haveSwapped.add(bestSwap.t2);
				index = 0;
			}
			else {
				index += 2;
			}
		}

		
		// set up matches, flip a coin 
		Random r = new Random();
		boolean P = (r.nextDouble() > 0.5) ? true : false;
		for (int rank = 0; rank < Teams1.size(); rank++) {
			Match m;
			if (P) {
				m = new Match(Teams1.get(rank), Teams2.get(rank));
			}
			else {
				m = new Match(Teams2.get(rank), Teams1.get(rank));
			}
			Teams1.get(rank).opponents.add(Teams1.get(rank));
			Teams1.get(rank).opponents.add(Teams2.get(rank));

			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().persist(m);
			db.getEntityManager().getTransaction().commit();
		}
	}

}
