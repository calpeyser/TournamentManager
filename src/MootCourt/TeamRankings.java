package MootCourt;

import java.util.Collections;
import java.util.List;

import Visibles.TableVisible;

public class TeamRankings extends TableVisible {

	@Override
	public String getName() {
		return "Current Team Rankings";
	}

	@Override
	public Object[] getColumnNames() {
		String[] out = {"School Name", "Designation", "Score", "Point Differential", "Hit Teams", "Player 1", "Player 2", "Last Round Side"};
		return out;
	}

	@Override
	public Object[][] getData() {
		// get teams
		List<Team> allTeams = Utils.getAllTeams(db);

		Collections.sort(allTeams);
		Object[][] out = new Object[allTeams.size()][8];
		for (int teamIndex = 0; teamIndex < allTeams.size(); teamIndex++) {
			Team t = allTeams.get(teamIndex);
			out[teamIndex][0] = t.schoolName;
			out[teamIndex][1] = t.designation;
			out[teamIndex][2] = t.ballots;
			out[teamIndex][3] = t.pointDifferential;
			out[teamIndex][4] = t.hitTeams;
			out[teamIndex][5] = t.player1;
			out[teamIndex][6] = t.player2;
			out[teamIndex][7] = t.wentP ? "Petitioner" : "Respondent";
		}
		return out;
	}

}
