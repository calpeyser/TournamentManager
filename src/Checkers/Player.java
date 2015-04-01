package Checkers;

import javax.persistence.*;

import Base.*;

@Entity
public class Player extends Record implements Comparable<Player> {
	@Basic @MustBeSet @FormFormat(name = "Player Name") public String name;
	@Basic @FormFormat(name = "Games Won") public int gamesWon;
	@Basic @FormFormat(name = "Total Turns Played") public int totalTurns;
	
	public Player() {
		name = null; gamesWon = 0; totalTurns = 0;
	}
	
	public Player(String name) {
		this.name = name; gamesWon = 0; totalTurns = 0;
	}

	@Override
	public int compareTo(Player that) {
		if (this.gamesWon > that.gamesWon) {return -1;}
		if (this.gamesWon < that.gamesWon) {return 1;}
		if (this.totalTurns < that.totalTurns) {return -1;}
		if (this.totalTurns > that.totalTurns) {return 1;}
		return 0;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
