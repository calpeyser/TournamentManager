package View;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Cal Peyser
 *
 *
 * General purpose popup to give user information, then return control to calling
 * window.
 */
public class PopupDialog extends JDialog {

	private JButton close;

	/**
	 * Create the dialog.
	 */
	public PopupDialog(Window parent, String message) {
		super(parent, "Popup", Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(new JLabel(message), BorderLayout.NORTH);
		close = new JButton("Close");
		
		getContentPane().add(close, BorderLayout.SOUTH);
	}
	
	public void addListenerToClose(ActionListener listener) {
		close.addActionListener(listener);
	}
	
}

	
	