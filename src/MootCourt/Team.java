package MootCourt;

import javax.persistence.*;

import Base.*;

public class Team extends Record implements Comparable<Team> {
	@Basic @MustBeSet public String schoolName;
	@Basic @MustBeSet public String designation;
	
	@Basic @MustBeSet public String player1Name;
	@Basic @MustBeSet public String player2Name;
	
	@Basic public int score;
	@Basic public int pointDifferential;
	
	@Basic boolean wentP;
	
	public Team() {
		this.schoolName = null;
		this.designation = null;
		this.player1Name = null;
		this.player2Name = null;
	}
		
	public Team(String schoolName, String designation, String player1Name, String player2Name) {
		this.schoolName = schoolName;
		this.designation = designation;
		this.player1Name = player1Name;
		this.player2Name = player2Name;
	}
	
	@Override
	public String toString() {
		return "Team " + designation + " from " + schoolName;
	}

	@Override
	public int compareTo(Team that) {
		if (this.score > that.score) {return -1;}
		if (this.score < that.score) {return 1;}
		if (this.pointDifferential > that.pointDifferential) {return -1;}
		if (this.pointDifferential < that.pointDifferential) {return 1;}
		return 0;
	}

}
