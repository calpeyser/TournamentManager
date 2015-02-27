package View;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

// adapted this solution from stack overflow
public class CustomDoubleField extends JTextField {
	
	public void processKeyEvent(KeyEvent ev) {
	    char c = ev.getKeyChar();
	    try {
	      // Ignore all non-printable characters. Just check the printable ones.
	      if (c == '.') {
	    	  // let it fly
	      }
	      else if (c > 31 && c < 127) {
	        Integer.parseInt(c + "");
	      }

	      super.processKeyEvent(ev);
	    }
	    catch (NumberFormatException nfe) {
	      // Do nothing. Character inputted is not a number or period, so ignore it.
	    }
	  }
	
	public void setDouble(double in) {
		super.setText(Double.toString(in));
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(super.getText());
	}
	
}
