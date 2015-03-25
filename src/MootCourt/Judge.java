package MootCourt;

import javax.persistence.*;

import Base.FormFormat;
import Base.MustBeSet;
import Base.Record;

@Entity
public class Judge extends Record {

	@Basic @MustBeSet @FormFormat(immutable = true) public String name;
	@OneToOne public @FormFormat(immutable = true) Match currentMatch;
	
	@Basic @FormFormat(tab = "Petitioner", name = "Petitioner 1 Presentation") public int P1Presentation;
	@Basic @FormFormat(tab = "Petitioner", name = "Petitioner 1 Argument") public int P1Argument;
	@Basic @FormFormat(tab = "Petitioner", name = "Petitioner 2 Presentation") public int P2Presentation;
	@Basic @FormFormat(tab = "Petitioner", name = "Petitioner 2 Presentation") public int P2Argument;
	@Basic @FormFormat(tab = "Petitioner", name = "Petitioner Teamwork") public int PTeamwork;
	
	@Basic @FormFormat(tab = "Respondent", name = "Respondent 1 Presentation") public int R1Presentation;
	@Basic @FormFormat(tab = "Respondent", name = "Respondent 1 Argument") public int R1Argument;
	@Basic @FormFormat(tab = "Respondent", name = "Respondent 2 Presentation") public int R2Presentation;
	@Basic @FormFormat(tab = "Respondent", name = "Respondent 2 Argument") public int R2Argument;
	@Basic @FormFormat(tab = "Respondent", name = "Responent Teamwork") public int RTeamwork;

	@OneToOne @FormFormat(tab = "Rankings", name = "Rank : 4 (best)") public Player rank4;
	@OneToOne @FormFormat(tab = "Rankings", name = "Rank : 3") public Player rank3;
	@OneToOne @FormFormat(tab = "Rankings", name = "Rank : 2") public Player rank2;
	@OneToOne @FormFormat(tab = "Rankings", name = "Rank : 1 (worst)") public Player rank1;
	
	public Judge() {
		currentMatch = null;
		rank4 = null; rank3 = null; rank2 = null; rank1 = null;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void clear() {
		this.P1Argument = 0;
		this.P1Presentation = 0;
		this.P2Argument = 0;
		this.P2Presentation = 0;
		this.PTeamwork = 0;
		
		this.R1Presentation = 0;
		this.R1Argument = 0;
		this.R2Presentation = 0;
		this.R2Argument = 0;
		this.RTeamwork = 0;
		
		this.rank1 = null;
		this.rank2 = null;
		this.rank3 = null;
		this.rank4 = null;
	}
	
}
