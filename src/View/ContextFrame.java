package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Data.*;
import DataAction.DataAction;
import DataAction.UIDataAction;
import Ruleset.*;
import Visibles.Visible;

import javax.swing.JList;

import java.util.*;

import javax.swing.JButton;

import java.awt.Dialog.ModalExclusionType;

public class ContextFrame extends JFrame {

	private JTabbedPane tabbedPane;
	private JPanel stateTab;
	private JPanel actionTab;
	private JTabbedPane visibleTab;
	private Map<Visible, JPanel> visiblePanels;
	
	// stuff for events panel
	private JPanel eventsPanel;
	public Event selectedEvent;
	private ActionListener eventListener;
	
	// stuff for actions panel
	private JPanel actionPanel;
	public UIDataAction selectedAction;
	private ActionListener dataActionListener;
	
	// buttons
	private JButton saveButton;
	
	// dynamic information stores
	public DefaultListModel<Event> nextEventsModel;
	public DefaultListModel<UIDataAction> dataActionsModel;
	
	// dynamic information displays
	public JLabel currentStateLabel;
	public JList<Event> eventList;
	public JList<UIDataAction> actionList;
	
	// tournament-specific data
	private Ruleset ruleset;
	private TournamentContext context;


	public void addListenerToEvent(ActionListener listener) {
		this.eventListener = listener;
	}
	
	public void addDynamicActions(ActionListener listener) {
		this.dataActionListener = listener;
	}
	
	public void addListenerToSave(ActionListener listener) {
		this.saveButton.addActionListener(listener);
	}
	
	public ContextFrame(TournamentContext context, Ruleset ruleset) {
		super("Tournament Manager for " + ruleset.getName());
		
		// set local variables
		this.ruleset = ruleset;
		this.context = context;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		
		
		// create the main tabbed pane with two tabs
		tabbedPane = new JTabbedPane();
		stateTab = new JPanel();
		stateTab.setLayout(new BorderLayout());
		actionTab = new JPanel();
		actionTab.setLayout(new BorderLayout());
		visibleTab = new JTabbedPane();
		tabbedPane.addTab("State Management", stateTab);
		tabbedPane.addTab("Data Management", actionTab);
		tabbedPane.addTab("Statistics", visibleTab);
		
		getContentPane().add(tabbedPane);
		
		// STATE TAB ----------------------------------------------
		
		// Tells current state
		currentStateLabel = new JLabel("", SwingConstants.CENTER);
		currentStateLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
		stateTab.add(currentStateLabel, BorderLayout.PAGE_START);
				
		// instructions
		JPanel instructionsPanel = new JPanel();
		instructionsPanel.setLayout(new BorderLayout());
		JLabel instructionsLabel = new JLabel("Continue the tournament by choosing an event from this panel.", SwingConstants.CENTER);
		instructionsLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		instructionsPanel.add(instructionsLabel, BorderLayout.WEST);
		saveButton = new JButton("Save");
		instructionsPanel.add(saveButton, BorderLayout.EAST);
		stateTab.add(instructionsPanel, BorderLayout.PAGE_END);
		
		// lists all available events
		eventsPanel = new JPanel();
		eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.PAGE_AXIS));

		JScrollPane eventsPane = new JScrollPane(eventsPanel);	
		stateTab.add(eventsPane, BorderLayout.CENTER);
		
		// ACTION TAB ----------------------------------------------
		
		// UI action panel
		actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.PAGE_AXIS));
		
		JScrollPane actionPane = new JScrollPane(actionPanel);
		actionTab.add(actionPane, BorderLayout.CENTER);				

		// title 
		JLabel actionTitleLabel = new JLabel("Available Data Actions", SwingConstants.CENTER);
		actionTitleLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
		actionTab.add(actionTitleLabel, BorderLayout.PAGE_START);
		
		// instructions
		JLabel actionInstructionsLabel = new JLabel("Manage tournament data by selecting an action from this panel.", SwingConstants.CENTER);
		actionInstructionsLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		actionTab.add(actionInstructionsLabel, BorderLayout.PAGE_END);
		// state starts at beginning state given in ruleset
		setState(context.getCurrentState());

		
		// VISIBLE TAB ----------------------------------------------
		
		// visibleTab has all the visible data to give stats on the tournament
		visiblePanels = new HashMap<Visible, JPanel>();
		for (Visible v : ruleset.getVisibles()) {
			JPanel p = new JPanel();
			p.setLayout(new BorderLayout());
			p.add(v.getComponent());
			visibleTab.addTab(v.getName(), p);
			visiblePanels.put(v, p);
		}		
				
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedComponent() == visibleTab) {
					refreshVisibles();
				}
			}
		});
	}

	public void setState(State s) {
		currentStateLabel.setText("Current State: " + s.getName());
		eventsPanel.removeAll();
		for (final Event e : ruleset.getTransitionFunction().getPossibleEvents(s)) {
			JButton button = new JButton(e.getName());
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					selectedEvent = e;
					eventListener.actionPerformed(actionEvent);
				}
			});
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
			eventsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			eventsPanel.add(button);
		}
		
		actionPanel.removeAll();
		for (final UIDataAction a : context.getCurrentState().getDuringConfig()) {
			JButton button = new JButton(a.toString());
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					selectedAction = a;
					dataActionListener.actionPerformed(actionEvent);
				}
			});
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
			actionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			actionPanel.add(button);
		}
		
		this.revalidate();
		this.repaint();

	}
	
	private void refreshVisibles() {
		for (Visible v : visiblePanels.keySet()) {
			visiblePanels.get(v).removeAll();
			JComponent comp = v.getComponent();
			visiblePanels.get(v).add(comp);
		}
	}
		
}
