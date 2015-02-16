package MockTrial;

import javax.persistence.*;

import Base.MustBeSet;
import Base.Record;

@Entity
public class Player extends Record {

	@Basic @MustBeSet public String name;
	@Basic public int PAttRanks;
	@Basic public int PWitRanks;
	@Basic public int DAttRanks;
	@Basic public int DWitRanks;

	
	public Player() {
		this.name = null;
		this.PWitRanks = 0;
		this.DAttRanks = 0;
		this.DWitRanks = 0;
	}
	
	public Player(String name) {
		this.name = name;
		this.PAttRanks = 0; 
		this.PWitRanks = 0;
		this.DAttRanks = 0;
		this.DWitRanks = 0;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean isSet() {
		return true;
	}
}
