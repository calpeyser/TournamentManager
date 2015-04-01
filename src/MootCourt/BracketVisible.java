package MootCourt;

import Visibles.TreeVisible;

public class BracketVisible extends TreeVisible {

	private Bracket b;
	
	private void setMatchToNode(String node, Match match) {
		setChild(match.PTeam.toString(), node);
		setChild(match.RTeam.toString(), node);
	}
	
	private void setSemifinals() {
		setChild("Semifinal 1", "Final Round");
		setChild("Semifinal 2", "Final Round");
	}
	
	private void setQuarterfinals() {
		setSemifinals();
		setChild("Quarterfinal 1", "Semifinal 1");
		setChild("Quarterfinal 2", "Semifinal 1");
		setChild("Quarterfinal 3", "Semifinal 2");
		setChild("Quarterfinal 4", "Semifinal 2");
	}
	
	private void setRoundOf16() {
		setQuarterfinals();
		setChild("RoundOf16 1", "Quarterfinal 1");
		setChild("RoundOf16 2", "Quarterfinal 1");
		setChild("RoundOf16 3", "Quarterfinal 2");
		setChild("RoundOf16 4", "Quarterfinal 2");
		setChild("RoundOf16 5", "Quarterfinal 3");
		setChild("RoundOf16 6", "Quarterfinal 3");
		setChild("RoundOf16 7", "Quarterfinal 4");
		setChild("RoundOf16 8", "Quarterfinal 4");
	}
	
	private void setRoundOf32() {
		setRoundOf16();
		setChild("RoundOf32 1", "RoundOf16 1");
		setChild("RoundOf32 2", "RoundOf16 1");
		setChild("RoundOf32 3", "RoundOf16 2");
		setChild("RoundOf32 4", "RoundOf16 2");
		setChild("RoundOf32 5", "RoundOf16 3");
		setChild("RoundOf32 6", "RoundOf16 3");
		setChild("RoundOf32 7", "RoundOf16 4");
		setChild("RoundOf32 8", "RoundOf16 4");
		setChild("RoundOf32 9", "RoundOf16 5");
		setChild("RoundOf32 10", "RoundOf16 5");
		setChild("RoundOf32 11", "RoundOf16 6");
		setChild("RoundOf32 12", "RoundOf16 6");
		setChild("RoundOf32 13", "RoundOf16 7");
		setChild("RoundOf32 14", "RoundOf16 7");
		setChild("RoundOf32 15", "RoundOf16 8");
		setChild("RoundOf32 16", "RoundOf16 8");
	}
	
	@Override
	protected String rootName() {
		return "Final Round";
	}

	@Override
	protected void configure() {
		b = Utils.getBracket(db);
		if (b == null) {return;} // we're not up to elimination yet
		else if (b.stage == BracketStage.FINALS) {
			setMatchToNode("Final Round", b.matches.get(0));
		}
		else if (b.stage == BracketStage.SEMIFINALS) {
			setSemifinals();
			setMatchToNode("Semifinal 1", b.matches.get(0));
			setMatchToNode("Semifinal 2", b.matches.get(1));
		}
		else if (b.stage == BracketStage.QUARTERFINALS) {
			setQuarterfinals();
			setMatchToNode("Quarterfinal 1", b.matches.get(0));
			setMatchToNode("Quarterfinal 2", b.matches.get(1));
			setMatchToNode("Quarterfinal 3", b.matches.get(2));
			setMatchToNode("Quarterfinal 4", b.matches.get(3));
		}
		else if (b.stage == BracketStage.ROUND_OF_16) {
			setRoundOf16();
			setMatchToNode("RoundOf16 1", b.matches.get(0));
			setMatchToNode("RoundOf16 2", b.matches.get(1));
			setMatchToNode("RoundOf16 3", b.matches.get(2));
			setMatchToNode("RoundOf16 4", b.matches.get(3));
			setMatchToNode("RoundOf16 5", b.matches.get(4));
			setMatchToNode("RoundOf16 6", b.matches.get(5));
			setMatchToNode("RoundOf16 7", b.matches.get(6));
			setMatchToNode("RoundOf16 8", b.matches.get(7));
		}
		else if (b.stage == BracketStage.ROUND_OF_32) {
			setRoundOf32();
			setMatchToNode("RoundOf32 1", b.matches.get(0));
			setMatchToNode("RoundOf32 2", b.matches.get(1));
			setMatchToNode("RoundOf32 3", b.matches.get(2));
			setMatchToNode("RoundOf32 4", b.matches.get(3));
			setMatchToNode("RoundOf32 5", b.matches.get(4));
			setMatchToNode("RoundOf32 6", b.matches.get(5));
			setMatchToNode("RoundOf32 7", b.matches.get(6));
			setMatchToNode("RoundOf32 8", b.matches.get(7));
			setMatchToNode("RoundOf32 9", b.matches.get(8));
			setMatchToNode("RoundOf32 10", b.matches.get(9));
			setMatchToNode("RoundOf32 11", b.matches.get(10));
			setMatchToNode("RoundOf32 12", b.matches.get(11));
			setMatchToNode("RoundOf32 13", b.matches.get(12));
			setMatchToNode("RoundOf32 14", b.matches.get(13));
			setMatchToNode("RoundOf32 15", b.matches.get(14));
			setMatchToNode("RoundOf32 16", b.matches.get(15));
		}
	}

	@Override
	public String getName() {
		return "Bracket";
	}

}
