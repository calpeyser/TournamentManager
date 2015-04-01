package MootCourt;

import DataAction.AutomaticDataAction;
import DataAction.DataActionException;
import java.util.*;

public class UpdateBracket extends AutomaticDataAction {

	private void updateBracketStage(Bracket b) {
		db.getEntityManager().getTransaction().begin();
		if (b.stage == BracketStage.ROUND_OF_32) {
			b.stage = BracketStage.ROUND_OF_16;
		}
		else if (b.stage == BracketStage.ROUND_OF_16) {
			b.stage = BracketStage.QUARTERFINALS;
		}
		else if (b.stage == BracketStage.QUARTERFINALS) {
			b.stage = BracketStage.SEMIFINALS;
		}
		else if (b.stage == BracketStage.SEMIFINALS) {
			b.stage = BracketStage.FINALS;
		}
		db.getEntityManager().getTransaction().commit();
	}
	
	private void contract(Bracket b) {
		List<Match> newMatches = new ArrayList<Match>();
		for (int i = 0; i < b.matches.size(); i += 2) {
			Team team1 = b.matches.get(i).nonEliminatedTeam();
			Team team2 = b.matches.get(i + 1).nonEliminatedTeam();
			Match newMatch;
			if (team1.hitTeamsAsP.contains(team2)) {
				newMatch = new Match(team2, team1);
			}
			else {
				newMatch = new Match(team1, team2);
			}
			newMatches.add(newMatch);
		}
		db.getEntityManager().getTransaction().begin();
		for (Match m : newMatches) {
			db.getEntityManager().persist(m);
		}
		for (Match m : b.matches) {
			db.getEntityManager().remove(m);
		}
		b.matches = newMatches;
		db.getEntityManager().getTransaction().commit();
	}
	
	@Override
	public void execute() throws DataActionException {
		Bracket b = Utils.getBracket(db);
		updateBracketStage(b);
		contract(b);
	}

}
