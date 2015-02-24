package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Utils.MiscUtils;

public class HomeFrame extends JFrame {

	private HomeFrame frame;
	private JPanel contentPane;
	private JButton newTournamentButton;
	private JButton continueTournamentButton;

	public PathChooserDialog pathChooser;

	public HomeFrame(String tournamentName) {
		super(tournamentName);
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setContentPane(contentPane);
		
		JLabel title = new JLabel("Tournament Management for " + tournamentName, SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 16));
		c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
		contentPane.add(title, c);
		
		newTournamentButton = new JButton("New Tournament");
		c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
		contentPane.add(newTournamentButton, c);
		continueTournamentButton = new JButton("Continue Tournament");
		c.gridx = 1; c.gridy = 1;
		contentPane.add(continueTournamentButton, c);
		
		JLabel newExplination = new JLabel(MiscUtils.convertToMultiline("Select a directory in which data\n for a new tournament will be saved."), SwingConstants.CENTER);
		newExplination.setFont(new Font("Serif", Font.PLAIN, 12));
		c.gridx = 0; c.gridy = 2;
		contentPane.add(newExplination, c);
		
		JLabel continueExplination = new JLabel(MiscUtils.convertToMultiline("Select a database from which\n to continue a tournament."), SwingConstants.CENTER);
		continueExplination.setFont(new Font("Serif", Font.PLAIN, 12));
		c.gridx = 1; c.gridy = 2; 
		contentPane.add(continueExplination, c);
	}
	
	public void addListenerToNewTournament(ActionListener listener) {
		newTournamentButton.addActionListener(listener);
	}
	
	public void addListenerToContinueTournament(ActionListener listener) {
		continueTournamentButton.addActionListener(listener);
	}
}
