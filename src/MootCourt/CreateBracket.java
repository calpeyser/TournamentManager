package MootCourt;

import java.util.*;

public class CreateBracket extends MootCourtPairingScheme {

	@Override
	public List<Match> createMatches() {
		// prune down to 32 teams
		List<Team> allTeams = Utils.getAllTeams(db);
		List<Team> qualifiedTeams = new ArrayList<Team>();
		Collections.sort(allTeams);
		for (int i = 0; i < 32; i++) {
			qualifiedTeams.add(allTeams.get(i));
		}
		
		// split into two halfs, for high-low matching
		for (int i = 0; i < 16; i++) {
			Teams1.add(qualifiedTeams.get(i));
		}
		for (int i = 16; i < 32; i++) {
			Teams2.add(0, qualifiedTeams.get(i));
		}
		resolveMatches();
		
		// the matches
		List<Match> matches = new ArrayList<Match>();
		for (int i = 0; i < 16; i++) {
			if (Teams1.get(i).hitTeamsAsP.contains(Teams2.get(i))) {
				matches.add(new Match(Teams2.get(i), Teams1.get(i)));
			}
			else {
				matches.add(new Match(Teams1.get(i), Teams2.get(i)));
			}
		}
		
		// we now reorder the brackets to reward the best teams
		List<Match> out = new ArrayList<Match>();
		out.add(matches.get(0));
		out.add(matches.get(15));
		out.add(matches.get(7));
		out.add(matches.get(8));
		out.add(matches.get(5));
		out.add(matches.get(10));
		out.add(matches.get(3));
		out.add(matches.get(13));
		out.add(matches.get(2));
		out.add(matches.get(12));
		out.add(matches.get(4));
		out.add(matches.get(11));
		out.add(matches.get(6));
		out.add(matches.get(9));
		out.add(matches.get(1));
		out.add(matches.get(14));
		
		// create bracket, then return the matches
		Bracket b = new Bracket(out);
		db.getEntityManager().persist(b);
		
		return out;
	}

}
