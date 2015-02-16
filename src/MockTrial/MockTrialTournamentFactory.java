package MockTrial;

import java.util.*;

import javax.persistence.Query;

import Base.Record;
import Data.*;
import DataAction.*;
import Ruleset.*;
import Visibles.Visible;
/**
 * Describes, on the highest level, the AMTA Mock Trial ruleset
 *
 */
public class MockTrialTournamentFactory extends DefaultTournamentFactory {

	@Override
	public Ruleset makeRuleset() {

		// actions
		UIDataAction configurePlayers = new AddPlayers();
		UIDataAction configureTeams   = new AddTeams();
		UIDataAction excelConfig = new MTExcelConfig();
		
		List<UIDataAction> configActions = new ArrayList<UIDataAction>();
		configActions.add(configurePlayers);
		configActions.add(configureTeams);
		configActions.add(excelConfig);
		
		List<AutomaticDataAction> round1EntryActions = new ArrayList<AutomaticDataAction>();
		round1EntryActions.add(new Round1Pairings());
		
		List<UIDataAction> roundActions = new ArrayList<UIDataAction>();
		roundActions.add(new EditMatches());
		
		List<AutomaticDataAction> roundEndActions = new ArrayList<AutomaticDataAction>();
		roundEndActions.add(new ProcessMatches());
		roundEndActions.add(new ClearMatches());
		
		
		// states
		State configure = new State("Initial Configuration", null, configActions, null);
		State round1 = new State("Round One", round1EntryActions, roundActions, roundEndActions);
		State round2 = new State("Round Two", null, null, null);
		State round3 = new State("Round Three", null, null, null);
		State round4 = new State("Round Four", null, null, null);
		State end = new State("Tournament Finished", null, null, null);
		List<State> states = new ArrayList<State>();
		states.add(configure); states.add(round1); states.add(round2); states.add(round3);
		states.add(round4); states.add(end);
		
		// there's only one event
		Event proceed = new Event("Proceed");
		List<Event> events = new ArrayList<Event>();
		events.add(proceed);
		
		
		// transition function
		Map<State, Map<Event, State>> transition = new HashMap<State, Map<Event, State>>();
		// configure state
		Map<Event, State> fromConfigure = new HashMap<Event, State>();
		fromConfigure.put(proceed, round1); transition.put(configure, fromConfigure);
		// rounds
		Map<Event, State> fromRound1 = new HashMap<Event, State>();
		Map<Event, State> fromRound2 = new HashMap<Event, State>();
		Map<Event, State> fromRound3 = new HashMap<Event, State>();
		Map<Event, State> fromRound4 = new HashMap<Event, State>();
		fromRound1.put(proceed, round2);
		fromRound2.put(proceed, round3);
		fromRound3.put(proceed, round4);
		fromRound4.put(proceed, end);
		transition.put(round1, fromRound1);
		transition.put(round2, fromRound2);
		transition.put(round3, fromRound3);
		transition.put(round4, fromRound4);
		
		// end states
		Map<Event, State> fromEnd = new HashMap<Event, State>();
		transition.put(end, fromEnd);
		TransitionFunction trans = new TransitionFunction(events, transition);
		
		// config classes
		List<Class<? extends Record>> configClasses = new ArrayList<Class<? extends Record>>();
		configClasses.add(Player.class);
		configClasses.add(Team.class);
		
		// visible
		List<Visible> visibles = new ArrayList<Visible>();
		visibles.add(new TeamRankings());
		
		return new Ruleset(states, configure, events, trans, configClasses, visibles);

	}
}
