package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
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

	// buttons
	public JButton btnDoAction;
	public JButton btnDynamicAction;
	
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
		btnDoAction.addActionListener(listener);
	}
	
	public void addDynamicActions(ActionListener listener) {
		btnDynamicAction.addActionListener(listener);
	}
	
	
	public ContextFrame(TournamentContext context, Ruleset ruleset) {
		super("Tournament Manager");
		
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
		tabbedPane.addTab("Current Tournament State", stateTab);
		tabbedPane.addTab("Available Configuration Actions", actionTab);
		tabbedPane.addTab("Tournament Stats", visibleTab);
		
		getContentPane().add(tabbedPane);
		
		// Tells current state
		currentStateLabel = new JLabel();
		stateTab.add(currentStateLabel, BorderLayout.PAGE_START);
		
		// executes event
		btnDoAction = new JButton("Select Event");
		stateTab.add(btnDoAction, BorderLayout.LINE_END);
				
		// lists all available actions
		nextEventsModel = new DefaultListModel<Event>();
		eventList = new JList<Event>(nextEventsModel);
		eventList.setLayoutOrientation(JList.VERTICAL_WRAP);
		eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stateTab.add(eventList, BorderLayout.CENTER);

		
		// button to perform the UI action
		btnDynamicAction = new JButton("Perform Dynamic Configuration");
		actionTab.add(btnDynamicAction, BorderLayout.LINE_END);
		
		dataActionsModel = new DefaultListModel<UIDataAction>();
		
		actionList = new JList<UIDataAction>(dataActionsModel);
		actionList.setLayoutOrientation(JList.VERTICAL_WRAP);
		actionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actionTab.add(actionList, BorderLayout.CENTER);
		
		// state starts at beginning state given in ruleset
		setState(context.getCurrentState());
		
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
		nextEventsModel.removeAllElements();
		for (Event e : ruleset.getTransitionFunction().getPossibleEvents(context.getCurrentState())) {
			nextEventsModel.addElement(e);
		}
		dataActionsModel.removeAllElements();
		for (UIDataAction a : context.getCurrentState().getDuringConfig()) {
			dataActionsModel.addElement(a);
		}
	}
	
	private void refreshVisibles() {
		for (Visible v : visiblePanels.keySet()) {
			visiblePanels.get(v).removeAll();
			JComponent comp = v.getComponent();
			visiblePanels.get(v).add(comp);
		}
	}
		
}
