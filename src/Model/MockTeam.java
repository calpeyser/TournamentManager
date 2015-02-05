package Model;

import javax.persistence.*;
import java.util.*;

import Base.*;

@Entity
public class MockTeam extends Record {
	@Basic @MustBeSet public String name;
	@OneToMany (targetEntity = MockPlayer.class) public List<MockPlayer> players;
	
	public MockTeam() {
		players = new ArrayList<MockPlayer>();
	}
	
	@Override
	public String toString() {
		return name;
	}
}
