package Data;

import Ruleset.*;
// Encapsulates the state of a tournament
public class TournamentContext {
	private State currentState;
	
	public TournamentContext(State currentState) {
		this.currentState = currentState;
	}
	
	public State getCurrentState() {
		return this.currentState;
	}

	public void setCurrentState(State state) {
		this.currentState = state;
	}
}

