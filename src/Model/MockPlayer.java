package Model;

import java.lang.reflect.*;

import javax.persistence.*;

import Base.*;

@Entity
public class MockPlayer extends Record {
	@Basic @Preconfig public String name;
	@Basic public Integer score;
	
	public MockPlayer(String name) {
		this.name = name;
		this.score = 0;
	}
	
	@Override
	public String toString() {
		return "Player with name " + name + " and score " + score;
	}
		
}
