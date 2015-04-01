package MootCourt;

import javax.persistence.*;

import java.util.*;

import Base.*;

@Entity
public class Team extends Record implements Comparable<Team> {
	@Basic @MustBeSet public String schoolName;
	@Basic @MustBeSet public String designation;
	
	@OneToOne @MustBeSet public Player player1;
	@OneToOne @MustBeSet public Player player2;
	
	@OneToMany (targetEntity = Team.class) public List<Team> hitTeams;
	@OneToMany (targetEntity = Team.class) public List<Team> hitTeamsAsP;
	@OneToMany (targetEntity = Team.class) public List<Team> hitTeamsAsR;
	
	
	@Basic public int ballots;
	@Basic public int pointDifferential;
	
	@Basic boolean wentP;
	
	@Basic boolean eliminated;
	
	public Team() {
		this.schoolName = null;
		this.designation = null;
		this.player1 = null;
		this.player2 = null;
		this.hitTeams = new ArrayList<Team>();
		this.hitTeamsAsP = new ArrayList<Team>();
		this.hitTeamsAsR = new ArrayList<Team>();
		this.eliminated = false;
	}
		
	public Team(String schoolName, String designation, Player player1, Player player2) {
		this.schoolName = schoolName;
		this.designation = designation;
		this.player1 = player1;
		this.player2 = player2;
		this.hitTeams = new ArrayList<Team>();
		this.hitTeamsAsP = new ArrayList<Team>();
		this.hitTeamsAsR = new ArrayList<Team>();
		this.eliminated = false;
	}
	
	@Override
	public String toString() {
		return "Team " + designation + " from " + schoolName;
	}

	@Override
	public int compareTo(Team that) {
		if (this.ballots > that.ballots) {return -1;}
		if (this.ballots < that.ballots) {return 1;}
		if (this.pointDifferential > that.pointDifferential) {return -1;}
		if (this.pointDifferential < that.pointDifferential) {return 1;}
		return 0;
	}

}
