package Ruleset;

import java.util.*;
import Base.*;

public class Ruleset {
	/** All the states that can be taken during execution */
	protected List<State> states;
	
	/** The first state */
	protected State startState;
	
	/** All the events that can occur */
	protected List<Event> events;
	
	/** Dictates what states we can go to */
	protected TransitionFunction transitionFunction;
		
	/** Type of tournament */
	protected Class<? extends Tournament> tournamentType;
	
	public Ruleset(List<State> states,
			       State startState,
			       List<Event> events,
			       TransitionFunction transitionFunction,
			       Class<? extends Tournament> tournamentType) {
		this.states = states;
		this.startState = startState;
		this.events = events;
		this.transitionFunction = transitionFunction;
		this.tournamentType = tournamentType;
	}
	
	public State getStartState() {
		return this.startState;
	}
	
	public TransitionFunction getTransitionFunction() {
		return this.transitionFunction;
	}
	
	public Class<? extends Tournament> getTournamentType() {
		return this.tournamentType;
	}
	
}
