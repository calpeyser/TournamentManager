package MootCourt;

import DataAction.*;
import java.util.*;

public abstract class MootCourtPairingScheme extends AutomaticDataAction {

	protected List<Team> PTeams = new ArrayList<Team>();
	protected List<Team> RTeams = new ArrayList<Team>();
	
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
		for (int i = 0; i < PTeams.size(); i++) {
			if (!isPermissableMatch(PTeams.get(i), RTeams.get(i))) {
				return false;
			}
		}
		return true;
	}

	protected void resolveMatches() {
		Utils.customAssert(PTeams.size() == RTeams.size(), "Unequal size for PTeams and DTeams");
		int i = 0;
		int loopcount = 0;
		while(true) {
			loopcount++;
			if (i == PTeams.size() || loopcount > 100) {
				break;
			}
			if (isPermissableMatch(PTeams.get(i), RTeams.get(i))) {
				i++; continue;
			}
			else {
				if (i == 0) {
					Team temp = PTeams.get(i);
					PTeams.set(i, PTeams.get(i+1));
					PTeams.set(i + 1, temp);
					i = 0; continue;
				}
				else {
					Team temp = RTeams.get(i);
					RTeams.set(i, RTeams.get(i-1));
					RTeams.set(i - 1, temp);
					i = 0; continue;
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
