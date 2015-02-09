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
		
		List<UIDataAction> configActions = new ArrayList<UIDataAction>();
		configActions.add(configurePlayers);
		configActions.add(configureTeams);
		
		// states
		State configure = new State("Initial Configuration", null, configActions, null);
		State round1 = new State("Round One", null, null, null);
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
		
		return new Ruleset(states, configure, events, trans, configClasses, visibles);

	}

	@Override
	public TournamentDataStore makeDB(Ruleset r) {

		TournamentDataStore db = super.makeDB(r);
		
		addTestingData(db);
		
		return db;
	}		
	
	// shouldn't be there in real life.  Just to take the pain out of config
	private void addTestingData(TournamentDataStore db) {
		db.getEntityManager().getTransaction().begin();
		Player[] players1 = {
				new Player("Team1Player1"),
				new Player("Team1Player2"),
				new Player("Team1Player3"),
				new Player("Team1Player4"),
				new Player("Team1Player5"),
				new Player("Team1Player6"),
		};
		Player[] players2 = {
				new Player("Team2Player1"),
				new Player("Team2Player2"),
				new Player("Team2Player3"),
				new Player("Team2Player4"),
				new Player("Team2Player5"),
				new Player("Team2Player6"),
				new Player("Team2Player7"),
				new Player("Team2Player8"),
		};
		Player[] players3 = {
				new Player("Team3Player1"),
				new Player("Team3Player2"),
				new Player("Team3Player3"),
				new Player("Team3Player4"),
				new Player("Team3Player5"),
				new Player("Team3Player6"),
				new Player("Team3Player7"),
		};
		Player[] players4 = {
				new Player("Team4Player1"),
				new Player("Team4Player2"),
				new Player("Team4Player3"),
				new Player("Team4Player4"),
				new Player("Team4Player5"),
				new Player("Team4Player6"),
		};
		Player[] players5 = {
				new Player("Team5Player1"),
				new Player("Team5Player2"),
				new Player("Team5Player3"),
				new Player("Team5Player4"),
				new Player("Team5Player5"),
				new Player("Team5Player6"),
				new Player("Team5Player7"),
				new Player("Team5Player8"),
				new Player("Team5Player9"),
		};
		Player[] players6 = {
				new Player("Team6Player1"),
				new Player("Team6Player2"),
				new Player("Team6Player3"),
				new Player("Team6Player4"),
				new Player("Team6Player5"),
				new Player("Team6Player6"),
				new Player("Team6Player7"),
				new Player("Team6Player8"),
		};
		Player[] players7 = {
				new Player("Team7Player1"),
				new Player("Team7Player2"),
				new Player("Team7Player3"),
				new Player("Team7Player4"),
				new Player("Team7Player5"),
				new Player("Team7Player6"),
				new Player("Team7Player7"),
				new Player("Team7Player8"),
				new Player("Team7Player9"),
				new Player("Team7Player10"),
		};
		Player[] players8 = {
				new Player("Team8Player1"),
				new Player("Team8Player2"),
				new Player("Team8Player3"),
				new Player("Team8Player4"),
				new Player("Team8Player5"),
				new Player("Team8Player6"),
		};
		Player[] players9 = {
				new Player("Team9Player1"),
				new Player("Team9Player2"),
				new Player("Team9Player3"),
				new Player("Team9Player4"),
				new Player("Team9Player5"),
				new Player("Team9Player6"),
				new Player("Team9Player7"),
		};
		Player[] players10 = {
				new Player("Team10Player1"),
				new Player("Team10Player2"),
				new Player("Team10Player3"),
				new Player("Team10Player4"),
				new Player("Team10Player5"),
				new Player("Team10Player6"),
				new Player("Team10Player7"),
				new Player("Team10Player8"),
		};
		Player[] players11 = {
				new Player("Team11Player1"),
				new Player("Team11Player2"),
				new Player("Team11Player3"),
				new Player("Team11Player4"),
				new Player("Team11Player5"),
				new Player("Team11Player6"),
				new Player("Team11Player7"),
		};
		Player[] players12 = {
				new Player("Team12Player1"),
				new Player("Team12Player2"),
				new Player("Team12Player3"),
				new Player("Team12Player4"),
				new Player("Team12Player5"),
				new Player("Team12Player6"),
				new Player("Team12Player7"),
				new Player("Team12Player8"),
		};
		Player[] players13 = {
				new Player("Team13Player1"),
				new Player("Team13Player2"),
				new Player("Team13Player3"),
				new Player("Team13Player4"),
				new Player("Team13Player5"),
				new Player("Team13Player6"),
				new Player("Team13Player7"),
		};
		Player[] players14 = {
				new Player("Team14Player1"),
				new Player("Team14Player2"),
				new Player("Team14Player3"),
				new Player("Team14Player4"),
				new Player("Team14Player5"),
				new Player("Team14Player6"),
		};
		Player[] players15 = {
				new Player("Team15Player1"),
				new Player("Team15Player2"),
				new Player("Team15Player3"),
				new Player("Team15Player4"),
				new Player("Team15Player5"),
				new Player("Team15Player6"),
				new Player("Team15Player7"),
				new Player("Team15Player8"),
		};
		Player[] players16 = {
				new Player("Team16Player1"),
				new Player("Team16Player2"),
				new Player("Team16Player3"),
				new Player("Team16Player4"),
				new Player("Team16Player5"),
				new Player("Team16Player6"),
				new Player("Team16Player7"),		
		};
		
		Player[][] allPlayers = {
				players1, players2, players3, players4, players5, players6, players7, players8,
				players9, players10, players11, players12, players13, players14, players15, players16
		};
		
		String[] schools = {
				"Princeton", "Princeton", "Harvard", "Yale", "NYU", "NYU", "NYU", "Cornell",
				"UVA", "Columbia", "Penn", "Miami", "Miami", "Rochester", "UCLA", "Chicago"
		};
		
		String[] designations = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"
		};
		
		List<Team> teams = new ArrayList<Team>();
		for (int index = 0; index < 16; index++) {
			List<Player> teamPlayers = new ArrayList<Player>();
			for (Player p : allPlayers[index]) {
				teamPlayers.add(p);
			}
			Team t = new Team(schools[index], designations[index], teamPlayers);
			db.getEntityManager().persist(t);
		}
		
		for (Player[] t : allPlayers) {
			for (Player p : t) {
				db.getEntityManager().persist(p);
			}
		}
		
		db.getEntityManager().getTransaction().commit();
	}
	


}
