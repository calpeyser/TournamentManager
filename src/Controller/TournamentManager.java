package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.*;

import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;

import Base.*;
import Check.Check;
import Check.CheckFailedException;
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
	private PopupDialog popup;
		
	private ActionListener stateChanger() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Event eventOccured = frame.eventList.getSelectedValue();
				if (eventOccured == null) {
					return;
				}
				
				int reply = JOptionPane.showConfirmDialog(frame, "Are you sure you want to invoke event " + eventOccured + "?", "Invoke Event?",  JOptionPane.YES_NO_OPTION);
				if (reply != JOptionPane.YES_OPTION) {
				   return;
				}
				
				// perform exit checks
				for (Check c : db.getContext().getCurrentState().getExitChecks()) {
					c.bind(db);
					try {
						c.performCheck();
					}
					catch (CheckFailedException e) {
						popup = new PopupDialog(frame, e.getMessage());
						popup.addListenerToClose(closePopup());
						popup.setVisible(true);	
						return;
					}
				}
				
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
				if (actionToPerform == null) {
					return;
				}
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
	
	private ActionListener closePopup() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popup.setVisible(false);
				popup.dispose();
			}
		};
	}
	
	public static void createNewTourament(TournamentFactory factory, String path) {
		Ruleset ruleset = factory.makeRuleset();
		TournamentDataStore db = factory.makeNewDB(ruleset, path);
		TournamentManager tm = new TournamentManager(ruleset, db);
		tm.frame.setVisible(true);	

	}
	
	public static void recoverTournament(TournamentFactory factory, String path) {
		Ruleset ruleset = factory.makeRuleset();
		TournamentDataStore db = factory.recoverDB(ruleset, path); 
		TournamentManager tm = new TournamentManager(ruleset, db);
		tm.frame.setVisible(true);	
	}	
}
