package MockTrial;

import javax.persistence.*;

import java.util.*;

import Base.MustBeSet;
import Base.Record;

@Entity
public class Team extends Record implements Comparable<Team> {
	@Basic @MustBeSet public String schoolName;
	@Basic @MustBeSet public String designation; // for this tournament.  Ex. "936"
	@OneToMany (targetEntity = Player.class) @MustBeSet public List<Player> players;
	
	@Basic public int CombinedStrength;
	@Basic public int PointDifferential;
	@Basic public double score;
	
	@Basic public boolean wentP; // did this team most recently go P?
	
	@OneToMany (targetEntity = Team.class) public List<Team> opponents;
		
	public Team() {
		this.schoolName = null;
		this.designation = null;
		this.players = new ArrayList<Player>();
		this.opponents = new ArrayList<Team>();
	}
		
	public Team(String schoolName, String designation, List<Player> players) {
		this.schoolName = schoolName;
		this.designation = designation;
		this.players = players;
		this.opponents = new ArrayList<Team>();
	}
	
	@Override
	public String toString() {
		return schoolName + " " + designation;
	}

	@Override
	public int compareTo(Team that) {
		if (this.score > that.score) {return -1;}
		else if (this.score < that.score) {return 1;}
		else {
			// if this is round 1, prioritize point differential
			if (this.opponents.size() == 1) {
				if (this.PointDifferential > that.PointDifferential) {return -1;}
				else if (this.PointDifferential < that.PointDifferential) {return 1;}
			}
			if (this.CombinedStrength > that.CombinedStrength) {return -1;}
			else if (this.CombinedStrength < that.CombinedStrength) {return 1;}
			else {return 0;}
		}
	}

	@Override
	public boolean isSet() {
		return true;
	}
	
}
