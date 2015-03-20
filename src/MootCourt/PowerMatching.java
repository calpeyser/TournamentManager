package MootCourt;

import java.util.*;

public class PowerMatching extends MootCourtPairingScheme {

	@Override
	public List<Match> createMatches() {
		List<Team> teams = Utils.getAllTeams(db);
		Collections.sort(teams);
		
		for (int i = 0; i < teams.size(); i += 2) {
			PTeams.add(teams.get(i));
			RTeams.add(teams.get(i+1));
		}
		
		resolveMatches();
		
		List<Match> out = new ArrayList<Match>();
		for (int i = 0; i < PTeams.size(); i++) {
			out.add(new Match(PTeams.get(i), RTeams.get(i)));
		}
		return out;
	}

}
