package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HomeFrame extends JFrame {

	private HomeFrame frame;
	private JPanel contentPane;
	private JButton newTournamentButton;
	private JButton continueTournamentButton;

	public PathChooserDialog pathChooser;

	public HomeFrame() {
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		newTournamentButton = new JButton("New Tournament");
		continueTournamentButton = new JButton("Continue Tournament");
		contentPane.add(newTournamentButton, BorderLayout.LINE_START);
		contentPane.add(continueTournamentButton, BorderLayout.LINE_END);
	}
	
	public void addListenerToNewTournament(ActionListener listener) {
		newTournamentButton.addActionListener(listener);
	}
	
	public void addListenerToContinueTournament(ActionListener listener) {
		continueTournamentButton.addActionListener(listener);
	}
	
	
}
