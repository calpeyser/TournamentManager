package MockTrial;

import javax.persistence.*;

import Base.MustBeSet;
import Base.Record;

@Entity
public class Match extends Record {

	@OneToOne (targetEntity = Team.class) @MustBeSet public Team PTeam;
	@OneToOne (targetEntity = Team.class) @MustBeSet public Team DTeam;
	
	@Basic public int POpen;
	@Basic public int PAttDirect1;
	@Basic public int PAttDirect2;
	@Basic public int PAttDirect3;
	@Basic public int PAttCross1;
	@Basic public int PAttCross2;
	@Basic public int PAttCross3;
	@Basic public int PWitDirect1;
	@Basic public int PWitDirect2;
	@Basic public int PWitDirect3;
	@Basic public int PWitCross1;
	@Basic public int PWitCross2;
	@Basic public int PWitCross3;
	@Basic public int PClose;

	@Basic public int DOpen;
	@Basic public int DAttDirect1;
	@Basic public int DAttDirect2;
	@Basic public int DAttDirect3;
	@Basic public int DAttCross1;
	@Basic public int DAttCross2;
	@Basic public int DAttCross3;
	@Basic public int DWitDirect1;
	@Basic public int DWitDirect2;
	@Basic public int DWitDirect3;
	@Basic public int DWitCross1;
	@Basic public int DWitCross2;
	@Basic public int DWitCross3;
	@Basic public int DClose;

	@OneToOne public Player Att1;
	@OneToOne public Player Att2;
	@OneToOne public Player Att3;
	@OneToOne public Player Att4;
	
	@OneToOne public Player Wit1;
	@OneToOne public Player Wit2;
	@OneToOne public Player Wit3;
	@OneToOne public Player Wit4;
	
	public Match() {
	}
	
	public Match(Team p, Team d) {
		PTeam = p; DTeam = d;
	}
	
	@Override 
	public boolean isSet() {
		return true;
	}
}
