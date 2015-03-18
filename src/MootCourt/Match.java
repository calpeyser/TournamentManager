package MootCourt;

import javax.persistence.*;
import java.util.*;
import Base.*;

public class Match extends Record {
	@OneToOne @MustBeSet public Team PTeam;
	@OneToOne @MustBeSet public Team RTeam;
	
	@OneToMany public List<Ballot> ballots;
	
	public Match() {
		this.PTeam = null;
		this.RTeam = null;
		this.ballots = new ArrayList<Ballot>();
	}
	
	public Match(Team PTeam, Team DTeam) {
		this.PTeam = PTeam;
		this.RTeam = DTeam;
		this.ballots = new ArrayList<Ballot>();
	}

	@Override
	public String toString() {
		return "Match between " + PTeam + " and " + RTeam;
	}
	
}
