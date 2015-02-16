package MockTrial;

import javax.persistence.*;

import Base.*;
import java.util.*;

@Entity
public class Match extends Record {

	@OneToOne (targetEntity = Team.class) @MustBeSet public Team PTeam;
	@OneToOne (targetEntity = Team.class) @MustBeSet public Team DTeam;
	
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1POpen;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PAttDirect1;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PAttDirect2;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PAttDirect3;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PAttCross1;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PAttCross2;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PAttCross3;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PWitDirect1;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PWitDirect2;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PWitDirect3;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PWitCross1;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PWitCross2;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PWitCross3;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1PClose;

	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DOpen;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DAttDirect1;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DAttDirect2;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DAttDirect3;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DAttCross1;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DAttCross2;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DAttCross3;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DWitDirect1;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DWitDirect2;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DWitDirect3;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DWitCross1;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DWitCross2;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DWitCross3;
	@Basic @FormFormat(tab = "Ballot 1") public int Ballot1DClose;

	@OneToOne @FormFormat(tab = "Ballot 1") public Player Ballot1Att1;
	@OneToOne @FormFormat(tab = "Ballot 1") public Player Ballot1Att2;
	@OneToOne @FormFormat(tab = "Ballot 1") public Player Ballot1Att3;
	@OneToOne @FormFormat(tab = "Ballot 1") public Player Ballot1Att4;
	
	@OneToOne @FormFormat(tab = "Ballot 1") public Player Ballot1Wit1;
	@OneToOne @FormFormat(tab = "Ballot 1") public Player Ballot1Wit2;
	@OneToOne @FormFormat(tab = "Ballot 1") public Player Ballot1Wit3;
	@OneToOne @FormFormat(tab = "Ballot 1") public Player Ballot1Wit4;

	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2POpen;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PAttDirect1;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PAttDirect2;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PAttDirect3;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PAttCross1;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PAttCross2;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PAttCross3;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PWitDirect1;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PWitDirect2;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PWitDirect3;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PWitCross1;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PWitCross2;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PWitCross3;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2PClose;

	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DOpen;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DAttDirect1;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DAttDirect2;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DAttDirect3;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DAttCross1;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DAttCross2;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DAttCross3;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DWitDirect1;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DWitDirect2;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DWitDirect3;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DWitCross1;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DWitCross2;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DWitCross3;
	@Basic @FormFormat(tab = "Ballot 2") public int Ballot2DClose;

	@OneToOne @FormFormat(tab = "Ballot 2") public Player Ballot2Att1;
	@OneToOne @FormFormat(tab = "Ballot 2") public Player Ballot2Att2;
	@OneToOne @FormFormat(tab = "Ballot 2") public Player Ballot2Att3;
	@OneToOne @FormFormat(tab = "Ballot 2") public Player Ballot2Att4;
	
	@OneToOne @FormFormat(tab = "Ballot 2") public Player Ballot2Wit1;
	@OneToOne @FormFormat(tab = "Ballot 2") public Player Ballot2Wit2;
	@OneToOne @FormFormat(tab = "Ballot 2") public Player Ballot2Wit3;
	@OneToOne @FormFormat(tab = "Ballot 2") public Player Ballot2Wit4;
	
	public Match() {
	}
	
	public Match(Team p, Team d) {
		PTeam = p; DTeam = d;
	}
	
	@Override 
	public boolean isSet() {
		List<Player> rankedPlayers = new ArrayList<Player>();
		rankedPlayers.add(Ballot1Att1); rankedPlayers.add(Ballot1Att2); rankedPlayers.add(Ballot1Att3); rankedPlayers.add(Ballot1Att4);
		rankedPlayers.add(Ballot2Att1); rankedPlayers.add(Ballot2Att2); rankedPlayers.add(Ballot2Att3); rankedPlayers.add(Ballot2Att4);
		rankedPlayers.add(Ballot1Wit1); rankedPlayers.add(Ballot1Wit2); rankedPlayers.add(Ballot1Wit3); rankedPlayers.add(Ballot1Wit4);
		rankedPlayers.add(Ballot2Wit1); rankedPlayers.add(Ballot2Wit2); rankedPlayers.add(Ballot2Wit3); rankedPlayers.add(Ballot2Wit4);

		for (Player p : rankedPlayers) {
			if (p == null) {return false;}
		}
	
		List<Player> duplicateChecker = new ArrayList<Player>();
		for (Player p : rankedPlayers) {
			if (duplicateChecker.contains(p)) {
				return false;
			}
			duplicateChecker.add(p);
		}
		return true;
	}
	
	@Override 
	public String toString() {
		return "Match between " + this.PTeam + " and " + this.DTeam;
	}
}
