package Data;

import Ruleset.*;
import java.util.*;
import Visibles.*;

// Encapsulates the state of a tournament
public class TournamentContext {
	private State currentState;
	private List<Visible> visibles;
	
	public TournamentContext(State currentState) {
		this.currentState = currentState;
	}
	
	public State getCurrentState() {
		return this.currentState;
	}

	public void setCurrentState(State state) {
		this.currentState = state;
	}
	
	public List<Visible> getVisibles() {
		return this.visibles;
	}
	
	public void getVisibles(List<Visible> visibles) {
		this.visibles = visibles;
	}
}

