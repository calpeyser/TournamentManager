package Model;

import Data.TournamentDataStore;
import DataAction.AutomaticDataAction;

public class MakePlayers extends AutomaticDataAction {

	@Override
	public void execute() {
		assertBound();
		MockPlayer[] players = new MockPlayer[3];
		for (int i = 0; i < 3; i++) {
			players[i] = new MockPlayer();
		}
		players[0].name = "Cal";
		players[1].name = "Gabe";
		players[2].name = "Rebecca";
	
			
		for (MockPlayer p : players) {
			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().persist(p);
			db.getEntityManager().getTransaction().commit();
		}
	}

}
