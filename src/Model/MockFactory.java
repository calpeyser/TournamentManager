package Model;

import Ruleset.*;
import Data.*;
import DataAction.*;

import java.util.*;

import Model.*;

/** Contains a bunch of static methods that create
 * the objects necessary to instantiate a 
 * TournamentManager
 */
public class MockFactory {
	public static Ruleset makeMockRuleset() {
		// automatic actions
		List<AutomaticDataAction> configure = new ArrayList<AutomaticDataAction>();
		configure.add(new MakePlayers());
		List<AutomaticDataAction> display = new ArrayList<AutomaticDataAction>();
		display.add(new PrintAllPlayers());
		
		// dynamic actions
		List<UIDataAction> configurePlayer = new ArrayList<UIDataAction>();
		configurePlayer.add(new ConfigurePlayer());
		
		// states and events
		State startState = new State("start", configure, configurePlayer, null);
		State midState1 = new State("mid1", null, null, null);
		State midState2 = new State("mid2", null, null, null);
		State endState = new State("end", display, null, null);
		Event left = new Event("left");
		Event right = new Event("right");
		List<State> states = new ArrayList<State>();
		states.add(startState); states.add(endState); states.add(midState1); states.add(midState2);
		List<Event> events = new ArrayList<Event>();
		events.add(left); events.add(right);
		
		// transition function
		Map<State, Map<Event, State>> transition = new HashMap<State, Map<Event, State>>();
		// start state
		Map<Event, State> fromStart = new HashMap<Event, State>();
		fromStart.put(left, midState1); fromStart.put(right, midState2); transition.put(startState, fromStart);
		// middle states
		Map<Event, State> fromMid = new HashMap<Event, State>();
		fromMid.put(left, endState); fromMid.put(right,  endState);
		transition.put(midState1, fromMid); transition.put(midState2, fromMid);
		// end states
		Map<Event, State> fromEnd = new HashMap<Event, State>();
		transition.put(endState, fromEnd);
		TransitionFunction trans = new TransitionFunction(events, transition);
		
		return new Ruleset(states, startState, events, trans, MockTournament.class);
	}
	
	public static TournamentDataStore makeMockDB(Ruleset r) {
		TournamentDataStore db = new ObjectDBDataStore("$objectdb/db/MockDB2.odb");
		db.wipeDataStore();
		for (int i = 0; i < 7; i++) {
			String name = ("Player " + (i+1));
			MockPlayer p = new MockPlayer(name);
			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().persist(p);
			db.getEntityManager().getTransaction().commit();
		}
		
		db.setContext(new TournamentContext(r.getStartState()));
		
		return db;
	}
	
	
	
}
