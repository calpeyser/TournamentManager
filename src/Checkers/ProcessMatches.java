package Checkers;

import DataAction.AutomaticDataAction;
import DataAction.DataActionException;

public class ProcessMatches extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		db.getEntityManager().getTransaction().begin();
		for (Match match : Utils.getAllMatches(db)) {
			match.winner.gamesWon++;
			match.player1.totalTurns += match.turns;
			match.player2.totalTurns += match.turns;
		}
		db.getEntityManager().getTransaction().commit();
	}
}
