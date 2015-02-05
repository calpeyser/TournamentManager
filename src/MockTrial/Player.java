package MockTrial;

import javax.persistence.*;

import Base.MustBeSet;
import Base.Record;

@Entity
public class Player extends Record {

	@Basic @MustBeSet public String name;
	// @OneToOne (targetEntity = Team.class) public Team team;
	@Basic public Integer ranks;
	
	public Player() {
		this.name = null;
	//	this.team = null;
		this.ranks = 0;
	}
	
	public Player(String name) {
		this.name = name;
	//	this.team = null;
		this.ranks = 0;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
