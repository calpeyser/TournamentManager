package Checkers;

import DataAction.AutomaticDataAction;
import DataAction.DataActionException;
import java.util.*;

public class RandomPairing extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		List<Player> allPlayers = Utils.getAllPlayers(db);
		Collections.shuffle(allPlayers);
		
		db.getEntityManager().getTransaction().begin();
		for (int i = 0; i < allPlayers.size(); i += 2) {
			Match match = new Match(allPlayers.get(i), allPlayers.get(i + 1));
			db.getEntityManager().persist(match);
		}
		db.getEntityManager().getTransaction().commit();
	}
}
