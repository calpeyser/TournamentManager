package Model;

import Base.*;
import javax.persistence.*;

@Entity
public class MockMatch extends Record {
	@Basic String name;
	@OneToOne MockPlayer player1;
	@OneToOne MockPlayer player2;

	
	public MockMatch(String name, MockPlayer player1, MockPlayer player2) {
		this.name = name;
		this.player1 = player1;
		this.player2 = player2;
	}
}
