package Ruleset;

import java.util.*;

public class TransitionFunction {
	
	private List<Event> possibleEvents;
	private Map<State, Map<Event, State>> transition;
	 
	public TransitionFunction(List<Event> possibleEvents, Map<State, Map<Event, State>> transition) {
		this.possibleEvents = possibleEvents;
		this.transition = transition;
	}

	public List<Event> getPossibleEvents() {
		return this.possibleEvents;
	}
	
	public List<Event> getPossibleEvents(State fromState) {
		Set<Event> events = transition.get(fromState).keySet();
		List<Event> out = new ArrayList<Event>();
		for (Event e : events) {
			out.add(e);
		}
		return out;
	}
	
	public State transition(State fromState, Event event) {
		return transition.get(fromState).get(event);
	}
	
}
