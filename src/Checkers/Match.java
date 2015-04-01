package Checkers;

import javax.persistence.*;

import Base.*;

@Entity
public class Match extends Record {
	@OneToOne @MustBeSet @FormFormat(name = "Player 1") public Player player1;
	@OneToOne @MustBeSet @FormFormat(name = "Player 2") public Player player2;
	@OneToOne @FormFormat(tab = "Results", name = "Winner") public Player winner;
	@Basic @FormFormat(tab = "Results", name = "Turns Played") public int turns;
	
	public Match() {
		player1 = null; player2 = null; winner = null; turns = 0;
	}
	
	public Match(Player player1, Player player2) {
		this.player1 = player1; this.player2 = player2; winner = null; turns = 0;
	}
	
	@Override
	public String toString() {
		return player1 + " vs. " + player2;
	}
}
