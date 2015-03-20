package MootCourt;

import DataAction.AutomaticDataAction;
import DataAction.DataActionException;

public class ClearMatches extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		db.getEntityManager().getTransaction().begin();
		for (Match m : Utils.getAllMatches(db)) {
			db.getEntityManager().remove(m);
		}
		db.getEntityManager().getTransaction().commit();
	}

}
