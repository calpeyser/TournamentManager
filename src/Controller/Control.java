package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import MockTrial.MockTrialTournamentFactory; // TODO: GET RID OF THIS WHEN DONE TESTING
import Ruleset.TournamentFactory;
import View.*;

public class Control {
	
	private HomeFrame frame;
	private TournamentFactory factory;
	
	public Control(TournamentFactory factory) {
		this.factory = factory;
		frame = new HomeFrame(factory.makeRuleset().getName());
		frame.addListenerToNewTournament(newTournament());
		frame.addListenerToContinueTournament(continueTournament());
	}
	
	
	public static void runTournament(TournamentFactory factory) {
		Control c = new Control(factory);
		c.frame.setVisible(true);
	}
	
	private ActionListener newTournament() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.pathChooser = new PathChooserDialog(frame, PathChooser.DIRECTORY_CHOOSER);
				int status = frame.pathChooser.fileChooser.showOpenDialog(frame);
				if (status == JFileChooser.APPROVE_OPTION) {
					String path = frame.pathChooser.fileChooser.getSelectedFile().toString();
					frame.setVisible(false);					
					frame.dispose();
					
					TournamentManager.createNewTourament(factory, path);
				}
			}
		};
	}

	private ActionListener continueTournament() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.pathChooser = new PathChooserDialog(frame, PathChooser.FILE_CHOOOSER);
				int status = frame.pathChooser.fileChooser.showOpenDialog(frame);
				if (status == JFileChooser.APPROVE_OPTION) {
					String path = frame.pathChooser.fileChooser.getSelectedFile().toString();
					frame.setVisible(false);					
					frame.dispose();
					
					TournamentManager.recoverTournament(factory, path);
				}
			}
		};
	}

	public static void run(TournamentFactory factory) {
		runTournament(factory);
	}	
}
