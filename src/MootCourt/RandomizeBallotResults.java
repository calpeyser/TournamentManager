package MootCourt;

import java.awt.Window;
import java.util.*;

import DataAction.UIDataAction;

public class RandomizeBallotResults extends UIDataAction {

	private void randomizeResults(Judge b) {
		Random gen = new Random();

		db.getEntityManager().getTransaction().begin();
		while (b.P1Presentation + b.P1Argument + b.P2Presentation + b.P2Argument + b.PTeamwork == 
				b.R1Presentation + b.R1Argument + b.R2Presentation + b.R2Argument + b.RTeamwork) {
			b.P1Presentation = gen.nextInt(10);
			b.P1Argument = gen.nextInt(10);
			b.P2Presentation = gen.nextInt(10);
			b.P2Argument = gen.nextInt(10);
			b.PTeamwork = gen.nextInt(10);
			b.R1Presentation = gen.nextInt(10);
			b.R1Argument = gen.nextInt(10);
			b.R2Presentation = gen.nextInt(10);
			b.R2Argument = gen.nextInt(10);
			b.RTeamwork = gen.nextInt(10);
			b.rank4 = b.currentMatch.PTeam.player1;
			b.rank3 = b.currentMatch.PTeam.player2;
			b.rank2 = b.currentMatch.RTeam.player1;
			b.rank1 = b.currentMatch.RTeam.player2;
		}
		db.getEntityManager().getTransaction().commit();
	}
	
	@Override
	public void attachToFrame(Window frame) {
		List<Judge> ballots = new ArrayList<Judge>();
		List<Match> allMatches = Utils.getAllMatches(db);
		for (Match m : allMatches) {
			for (Judge b : m.ballots) {
				ballots.add(b);
			}
		}
		
		for (Judge b : ballots) {
			randomizeResults(b);
		}
	}
	
	@Override
	public String description() {
		return "Randomize Ballot Results";
	}

}
