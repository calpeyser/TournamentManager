package View;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import DataAction.DBTableModel;

public class TableDialog extends JDialog {

	private JButton exitButton;
	private JButton addButton;
	private JButton deleteButton;
	private JButton editButton;
	
	// public components
	public JTable table;
	
	/**
	 * Create the dialog.
	 */
	public TableDialog(Window parent, DBTableModel data) {
		super(parent, "Table Configuration", Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 1500, 600);
		getContentPane().setLayout(new BorderLayout());
		
		table = new JTable(data);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane tableScrollPane = new JScrollPane(table);
		
		JPanel buttonPane = new JPanel();
		exitButton = new JButton("Exit");
		addButton = new JButton("Add Row");
		editButton = new JButton("Edit Row");
		deleteButton = new JButton("Delete Row");
		buttonPane.add(addButton); buttonPane.add(editButton); buttonPane.add(deleteButton); buttonPane.add(exitButton); 
		
		
		getContentPane().add(tableScrollPane, BorderLayout.NORTH);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}
	
	public void addListenerToExit(ActionListener listener) {
		exitButton.addActionListener(listener);
	}
	public void addListenerToAdd(ActionListener listener) {
		addButton.addActionListener(listener);
	}
	public void addListenerToDelete(ActionListener listener) {
		deleteButton.addActionListener(listener);
	}
	public void addListenerToEdit(ActionListener listener) {
		editButton.addActionListener(listener);
	}
	public void disableAdd() {
		addButton.setEnabled(false);
	}
	public void disableEdit() {
		editButton.setEnabled(false);
	}
	public void disableDelete() {
		deleteButton.setEnabled(false);
	}
} 
