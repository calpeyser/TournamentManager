package DataAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Base.*;
import Ruleset.*;
import View.*;
import Data.*;

import javax.swing.*;

/**
 * 
 * Some DataAction which can be done by the UI
 *
 */
public abstract class UIDataAction extends DynamicDataAction {
	
	// throw the data action onto a from
	public abstract void attachToFrame(ContextFrame frame);
	
}
