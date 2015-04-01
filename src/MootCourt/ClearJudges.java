package MootCourt;

import DataAction.AutomaticDataAction;
import DataAction.DataActionException;
import java.util.*;

public class ClearJudges extends AutomaticDataAction {

	@Override
	public void execute() throws DataActionException {
		List<Judge> allJudges = Utils.getAllJudges(db);
		db.getEntityManager().getTransaction().begin();
		for (Judge j : allJudges) {
			j.clear();
		}
		db.getEntityManager().getTransaction().commit();
	}

}
