package Model;

import Base.*;
import javax.persistence.*;

@Entity
public class MockMatch extends Record {
	@Basic @MustBeSet public String name;
	@OneToOne public MockPlayer player1;
	@OneToOne public MockPlayer player2;

	
	public MockMatch() {
		this.player1 = null;
		this.player2 = null;
	}
		
	@Override
	public String toString() {
		return name;
	}
	
}
