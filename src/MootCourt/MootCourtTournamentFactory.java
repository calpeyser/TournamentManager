package MootCourt;

import Ruleset.*;
import Visibles.*;
import Base.Record;
import Check.Check;
import DataAction.*;

import java.util.*;

public class MootCourtTournamentFactory extends DefaultTournamentFactory {

	@Override
	public Ruleset makeRuleset() {
		List<UIDataAction> configActions = new ArrayList<UIDataAction>();
		List<UIDataAction> roundActions = new ArrayList<UIDataAction>();
		List<UIDataAction> assignJudgesActions = new ArrayList<UIDataAction>();
		List<AutomaticDataAction> randomPairing = new ArrayList<AutomaticDataAction>();
		List<AutomaticDataAction> powerMatching = new ArrayList<AutomaticDataAction>();
		List<AutomaticDataAction> processMatches = new ArrayList<AutomaticDataAction>();
		List<AutomaticDataAction> processJudgeAssignments = new ArrayList<AutomaticDataAction>();
		
		configActions.add(new ExcelParticipantsConfig());
		configActions.add(new ExcelJudgesConfig());
		configActions.add(new ManualTeamsConfig());
		configActions.add(new ManualJudgesConfig());
		roundActions.add(new EnterBallotResults());
		roundActions.add(new RandomizeBallotResults());
		processMatches.add(new ProcessMatchesNonElimination());
		processMatches.add(new ClearMatches());
		assignJudgesActions.add(new AssignJudges());
		assignJudgesActions.add(new RandomizeJudgeAssignments());
		randomPairing.add(new RandomPairing());
		powerMatching.add(new PowerMatching());
		processJudgeAssignments.add(new ProcessJudgeAssignments());
		
		
		List<Check> roundExitChecks = new ArrayList<Check>();
		roundExitChecks.add(new RoundExitChecks());
		
		List<Check> nonElimAssignmentChecks = new ArrayList<Check>();
		nonElimAssignmentChecks.add(new AssignmentNonElimCheck());
		
		List<State> states = new ArrayList<State>();
		State configure = new State("Configuration", null, configActions, null, null);
		State round1 = new State("Round 1", null, roundActions, processMatches, roundExitChecks);
		State round2 = new State("Round 2", null, roundActions, processMatches, roundExitChecks);
		State round3 = new State("Round 3", null, roundActions, processMatches, roundExitChecks);
		State round4 = new State("Round 4", null, roundActions, processMatches, roundExitChecks);
		State elim32 = new State("Elimination : Round of 32", null, roundActions, processMatches, roundExitChecks);
		State elim16 = new State("Elimination : Sweet Sixteen", null, roundActions, processMatches, roundExitChecks);
		State elim8 = new State("Elimination : Quarterfinals", null, roundActions, processMatches, roundExitChecks);
		State elim4 = new State("Elimination : Semifinals", null, roundActions, processMatches, roundExitChecks);
		State elim2 = new State("Elimination : Finals", null, roundActions, processMatches, roundExitChecks);
		State end = new State("Tournament Finished", null, null, null, null);
		
		// judge assignment
		State assignJudges1 = new State("Assign Judges", randomPairing, assignJudgesActions, processJudgeAssignments, nonElimAssignmentChecks);
		State assignJudges2 = new State("Assign Judges", powerMatching, assignJudgesActions, processJudgeAssignments, nonElimAssignmentChecks);
		State assignJudges3 = new State("Assign Judges", powerMatching, assignJudgesActions, processJudgeAssignments, nonElimAssignmentChecks);
		State assignJudges4 = new State("Assign Judges", powerMatching, assignJudgesActions, processJudgeAssignments, nonElimAssignmentChecks);
		State assignJudgesElim32 = new State("Assign Judges", null, assignJudgesActions, processJudgeAssignments, null);
		State assignJudgesElim16 = new State("Assign Judges", null, assignJudgesActions, processJudgeAssignments, null);
		State assignJudgesElim8 = new State("Assign Judges", null, assignJudgesActions, processJudgeAssignments, null);
		State assignJudgesElim4 = new State("Assign Judges", null, assignJudgesActions, processJudgeAssignments, null);
		State assignJudgesElim2 = new State("Assign Judges", null, assignJudgesActions, processJudgeAssignments, null);

		states.add(configure); states.add(round1); states.add(round2); states.add(round3); states.add(round4);
		states.add(elim32); states.add(elim16); states.add(elim8); states.add(elim4); states.add(elim2); states.add(end);
		states.add(assignJudges1); states.add(assignJudges2); states.add(assignJudges3); states.add(assignJudges4);
		states.add(assignJudgesElim32); states.add(assignJudgesElim16); states.add(assignJudgesElim8);
		states.add(assignJudgesElim4); states.add(assignJudgesElim8); states.add(assignJudges4); states.add(assignJudgesElim2);

		
		
		Event proceed = new Event("Proceed");
		List<Event> events = new ArrayList<Event>();
		events.add(proceed);

		// transition function
		Map<State, Map<Event, State>> transition = new HashMap<State, Map<Event, State>>();
		// configure state
		Map<Event, State> fromConfigure = new HashMap<Event, State>();
		fromConfigure.put(proceed, assignJudges1); transition.put(configure, fromConfigure);
		
		// rounds
		Map<Event, State> fromRound1 = new HashMap<Event, State>();
		Map<Event, State> fromRound2 = new HashMap<Event, State>();
		Map<Event, State> fromRound3 = new HashMap<Event, State>();
		Map<Event, State> fromRound4 = new HashMap<Event, State>();
		Map<Event, State> fromElim32 = new HashMap<Event, State>();
		Map<Event, State> fromElim16 = new HashMap<Event, State>();
		Map<Event, State> fromElim8 = new HashMap<Event, State>();
		Map<Event, State> fromElim4 = new HashMap<Event, State>();
		Map<Event, State> fromElim2 = new HashMap<Event, State>();

		Map<Event, State> toRound1 = new HashMap<Event, State>();
		Map<Event, State> toRound2 = new HashMap<Event, State>();
		Map<Event, State> toRound3 = new HashMap<Event, State>();
		Map<Event, State> toRound4 = new HashMap<Event, State>();
		Map<Event, State> toElim32 = new HashMap<Event, State>();
		Map<Event, State> toElim16 = new HashMap<Event, State>();
		Map<Event, State> toElim8 = new HashMap<Event, State>();
		Map<Event, State> toElim4 = new HashMap<Event, State>();
		Map<Event, State> toElim2 = new HashMap<Event, State>();

		
		fromRound1.put(proceed, assignJudges2); transition.put(round1, fromRound1);
		fromRound2.put(proceed, assignJudges3); transition.put(round2, fromRound2);
		fromRound3.put(proceed, assignJudges4); transition.put(round3, fromRound3);
		fromRound4.put(proceed, assignJudgesElim32); transition.put(round4, fromRound4);
		fromElim32.put(proceed, assignJudgesElim16); transition.put(elim32, fromElim32);
		fromElim16.put(proceed, assignJudgesElim8); transition.put(elim16, fromElim16);
		fromElim8.put(proceed, assignJudgesElim4); transition.put(elim8, fromElim8);
		fromElim4.put(proceed, assignJudgesElim2); transition.put(elim4, fromElim4);
		fromElim2.put(proceed, end); 
		
		toRound1.put(proceed, round1); transition.put(assignJudges1, toRound1);
		toRound2.put(proceed, round2); transition.put(assignJudges2, toRound2);
		toRound3.put(proceed, round3); transition.put(assignJudges3, toRound3);
		toRound4.put(proceed, round4); transition.put(assignJudges4, toRound4);
		toElim32.put(proceed, elim32); transition.put(assignJudgesElim32, toElim32);
		toElim16.put(proceed, elim16); transition.put(assignJudgesElim16, toElim16);
		toElim8.put(proceed, elim8); transition.put(assignJudgesElim8, toElim8);
		toElim4.put(proceed, elim4); transition.put(assignJudgesElim4, toElim4);
		toElim2.put(proceed, elim2); transition.put(assignJudgesElim2, toElim2);
		
		transition.put(round1, fromRound1);
		transition.put(round2, fromRound2);
		transition.put(round3, fromRound3);
		transition.put(round4, fromRound4);
		transition.put(elim32, fromElim32);
		transition.put(elim16, fromElim16);
		transition.put(elim8, fromElim8);
		transition.put(elim4, fromElim4);
		transition.put(elim2, fromElim2);
		
		TransitionFunction tfunction = new TransitionFunction(events, transition);

		List<Class<? extends Record>> configClasses = new ArrayList<Class<? extends Record>>();
		configClasses.add(Match.class); configClasses.add(Judge.class);
		
		List<Visible> visibles = new ArrayList<Visible>();
		visibles.add(new CurrentMatches());
		visibles.add(new TeamRankings());
		visibles.add(new PlayerRankings());
		
		return new Ruleset("Moot Court", states, configure, events, tfunction, configClasses, visibles);
		
	}

}
