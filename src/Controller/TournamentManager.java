package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.*;

import javax.persistence.Query;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;

import Base.*;
import Ruleset.*;
import Data.*;
import DataAction.*;
import Model.*;
import View.*;
import Visibles.*;
import MockTrial.*; // gonna try to repurpose this...

public class TournamentManager {
	
	public ContextFrame frame;
	private Ruleset ruleset;
	private TournamentDataStore db;
		
	private ActionListener stateChanger() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Event eventOccured = frame.eventList.getSelectedValue();
				// execute exiting actions
				for (AutomaticDataAction action : db.getContext().getCurrentState().getExitConfig()) {
					action.bind(db);
					action.execute();
				}
				
				// find new state
				State newState = ruleset.getTransitionFunction().transition(db.getContext().getCurrentState(), eventOccured);
				db.getContext().setCurrentState(newState);
				
				// execute entry actions
				for (AutomaticDataAction action : newState.getEntryConfig()) {
					action.bind(db);
					action.execute();
				}
				
				// tell the database that we are in a new state
				db.getEntityManager().getTransaction().begin();
				Query q = db.getEntityManager().createQuery("SELECT c FROM State c");
				List<State> oldStates = q.getResultList();
				for (State s : oldStates) {
					db.getEntityManager().detach(s);
				}
				db.getEntityManager().persist(newState);
				db.getEntityManager().getTransaction().commit();
				
				// save the database
				db.save();
				
				// alter frame
				frame.setState(newState);			
			}
		};
	}
	
	private ActionListener dynamicAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				UIDataAction actionToPerform = frame.actionList.getSelectedValue();
				// open the dialogue 
				actionToPerform.bind(db);
				actionToPerform.attachToFrame(frame);
			}
		};
	}
		
	public TournamentManager(final Ruleset ruleset, final TournamentDataStore db) {		
		this.ruleset = ruleset;
		this.db = db;
		
		for (AutomaticDataAction action : ruleset.getStartState().getEntryConfig()) {
			action.bind(db);
			action.execute();
		}
		
		for (Visible v : ruleset.getVisibles()) {
			v.bind(db);
		}
		
		this.frame = new ContextFrame(db.getContext(), ruleset);
		
		/* Transitioning to a new state */
		frame.addListenerToEvent(stateChanger());
		frame.addDynamicActions(dynamicAction());
	}
	
	public static void main(String[] args) {

		MockTrialTournamentFactory factory = new MockTrialTournamentFactory();
		Ruleset ruleset = factory.makeRuleset();
		TournamentDataStore dbinit = null; 
		if (args.length == 0) {
			dbinit = factory.makeDB(ruleset);
		}
		else {
			dbinit = factory.makeDB(args[0]);
		}
		
		final TournamentDataStore db = dbinit;
		
		TournamentManager tm = new TournamentManager(ruleset, db);
		tm.frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				db.wipeDataStore();
				e.getWindow().dispose();
			}
		});
		tm.frame.setVisible(true);
	}
}
