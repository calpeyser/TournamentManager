package View;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.*;

public class PathChooserDialog extends JDialog {

	public JFileChooser fileChooser;
	
	public PathChooserDialog(Window parent, PathChooser type) {
		super(parent, "Choose File Path", Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(new FlowLayout());
		
		// the filechooser
		fileChooser = new JFileChooser();
		if (type == PathChooser.DIRECTORY_CHOOSER)
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		else if (type == PathChooser.FILE_CHOOOSER)
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		else {
			throw new UnsupportedOperationException();
		}
		
		getContentPane().add(fileChooser);
	}
}
