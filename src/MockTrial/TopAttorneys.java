package MockTrial;

import Visibles.TableVisible;
import java.util.*;

public class TopAttorneys extends TableVisible {

	private class AttorneyComparator implements Comparator<Player> {
		@Override
		public int compare(Player p1, Player p2) {
			int p1Ranks = (p1.PAttRanks >= p1.DAttRanks) ? p1.PAttRanks : p1.DAttRanks;
			int p2Ranks = (p2.PAttRanks >= p2.DAttRanks) ? p2.PAttRanks : p2.DAttRanks;
			if (p1Ranks > p2Ranks) {return -1;}
			if (p2Ranks > p1Ranks) {return 1;}
			return 0;
		}	
	}
	
	@Override
	public Object[] getColumnNames() {
		String[] out = {"Name", "School", "Team", "Ranks"};
		return out;
	}

	@Override
	public Object[][] getData() {
		List<Player> allPlayers = Utils.getAllPlayers(db);
		Collections.sort(allPlayers, new AttorneyComparator());
		Object[][] out = new Object[allPlayers.size()][4];
		for (int index = 0; index < allPlayers.size(); index++) {
			Player p = allPlayers.get(index);
			Team t = findTeam(p);
			out[index][0] = p.name;
			out[index][1] = t.schoolName;
			out[index][2] = t.designation;
			out[index][3] = (p.PAttRanks >= p.DAttRanks) ? p.PAttRanks : p.DAttRanks;
		}
		return out;
	}

	@Override
	public String getName() {
		return "Top Attorneys";
	}

	private Team findTeam(Player p) {
		List<Team> allTeams = Utils.getAllTeams(db);
		for (Team t : allTeams) {
			if (t.players.contains(p)) {
				return t;
			}
		}
		throw new RuntimeException("Player " + p + " not found in any team");
	}
	
}
