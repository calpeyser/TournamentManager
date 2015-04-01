package Checkers;

import DataAction.AutomaticDataAction;
import DataAction.DataActionException;

public class ClearMatches extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		db.getEntityManager().getTransaction().begin();
		for (Match match : Utils.getAllMatches(db)) {
			db.getEntityManager().remove(match);
		}
		db.getEntityManager().getTransaction().commit();
	}
}
