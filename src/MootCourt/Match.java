package MootCourt;

import javax.persistence.*;

import java.util.*;

import Base.*;
import MockTrial.Player;

@Entity
public class Match extends Record {
	@OneToOne @MustBeSet @FormFormat(immutable = true) public Team PTeam;
	@OneToOne @MustBeSet @FormFormat(immutable = true) public Team RTeam;
	
	@OneToMany (targetEntity = Judge.class) public List<Judge> ballots;
	
	public Match() {
		this.PTeam = null;
		this.RTeam = null;
		this.ballots = new ArrayList<Judge>();
	}
	
	public Match(Team PTeam, Team DTeam) {
		this.PTeam = PTeam;
		this.RTeam = DTeam;
		this.ballots = new ArrayList<Judge>();
	}

	@Override
	public String toString() {
		return PTeam + " vs. " + RTeam;
	}
	
}
