package Checkers;

import Base.Record;
import Check.Check;
import DataAction.*;
import Ruleset.*;
import Visibles.Visible;

import java.util.*;

public class CheckersTournamentFactory extends DefaultTournamentFactory {

	@Override
	public Ruleset makeRuleset() {
		
		// ----------- ACTIONS -----------
		List<UIDataAction> configActions = new ArrayList<UIDataAction>();
		configActions.add(new ConfigurePlayersFromSpreadsheet());
		configActions.add(new ConfigurePlayers());
		
		List<AutomaticDataAction> randomPairing = new ArrayList<AutomaticDataAction>();
		randomPairing.add(new RandomPairing());
		
		List<AutomaticDataAction> powerMatching = new ArrayList<AutomaticDataAction>();
		powerMatching.add(new PowerMatching());
		
		List<UIDataAction> inRound = new ArrayList<UIDataAction>();
		inRound.add(new EnterResults());
	
		List<AutomaticDataAction> postRound = new ArrayList<AutomaticDataAction>();
		postRound.add(new ProcessMatches()); 
		postRound.add(new ClearMatches());

		// ----------- CHECKS ------------
		List<Check> configChecks = new ArrayList<Check>();
		configChecks.add(new MustHavePlayers());
		configChecks.add(new NoDuplicatePlayers());
		configChecks.add(new EvenNumberOfPlayers());
		
		// ----------- STATES ------------
		List<State> states = new ArrayList<State>();
		State configure = new State("Configuration", null, configActions, null, configChecks);
		State round1 = new State("Round 1", randomPairing, inRound, postRound, null);
		State round2 = new State("Round 2", powerMatching, inRound, postRound, null);
		State round3 = new State("Round 3", powerMatching, inRound, postRound, null);
		State results = new State("Results", null, null, null, null);
		states.add(configure); states.add(round1); states.add(round2); states.add(round3);
		
		// ----------- EVENTS ------------
		Event proceed = new Event("Proceed");
		List<Event> events = new ArrayList<Event>();
		events.add(proceed);

		// ----- TRANSITION FUNCTION -----
		Map<State, Map<Event, State>> transition = new HashMap<State, Map<Event, State>>();
		Map<Event, State> fromConfigure = new HashMap<Event, State>();
		Map<Event, State> fromRound1 = new HashMap<Event, State>();
		Map<Event, State> fromRound2 = new HashMap<Event, State>();
		Map<Event, State> fromRound3 = new HashMap<Event, State>();
		Map<Event, State> fromEnd = new HashMap<Event, State>();


		fromConfigure.put(proceed, round1); transition.put(configure, fromConfigure);
		fromRound1.put(proceed, round2); transition.put(round1, fromRound1);
		fromRound2.put(proceed, round3); transition.put(round2, fromRound2);
		fromRound3.put(proceed, results); transition.put(round3, fromRound3);
		transition.put(results, fromEnd);
		
		TransitionFunction tfunction = new TransitionFunction(events, transition);
		
		// ----- CONFIG CLASSES ----------
		List<Class<? extends Record>> configClasses = new ArrayList<Class<? extends Record>>();
		configClasses.add(Player.class); configClasses.add(Match.class);
		
		// ----------- VISIBLES ------------		
		List<Visible> visibles = new ArrayList<Visible>();
		visibles.add(new CurrentMatches());
		visibles.add(new PlayerRankings());

		return new Ruleset("Checkers", states, configure, events, tfunction, configClasses, visibles);
	}
}
