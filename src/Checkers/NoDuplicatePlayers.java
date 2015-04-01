package Checkers;

import Check.Check;
import Check.CheckFailedException;
import java.util.*;

public class NoDuplicatePlayers extends Check {

	private Set<String> playerNames = new HashSet<String>();
	
	@Override
	public void performCheck() throws CheckFailedException {
		for (Player player : Utils.getAllPlayers(db)) {
			if (playerNames.contains(player.name)) {
				throw new CheckFailedException("Player name " + player.name + " appears more than once.");
			}
			else {
				playerNames.add(player.name);
			}
		}
	}
}
