package MockTrial;

import javax.persistence.*;

import Base.MustBeSet;
import Base.Record;

@Entity
public class Match extends Record {

	@OneToOne (targetEntity = Team.class) @MustBeSet public Team PTeam;
	@OneToOne (targetEntity = Team.class) @MustBeSet public Team DTeam;
	
	@Basic public Integer POpen;
	@Basic public Integer PAttDirect1;
	@Basic public Integer PAttDirect2;
	@Basic public Integer PAttDirect3;
	@Basic public Integer PAttCross1;
	@Basic public Integer PAttCross2;
	@Basic public Integer PAttCross3;
	@Basic public Integer PWitDirect1;
	@Basic public Integer PWitDirect2;
	@Basic public Integer PWitDirect3;
	@Basic public Integer PWitCross1;
	@Basic public Integer PWitCross2;
	@Basic public Integer PWitCross3;
	@Basic public Integer PClose;

	@Basic public Integer DOpen;
	@Basic public Integer DAttDirect1;
	@Basic public Integer DAttDirect2;
	@Basic public Integer DAttDirect3;
	@Basic public Integer DAttCross1;
	@Basic public Integer DAttCross2;
	@Basic public Integer DAttCross3;
	@Basic public Integer DWitDirect1;
	@Basic public Integer DWitDirect2;
	@Basic public Integer DWitDirect3;
	@Basic public Integer DWitCross1;
	@Basic public Integer DWitCross2;
	@Basic public Integer DWitCross3;
	@Basic public Integer DClose;

	public Match() {}
}
