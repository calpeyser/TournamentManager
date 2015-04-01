package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Utils.MiscUtils;

public class HomeFrame extends JFrame {

	private JPanel contentPane;
	private JButton newTournamentButton;
	private JButton continueTournamentButton;

	public PathChooserDialog pathChooser;

	public HomeFrame(String tournamentName) {
		super(tournamentName + ": Data Selection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 250);
		contentPane = new JPanel(); 
		contentPane.setLayout(new BorderLayout());
		getContentPane().add(contentPane);
		
		JPanel buttonsPane = new JPanel();
		buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.PAGE_AXIS));
		contentPane.add(new JScrollPane(buttonsPane), BorderLayout.CENTER);		
		
		JPanel textPane = new JPanel();
		textPane.setLayout(new BoxLayout(textPane, BoxLayout.PAGE_AXIS));
		contentPane.add(textPane, BorderLayout.SOUTH);
			
		buttonsPane.add(Box.createRigidArea(new Dimension(0, 30)));
		
		newTournamentButton = new JButton("New Tournament");
		newTournamentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonsPane.add(newTournamentButton);

		buttonsPane.add(Box.createRigidArea(new Dimension(0, 30)));
		
		continueTournamentButton = new JButton("Continue Tournament");
		continueTournamentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonsPane.add(continueTournamentButton);
		
		JLabel title = new JLabel("Tournament Management for " + tournamentName, SwingConstants.CENTER);
		title.setFont(new Font("Dialog", Font.PLAIN, 20));
		contentPane.add(title, BorderLayout.NORTH);

		JLabel explanation1 = new JLabel("Start a new tournament to select a directory in which to store tournament snapshots.", SwingConstants.CENTER);
		explanation1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textPane.add(explanation1);
		
		JLabel explanation2 = new JLabel("Continue a tournament to select a tournament snapshot to continue from.", SwingConstants.CENTER);
		explanation2.setFont(new Font("Dialog", Font.PLAIN,  12));
		textPane.add(explanation2);
		
	}
	
	public void addListenerToNewTournament(ActionListener listener) {
		newTournamentButton.addActionListener(listener);
	}
	
	public void addListenerToContinueTournament(ActionListener listener) {
		continueTournamentButton.addActionListener(listener);
	}
}
