package Checkers;

import Check.Check;
import Check.CheckFailedException;
import java.util.*;

public class MustHavePlayers extends Check {

	@Override
	public void performCheck() throws CheckFailedException {
		if (Utils.getAllPlayers(db).size() == 0) {
			throw new CheckFailedException("No players have been added to the tournament.");
		}
	}
}
