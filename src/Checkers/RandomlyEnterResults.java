package Checkers;

import java.util.Random;

import DataAction.AutomaticDataAction;
import DataAction.DataActionException;

public class RandomlyEnterResults extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		db.getEntityManager().getTransaction().begin();
		for (Match match : Utils.getAllMatches(db)) {
			match.winner = match.player1;
			match.turns = (new Random()).nextInt(60);
		}
		db.getEntityManager().getTransaction().commit();
	}

}
