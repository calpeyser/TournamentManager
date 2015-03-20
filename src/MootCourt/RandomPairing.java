package MootCourt;

import java.util.*;

public class RandomPairing extends MootCourtPairingScheme {

	@Override
	public List<Match> createMatches() {
		List<Team> teams = Utils.getAllTeams(db);
		
		while(true) {
			Collections.shuffle(teams, new Random()); PTeams.clear(); RTeams.clear();
			for (int i = 0; i < teams.size(); i += 2) {
				PTeams.add(teams.get(i));
				RTeams.add(teams.get(i+1));
			}
			if (allMatchesPermissable()) {break;}
		}
		
		List<Match> out = new ArrayList<Match>();
		for (int i = 0; i < PTeams.size(); i++) {
			out.add(new Match(PTeams.get(i), RTeams.get(i)));
		}
		return out;
	}
}
