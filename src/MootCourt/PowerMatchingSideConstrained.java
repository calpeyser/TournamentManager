package MootCourt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerMatchingSideConstrained extends MootCourtPairingScheme {

	// Teams1 is going P, Teams2 is going R
	
	@Override
	public List<Match> createMatches() {
		List<Team> teams = Utils.getAllTeams(db);		
		Teams1.clear(); Teams2.clear();
		for (Team t : teams) {
			if (t.wentP) {
				Teams2.add(t);
			}
			else {
				Teams1.add(t);
			}
		}
		
		Collections.sort(Teams1);
		Collections.sort(Teams2);
				
		resolveMatches();
		
		List<Match> out = new ArrayList<Match>();
		for (int i = 0; i < Teams1.size(); i++) {
			Match m = new Match(Teams1.get(i), Teams2.get(i));
			out.add(m);
		}
		return out;	
	}

}
