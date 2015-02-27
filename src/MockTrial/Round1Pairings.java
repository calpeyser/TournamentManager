package MockTrial;

import java.util.*;
import MockTrial.Utils.Swap;
import DataAction.AutomaticDataAction;

public class Round1Pairings extends AutomaticDataAction {
	
	@Override 
	public void execute() {
		List<Team> allTeams = Utils.getAllTeams(db);
		
		while(true) {
			Collections.shuffle(allTeams);
			Queue<Team> teamQueue = new LinkedList<Team>();
			for (Team t : allTeams) {teamQueue.add(t);}

			List<Match> matches = new ArrayList<Match>();
			boolean success = true;
			for (int i = 0; i < allTeams.size()/2; i++) {
				Team PTeam = teamQueue.poll();  Team DTeam = teamQueue.poll();
				if (Utils.matchIsPermissible(PTeam, DTeam)){
					matches.add(new Match(PTeam, DTeam));
				}
				else {
					success = false;
					break;
				}
			}
			if (!success) {continue;}
			else {
				db.getEntityManager().getTransaction().begin();
				for (Match m : matches) {
					m.PTeam.wentP = true;
					m.PTeam.opponents.add(m.DTeam);
					m.DTeam.opponents.add(m.PTeam);
					db.getEntityManager().persist(m);
				}
				db.getEntityManager().getTransaction().commit();
				break;
			}
		}
	}
}
