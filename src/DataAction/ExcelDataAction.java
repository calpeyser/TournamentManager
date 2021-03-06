package DataAction;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import org.apache.poi.xssf.usermodel.*;

import View.FileChooserDialog;
import View.PopupDialog;

/**
 * This class is to be extended to create data actions which require excel spreadsheets.
 * The code in this class will handle attaching file-selection to the GUI, so GUI considerations
 * are isolated from the user.  Extended ExcelDataActions need only define a performConfiguration()
 * method, which executes whatever is to be done with the required workbook.
 * @author Cal Peyser
 *
 */
public abstract class ExcelDataAction extends UIDataAction {

	private String excelPath;
	private PopupDialog popup;
	protected XSSFWorkbook workbook;
	
	public void attachToFrame(Window frame) {
		JFileChooser fileChooser = new JFileChooser();
		int status = fileChooser.showOpenDialog(frame);
		
		if (status == JFileChooser.APPROVE_OPTION) {
			excelPath = fileChooser.getSelectedFile().toString();
			try {
				workbook = new XSSFWorkbook(new FileInputStream(excelPath));
				performConfiguration();
			}
			catch (Exception e) {
				db.getEntityManager().getTransaction().commit(); // just in case transaction is active
				String errorMessage = "File cound not be opened as an Excel Spreadsheet.  Error: ";
				errorMessage += e.getLocalizedMessage();
				JOptionPane.showMessageDialog(frame, errorMessage);
			}
		}
	}

	protected abstract void performConfiguration();

}
