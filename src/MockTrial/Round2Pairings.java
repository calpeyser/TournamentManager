package MockTrial;

import DataAction.AutomaticDataAction;

import java.util.*;

/**
 * Configures the database with matches from round 2.
 * @author Cal Peyser
 *
 */
public class Round2Pairings extends AutomaticDataAction {

	private class Swap {
		private Team t1; private Team t2;
		public Swap(Team t1, Team t2) {
			this.t1 = t1; this.t2 = t2;
		}
		
		public double ballotDifference() {
			return Math.abs(t1.score - t2.score);
		}
		
		public int pointDifferentialDifference() {
			return Math.abs(t1.PointDifferential - t2.PointDifferential);
		}
		
		public void execute(List<Team> PTeams, List<Team> DTeams) {
			boolean isP = !t1.wentP;
			int index1 = 0;
			int index2 = 0;
			if (isP) {
				index1 = PTeams.indexOf(t1);
				index2 = PTeams.indexOf(t2);
				PTeams.set(index1, t2);
				PTeams.set(index2, t1);
			}
			if (!isP) {
				index1 = DTeams.indexOf(t1);
				index2 = DTeams.indexOf(t2);
				DTeams.set(index1, t2);
				DTeams.set(index2, t1);
			}
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
		
		// Ok, we have a tie.  Let's see if we can decide a swap based on point differential
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
		
		// Neither ballots or point differential worked.  Move on to sum of ranks
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
					if (index >= lookDistance - 1) {
						if (!haveSwapped.contains(PTeams.get(index - lookDistance))) {canditatesForSwapWithP.put(PTeams.get(index - lookDistance), index - lookDistance);}
						if (!haveSwapped.contains(DTeams.get(index - lookDistance))) {canditatesForSwapWithD.put(DTeams.get(index - lookDistance), index - lookDistance);}
					}
					if (index <= PTeams.size() - lookDistance) {
						if (!haveSwapped.contains(PTeams.get(index + lookDistance))) {canditatesForSwapWithP.put(PTeams.get(index + lookDistance), index - lookDistance);}
						if (!haveSwapped.contains(DTeams.get(index + lookDistance))) {canditatesForSwapWithD.put(DTeams.get(index + lookDistance), index - lookDistance);}
					}
				}
				// find best swap
				Swap bestSwap = bestSwap(canditatesForSwapWithP, canditatesForSwapWithD, PTeams.get(index), DTeams.get(index));
				swaps.add(bestSwap);
				bestSwap.execute(PTeams, DTeams);
				index = 0;
			}
			else {
				index ++;
			}
		}

		
		// set up matches
		for (int rank = 0; rank < PTeams.size(); rank++) {
			Match m = new Match(PTeams.get(rank), DTeams.get(rank));
			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().persist(m);
			db.getEntityManager().getTransaction().commit();
		}
	}
}
