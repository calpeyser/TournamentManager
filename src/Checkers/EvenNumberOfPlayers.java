package Checkers;

import Check.Check;
import java.util.*;
import Check.CheckFailedException;

public class EvenNumberOfPlayers extends Check {

	@Override
	public void performCheck() throws CheckFailedException {
		List<Player> allPlayers = Utils.getAllPlayers(db);
		if (allPlayers.size() % 2 == 1) {
			throw new CheckFailedException("Must have an even number of playes. Currently have " + allPlayers.size());
		}
	}
}
