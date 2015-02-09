package Ruleset;

import java.util.*;

import Visibles.Visible;
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
			
	/** Database classes to configure before the tournament */
	protected List<Class<? extends Record>> configClasses;
	
	/** The visible state of the tournament */
	protected List<Visible> visibles;
	
	public Ruleset(List<State> states,
			       State startState,
			       List<Event> events,
			       TransitionFunction transitionFunction,
			       List<Class<? extends Record>> configClasses,
			       List<Visible> visibles) {
		this.states = states;
		this.startState = startState;
		this.events = events;
		this.transitionFunction = transitionFunction;
		this.configClasses = configClasses;
		this.visibles = visibles;
	}
	
	public State getStartState() {
		return this.startState;
	}
	
	public TransitionFunction getTransitionFunction() {
		return this.transitionFunction;
	}
	
	public List<Visible> getVisibles() {
		return this.visibles;
	}
	
	public State stateByName(String stateName) {
		for (State s : states) {
			if (s.getName().equals(stateName)) {
				return s;
			}
		}
		throw new RuntimeException("Could not find state named " + stateName);
	}
}
