package MootCourt;

import DataAction.*;
import java.util.*;

public abstract class MootCourtPairingScheme extends AutomaticDataAction {

	protected List<Team> Teams1 = new ArrayList<Team>();
	protected List<Team> Teams2 = new ArrayList<Team>();
	
	protected boolean isPermissableMatch(Team PTeam, Team RTeam) {
		if (PTeam.hitTeams.contains(RTeam)) {
			return false;
		}
		if (PTeam.schoolName.equals(RTeam.schoolName)) {
			return false;
		}
		return true;
	}
	
	protected boolean allMatchesPermissable() {
		for (int i = 0; i < Teams1.size(); i++) {
			if (!isPermissableMatch(Teams1.get(i), Teams2.get(i))) {
				return false;
			}
		}
		return true;
	}

	private List<Team> copy(List<Team> teams) {
		List<Team> out = new ArrayList<Team>();
		for (Team t: teams) {
			out.add(t);
		}
		return out;
	}
	
	protected void resolveMatches() {
		Utils.customAssert(Teams1.size() == Teams2.size(), "Unequal size for PTeams and DTeams");
		List<Team> backup1 = copy(Teams1); List<Team> backup2 = copy(Teams2);
		int i = 0;
		int loopcount = 0;
		while(true) {
			loopcount++;
			if (i == Teams1.size()) {
				break;
			}
			if (loopcount > 100) {
				// give up
				Teams1 = backup1;
				Teams2 = backup2;
				break;
			}
			if (isPermissableMatch(Teams1.get(i), Teams2.get(i))) {
				i++; continue;
			}
			else {
				// we choose a random swap based on the loopcount and try it
				// flip Teams1 forward
				if (loopcount % 4 == 0) {
					if (i == Teams1.size() - 1) {continue;}
					else {
						Team temp = Teams1.get(i);
						Teams1.set(i, Teams1.get(i + 1));
						Teams1.set(i + 1, temp);
						i = 0; continue;
					}
				}
				// flip Teams2 forward
				else if (loopcount % 4 == 1) {
					if (i == Teams1.size() - 1) {continue;}
					else {
						Team temp = Teams2.get(i);
						Teams2.set(i, Teams2.get(i + 1));
						Teams2.set(i + 1, temp);
						i = 0; continue;
					}
				}
				// flip Teams1 backward
				else if (loopcount % 4 == 2) {
					if (i == 0) {continue;}
					else {
						Team temp = Teams1.get(i);
						Teams1.set(i, Teams1.get(i - 1));
						Teams1.set(i - 1, temp);
						i = 0; continue;
					}
				}
				// flip Teams2 backward
				else if (loopcount % 4 == 3) {
					if (i == 0) {continue;}
					else {
						Team temp = Teams2.get(i);
						Teams2.set(i, Teams2.get(i - 1));
						Teams2.set(i - 1, temp);
						i = 0; continue;
					}
				}				
			}
		}
	} 
	
	@Override
	public void execute() throws DataActionException {
		db.getEntityManager().getTransaction().begin();
		for (Match m : createMatches()) {
			db.getEntityManager().persist(m);
		}
		db.getEntityManager().getTransaction().commit();
	}

	public abstract List<Match> createMatches();
	
}
