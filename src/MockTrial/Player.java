package MockTrial;

import javax.persistence.*;

import Base.MustBeSet;
import Base.Record;

@Entity
public class Player extends Record {

	@Basic @MustBeSet public String name;
	@OneToOne public Team team;
	@Basic public Integer ranks;
	
	public Player(String name, Team team) {
		this.name = name;
		this.team = team;
		this.ranks = 0;
	}
}
