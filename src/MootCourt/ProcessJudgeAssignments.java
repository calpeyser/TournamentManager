package MootCourt;

import DataAction.AutomaticDataAction;
import DataAction.DataActionException;

public class ProcessJudgeAssignments extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		db.getEntityManager().getTransaction().begin();
		for (Match m : Utils.getAllMatches(db)) {
			for (Judge b : m.ballots) {
				b.currentMatch = m;
			}
		}
		db.getEntityManager().getTransaction().commit();
	}
}
