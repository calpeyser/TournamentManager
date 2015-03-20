package MootCourt;

import java.util.*;

import Visibles.TableVisible;

public class PlayerRankings extends TableVisible {

	@Override
	public String getName() {
		return "Current Player Rankings";
	}

	@Override
	public Object[] getColumnNames() {
		String[] out = {"Name", "Ranks", "Team Designation", "School"};
		return out;
	}

	@Override
	public Object[][] getData() {
		// get players
		List<Player> allPlayers = Utils.getAllPlayers(db);
		
		Collections.sort(allPlayers);
		Object[][] out = new Object[allPlayers.size()][4];
		for (int playerIndex = 0; playerIndex < allPlayers.size(); playerIndex++) {
			Player p = allPlayers.get(playerIndex);
			Team t = getTeam(p);
			out[playerIndex][0] = p.name;
			out[playerIndex][1] = p.ranks;
			out[playerIndex][2] = t.designation;
			out[playerIndex][3] = t.schoolName;
		}
		return out;
	}
	
	private Team getTeam(Player player) {
		List<Team> allTeams = Utils.getAllTeams(db);
		for (Team t : allTeams) {
			if (player.equals(t.player1) || player.equals(t.player2)) {
				return t;
			}
		}
		throw new RuntimeException("Player " + player + " not found on any team");
	}
}
