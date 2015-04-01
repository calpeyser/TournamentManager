package Checkers;

import java.util.Collections;
import java.util.List;
import Visibles.TableVisible;

public class PlayerRankings extends TableVisible {

	@Override
	public Object[] getColumnNames() {
		String[] names = {"Name", "Games Won", "Total Turns Played"};
		return names;
	}

	@Override
	public Object[][] getData() {
		List<Player> allPlayers = Utils.getAllPlayers(db);
		Collections.sort(allPlayers);
		
		Object[][] data = new Object[allPlayers.size()][3];
		for (int playerIndex = 0; playerIndex < allPlayers.size(); playerIndex++) {
			Player p = allPlayers.get(playerIndex);
			data[playerIndex][0] = p.name;
			data[playerIndex][1] = p.gamesWon;
			data[playerIndex][2] = p.totalTurns;
		}
		return data;
	}

	@Override
	public String getName() {
		return "Player Rankings";
	}

}
