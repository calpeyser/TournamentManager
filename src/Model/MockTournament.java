package Model;

import Base.*;
import java.util.*;

public class MockTournament extends Tournament {
	@ConfigField(name = "Players", type = ConfigType.MANUAL)
	public List<MockPlayer> players;
	@ConfigField(name = "Current Matches", type = ConfigType.AUTOMATIC)
	public List<MockMatch> currentMatches;
	
	@Override
	public String toString() {
		return ("MockTournament with name: " + this.name + " players: " 
				+ this.players.size() + " current matches: " + this.currentMatches.size());
	}
}
