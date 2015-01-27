package Model;

import Data.TournamentDataStore;
import DataAction.AutomaticDataAction;

public class MakePlayers extends AutomaticDataAction {

	@Override
	public void execute() {
		assertBound();
		MockPlayer[] players = {
				new MockPlayer("Cal"),
				new MockPlayer("Gabe"),
				new MockPlayer("Rebecca"),
				new MockPlayer("Mom"),
				new MockPlayer("Dad"),
		};
	
		for (MockPlayer p : players) {
			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().persist(p);
			db.getEntityManager().getTransaction().commit();
		}
	}

}
