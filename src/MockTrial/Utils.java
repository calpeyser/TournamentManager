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
	
	public static class Swap {
		public Team t1; public Team t2;
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
		
/*		public void execute(List<Team> Teams1, List<Team> Teams2) {
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
*/		
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

	public static Swap bestSwap(Map<Team, Integer> PCandidatesAndRank, Map<Team, Integer> DCanditatesAndRank, Team PTeam, Team DTeam) {
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
			int rank1 = PCandidatesAndRank.containsKey(s.t1) ? PCandidatesAndRank.get(s.t1) : DCanditatesAndRank.get(s.t1);
			int rank2 = PCandidatesAndRank.containsKey(s.t2) ? PCandidatesAndRank.get(s.t2) : DCanditatesAndRank.get(s.t2);
			
			if (rank1 + rank2 > maxRankSum) {
				maxRankSum = rank1 + rank2;
			}
		}
		numberOfBestSwaps = 0;
		bestSwap = null;
		for (Swap s : possibleSwaps) {
			int rank1 = PCandidatesAndRank.containsKey(s.t1) ? PCandidatesAndRank.get(s.t1) : DCanditatesAndRank.get(s.t1);
			int rank2 = PCandidatesAndRank.containsKey(s.t2) ? PCandidatesAndRank.get(s.t2) : DCanditatesAndRank.get(s.t2);

			if (rank1 + rank2 == maxRankSum) {
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

/*	
	public static Swap bestSwap(Map<Team, Integer> PCandidatesAndRank, Map<Team, Integer> DCanditatesAndRank, Team PTeam, Team DTeam) {
		Set<Team> PCandidates = PCandidatesAndRank.keySet();
		Set<Team> DCanditates = DCanditatesAndRank.keySet();
		List<Swap> possibleSwaps = new ArrayList<Swap>();
		for (Team pt : PCandidates) {
			possibleSwaps.add(new Swap(pt, PTeam));
		}
		for (Team dt : DCanditates) {
			possibleSwaps.add(new Swap(dt, DTeam));
		}
		// return an arbitrary swap
        int randomIndex = (new Random()).nextInt(possibleSwaps.size());
        return possibleSwaps.get(randomIndex);
	}
*/
	
	
}
