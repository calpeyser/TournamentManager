package Model;
import Data.TournamentDataStore;
import java.util.*;

public class MockPairingScheme {

	private TournamentDataStore db;
	
	public MockPairingScheme(TournamentDataStore db) {
		this.db = db;
	}
	
	public void configure() {
		List<MockPlayer> players = db.getAll(MockPlayer.class);
		for (int i = 0; i < players.size(); i += 2) {
			
			MockMatch thisMatch = new MockMatch(
					"Match " + (i/2),
					 players.get(i), 
					 players.get(i+1));
			db.add(thisMatch);
		}
	}
}
