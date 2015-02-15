package MockTrial;

import javax.persistence.*;

import java.util.*;

import Base.MustBeSet;
import Base.Record;

@Entity
public class Team extends Record {
	@Basic @MustBeSet public String schoolName;
	@Basic @MustBeSet public String designation; // for this tournament.  Ex. "936"
	@OneToMany (targetEntity = Player.class) @MustBeSet public List<Player> players;
		
	@OneToMany (targetEntity = Team.class) public List<Team> wins;
	@OneToMany (targetEntity = Team.class) public List<Team> losses;
	
	public Team() {
		this.schoolName = null;
		this.designation = null;
		this.players = new ArrayList<Player>();
		this.wins = new ArrayList<Team>();
		this.losses = new ArrayList<Team>();
	}
		
	public Team(String schoolName, String designation, List<Player> players) {
		this.schoolName = schoolName;
		this.designation = designation;
		this.players = players;
		this.wins = new ArrayList<Team>();
		this.losses = new ArrayList<Team>();
	}
	
	@Override
	public String toString() {
		return schoolName + " " + designation;
	}

	@Override
	public boolean isSet() {
		return true;
	}
	
}
