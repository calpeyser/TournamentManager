package MootCourt;

import javax.persistence.*;
import Base.*;
import java.util.*;

@Entity
public class Bracket extends Record {

	@Basic public BracketStage stage;

	@OneToMany (targetEntity = Match.class) public List<Match> matches; 
		
	public Bracket() {
		stage = BracketStage.ROUND_OF_32;
	}
	
	public Bracket(List<Match> matches) {
		stage = BracketStage.ROUND_OF_32;
		Utils.customAssert(matches.size() == 16, "Bracket must start with 16 matches");
		this.matches = matches;
	}
	
	@Override
	public String toString() {
		String out = "Bracket with ";
		for (Match m : matches) {
			out += " " + m;
		}
		return out;
	}
}
