package MockTrial;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Visibles.ListVisible;
import Visibles.TableVisible;

public class TeamRankings extends TableVisible {

	@Override
	public String getName() {
		return "Current Team Rankings";
	}

	@Override
	public Object[] getColumnNames() {
		String[] out = {"School Name", "Designation", "Score", "CS", "Point Differential", "Went P"};
		return out;
	}

	@Override
	public Object[][] getData() {
		// get teams
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> r = cq.from(Team.class);
		cq.select(r);
		TypedQuery<Team> q = db.getEntityManager().createQuery(cq);
		List<Team> allTeams = q.getResultList();

		Collections.sort(allTeams);
		Object[][] out = new Object[allTeams.size()][6];
		for (int teamIndex = 0; teamIndex < allTeams.size(); teamIndex++) {
			Team t = allTeams.get(teamIndex);
			out[teamIndex][0] = t.schoolName;
			out[teamIndex][1] = t.designation;
			out[teamIndex][2] = t.score;
			out[teamIndex][3] = t.CombinedStrength;
			out[teamIndex][4] = t.PointDifferential;
			out[teamIndex][5] = t.wentP;
		}
		return out;
	}

}
