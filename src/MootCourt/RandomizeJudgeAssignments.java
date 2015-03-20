package MootCourt;

import java.awt.Window;
import java.util.*;

import javax.swing.JOptionPane;

import DataAction.UIDataAction;

public class RandomizeJudgeAssignments extends UIDataAction {

	@Override
	public void attachToFrame(Window frame) {
		List<Judge> availableJudges = Utils.getAllJudges(db);
		List<Match> allMatches = Utils.getAllMatches(db);
		for (Match m : allMatches) {
			for (Judge j : m.ballots) {
				availableJudges.remove(j);
			}
		}
		if (availableJudges.size() < allMatches.size()) {
			JOptionPane.showMessageDialog(frame, "There are not enough judges to add one to each match");
			return;
		}
		for (Match m : allMatches) { 
			Judge randomJudge = availableJudges.get(new Random().nextInt(availableJudges.size()));
			availableJudges.remove(randomJudge);
			db.getEntityManager().getTransaction().begin();
			m.ballots.add(randomJudge);
			db.getEntityManager().getTransaction().commit();
		}
	}
	
	@Override
	public String description() {
		return "Randomly Assign a Judge to Each Round";
	}

}
