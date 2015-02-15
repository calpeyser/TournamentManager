package View;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// Adapted from 
// http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemoProject/src/components/FileChooserDemo.java

public class FileChooserDialog extends JDialog {

	public JFileChooser fileChooser;
	
	/**
	 * Create the dialog.
	 */
	public FileChooserDialog(Window parent) {
		super(parent, "Select Excel File", Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(new FlowLayout());
		
		// the filechooser
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		getContentPane().add(fileChooser);
	}
	
}
