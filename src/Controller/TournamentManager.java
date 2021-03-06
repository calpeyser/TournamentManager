package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;







import Check.Check;
import Check.CheckFailedException;
import Ruleset.*;
import Data.*;
import DataAction.*;
import View.*;
import Visibles.*;

public class TournamentManager {
	
	public ContextFrame frame;
	private Ruleset ruleset;
	private TournamentDataStore db;
		
	private ActionListener stateChanger() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Event eventOccured = frame.selectedEvent;
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
						int reply2 = JOptionPane.showConfirmDialog(frame, "WARNING: " + e.getMessage() +".  Proceeding may cause ruleset failure.  Proceed anyway?", "STATE WARNING", JOptionPane.YES_NO_OPTION);
						if (reply2 != JOptionPane.YES_OPTION) {
							return;
						}
					}
				}
				
				// execute exiting actions
				for (AutomaticDataAction action : db.getContext().getCurrentState().getExitConfig()) {
					action.bind(db);
					try {
						action.execute();
					} catch (DataActionException e) {
						JOptionPane.showMessageDialog(frame, e.getMessage());
						return;
					}
				}
				
				// find new state
				State newState = ruleset.getTransitionFunction().transition(db.getContext().getCurrentState(), eventOccured);
				db.getContext().setCurrentState(newState);
				
				// execute entry actions
				for (AutomaticDataAction action : newState.getEntryConfig()) {
					action.bind(db);
					try {
						action.execute();
					} catch (DataActionException e) {
						e.printStackTrace();
						return;
					}
				}
								
				// auto-save the database
				db.save();
				
				// alter frame
				frame.setState(newState);			
			}
		};
	} 
	
	private ActionListener dynamicAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				UIDataAction actionToPerform = frame.selectedAction;
				if (actionToPerform == null) {
					return;
				}
				// open the dialogue 
				actionToPerform.bind(db);
				actionToPerform.attachToFrame(frame);
			}
		};
	}
	
	private ActionListener saveListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String savename = JOptionPane.showInputDialog("Please provide a name for this tournament snapshot");
				if (savename == null) {
					return;
				}
				db.save(savename, frame);
			}
		};
	}
		
	public TournamentManager(final Ruleset ruleset, final TournamentDataStore db) {		
		this.ruleset = ruleset;
		this.db = db;
		
		// bind actions
		for (AutomaticDataAction action : ruleset.getStartState().getEntryConfig()) {
			action.bind(db);
			try {
				action.execute();
			} catch (DataActionException e) {
				JOptionPane.showMessageDialog(frame, e.getMessage());
			}
		}
		
		// bind visibles
		for (Visible v : ruleset.getVisibles()) {
			v.bind(db);
		}
		
		this.frame = new ContextFrame(db.getContext(), ruleset);
		
		/* Transitioning to a new state */
		frame.addListenerToEvent(stateChanger());
		frame.addDynamicActions(dynamicAction());
		
		// save
		frame.addListenerToSave(saveListener());
		
		// exit
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				db.cleanFiles();
			}
		});
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
		db.pivot();
		TournamentManager tm = new TournamentManager(ruleset, db);
		tm.frame.setVisible(true);	
	}	
}
