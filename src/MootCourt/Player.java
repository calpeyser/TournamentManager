package MootCourt;

import javax.persistence.*;

import Base.Record;

@Entity
public class Player extends Record implements Comparable<Player> {

	@Basic public String name;
	@Basic public int ranks;
	
	public Player() {
		this.name = null;
		this.ranks = 0;
	}
	
	public Player(String name) {
		this.name = name;
		this.ranks = 0;
	}

	@Override
	public int compareTo(Player that) {
		if (this.ranks > that.ranks) {return -1;}
		if (this.ranks < that.ranks) {return 1;}
		return 0;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
