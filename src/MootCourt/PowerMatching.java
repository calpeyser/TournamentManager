package MootCourt;

import java.util.*;

public class PowerMatching extends MootCourtPairingScheme {

	@Override
	public List<Match> createMatches() {
		List<Team> teams = Utils.getAllTeams(db);
		Collections.sort(teams);
		
		for (int i = 0; i < teams.size(); i += 2) {
			Teams1.add(teams.get(i));
			Teams2.add(teams.get(i+1));
		}
		
		resolveMatches();
		
		List<Match> out = new ArrayList<Match>();
		for (int i = 0; i < Teams1.size(); i++) {
			out.add(new Match(Teams1.get(i), Teams2.get(i)));
		}
		return out;
	}

}
